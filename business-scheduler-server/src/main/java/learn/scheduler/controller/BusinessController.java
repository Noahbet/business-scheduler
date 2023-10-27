package learn.scheduler.controller;

import learn.scheduler.domain.*;
import learn.scheduler.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService service;
    private final AvailabilityService availabilityService;
    private final AppointmentService appointmentService;
    private final ServiceService serviceService;

    public BusinessController(BusinessService service, AvailabilityService availabilityService,
                              AppointmentService appointmentService, ServiceService serviceService) {
        this.service = service;
        this.availabilityService = availabilityService;
        this.appointmentService = appointmentService;
        this.serviceService = serviceService;
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<Business> searchById(@PathVariable int businessId) {
        Business business = service.searchById(businessId);

        if (business == null) {
            return ResponseEntity.notFound().build();
        }

        business.setAppointments(appointmentService.searchByBusinessId(business.getBusinessId()));
        business.setAvailability(availabilityService.searchByBusinessId(business.getBusinessId()));
        business.setServices(serviceService.getServicesForBusiness(business.getBusinessId()));

        return ResponseEntity.ok(business);
    }

    @GetMapping("/query/{query}")
    public ResponseEntity<List<Business>> searchBusinessesByQuery(@PathVariable String query) {
        List<Business> businesses = service.searchBusinesses(query);
        if (businesses == null) {
            return ResponseEntity.notFound().build();
        }

        for (Business business: businesses) {
            business.setAppointments(appointmentService.searchByBusinessId(business.getBusinessId()));
            business.setAvailability(availabilityService.searchByBusinessId(business.getBusinessId()));
            business.setServices(serviceService.getServicesForBusiness(business.getBusinessId()));
        }

        return ResponseEntity.ok(businesses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Business>> searchBusinessesByCat(@PathVariable String category) {

        try {
            Category categoryCat = Category.valueOf(category);
        } catch (NumberFormatException e) {
            Result<Business> result = new Result<>();
            result.addMessage(ActionStatus.INVALID, "Invalid enum value: " + category);
            return new ResponseEntity<>(getStatus(result, HttpStatus.NOT_FOUND));
        }

        List<Business> businesses = service.searchByCategory(Category.valueOf(category));

        for (Business business: businesses) {
            business.setAppointments(appointmentService.searchByBusinessId(business.getBusinessId()));
            business.setAvailability(availabilityService.searchByBusinessId(business.getBusinessId()));
            business.setServices(serviceService.getServicesForBusiness(business.getBusinessId()));
        }

        return ResponseEntity.ok(businesses);
    }

    @PostMapping
    public ResponseEntity<Business> addBusiness(@RequestBody Business business) {
        Result<Business> result = service.addBusiness(business);
        business.getAvailability().setBusinessId(result.getPayload().getBusinessId());

        Result<Availability> serviceResult = availabilityService.addAvailability(business.getAvailability());

        if (!serviceResult.isSuccess()) {
            result.addMessage(serviceResult.getStatus(), serviceResult.getMessages().get(0));
        }

        if (!result.isSuccess()) {
            service.deleteBusiness(business.getBusinessId());
            return new ResponseEntity<>(result.getPayload(), getStatus(result, HttpStatus.CONFLICT));
        }

        return new ResponseEntity<>(result.getPayload(), getStatus(result, HttpStatus.CREATED));
    }

    @PutMapping("/{businessId}")
    public ResponseEntity<Void> update(@PathVariable int businessId, @RequestBody Business business) {
        if (business != null && business.getBusinessId() != businessId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Business> result = service.updateBusinessName(business);
        return new ResponseEntity<>(getStatus(result, HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/{businessId}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable int businessId) {
        Result<Business> result = service.deleteBusiness(businessId);
        return new ResponseEntity<>(getStatus(result, HttpStatus.NO_CONTENT));
    }

    private HttpStatus getStatus(Result<Business> result, HttpStatus statusDefault) {
        return switch (result.getStatus()) {
            case INVALID -> HttpStatus.PRECONDITION_FAILED;
            case DUPLICATE -> HttpStatus.FORBIDDEN;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> statusDefault;
        };
    }
}
