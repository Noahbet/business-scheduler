package learn.scheduler.controller;

import learn.scheduler.domain.Result;
import learn.scheduler.domain.ServiceService;
import learn.scheduler.models.Business;
import learn.scheduler.models.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/service")
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<Service> searchById(@PathVariable int serviceId) {
        Service serviceResult = service.getServiceById(serviceId);

        if (serviceResult == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serviceResult);
    }

    @PostMapping
    public ResponseEntity<Service> addService(@RequestBody Service serviceToAdd) {
        Result<Service> result = service.addService(serviceToAdd);
        return new ResponseEntity<>(result.getPayload(), getStatus(result, HttpStatus.CREATED));
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<Void> updateService(@PathVariable int serviceId, @RequestBody Service serviceUpdated) {
        if (serviceUpdated != null && serviceUpdated.getServiceId() != serviceId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Service> result = service.updateService(serviceUpdated);
        return new ResponseEntity<>(getStatus(result, HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable int serviceId) {
        Result<Service> result = service.deleteService(serviceId);
        return new ResponseEntity<>(getStatus(result, HttpStatus.NO_CONTENT));
    }

    private HttpStatus getStatus(Result<Service> result, HttpStatus statusDefault) {
        return switch (result.getStatus()) {
            case INVALID -> HttpStatus.PRECONDITION_FAILED;
            case DUPLICATE -> HttpStatus.FORBIDDEN;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> statusDefault;
        };
    }
}
