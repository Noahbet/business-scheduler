package learn.scheduler.controller;

import learn.scheduler.domain.AppointmentService;
import learn.scheduler.domain.BusinessService;
import learn.scheduler.domain.Result;
import learn.scheduler.models.Appointment;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService service;

    public BusinessController(BusinessService service) {
        this.service = service;
    }

    @GetMapping("/query/{query}")
    public ResponseEntity<List<Business>> searchBusinesses(@PathVariable String query) {
        List<Business> businesses = service.searchBusinesses(query);
        if (businesses == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(businesses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Business>> searchBusinesses(@PathVariable Category category) {
        List<Business> businesses = service.searchByCategory(category);
        if (businesses == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(businesses);
    }

    @PostMapping
    public ResponseEntity<Business> addBusiness(@RequestBody Business business) {
        Result<Business> result = service.addBusiness(business);
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

    @DeleteMapping("/{appointmentId}")
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
