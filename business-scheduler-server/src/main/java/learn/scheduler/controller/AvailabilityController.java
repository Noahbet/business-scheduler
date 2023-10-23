package learn.scheduler.controller;

import learn.scheduler.domain.AvailabilityService;
import learn.scheduler.domain.Result;
import learn.scheduler.models.Availability;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityService service;

    public AvailabilityController(AvailabilityService service) {
        this.service = service;
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<Availability> searchByBusinessId(@PathVariable int businessId) {
        Availability availability = service.searchByBusinessId(businessId);
        if (availability == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(availability);
    }

    @PutMapping("/{businessId}")
    public ResponseEntity<Void> update(@PathVariable int businessId, @RequestBody Availability availability) {
        if (availability != null && availability.getBusinessId() != businessId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Availability> result = service.updateAvailability(availability);
        return new ResponseEntity<>(getStatus(result, HttpStatus.NO_CONTENT));
    }

    private HttpStatus getStatus(Result<Availability> result, HttpStatus statusDefault) {
        return switch (result.getStatus()) {
            case INVALID -> HttpStatus.PRECONDITION_FAILED;
            case DUPLICATE -> HttpStatus.FORBIDDEN;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> statusDefault;
        };
    }
}