package learn.scheduler.controller;

import learn.scheduler.domain.AppointmentService;
import learn.scheduler.domain.Result;
import learn.scheduler.models.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Appointment>> searchByUserId(@PathVariable int userId) {
        List<Appointment> appointment = service.searchByUserId(userId);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/businessId/{businessId}")
    public ResponseEntity<List<Appointment>> searchByBusinessId(@PathVariable int businessId) {
        List<Appointment> appointment = service.searchByBusinessId(businessId);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        Result<Appointment> result = service.addAppointment(appointment);
        return new ResponseEntity<>(result.getPayload(), getStatus(result, HttpStatus.CREATED));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int appointmentId) {
        Result<Appointment> result = service.deleteAppointment(appointmentId);
        return new ResponseEntity<>(getStatus(result, HttpStatus.NO_CONTENT));
    }

    private HttpStatus getStatus(Result<Appointment> result, HttpStatus statusDefault) {
        return switch (result.getStatus()) {
            case INVALID -> HttpStatus.PRECONDITION_FAILED;
            case DUPLICATE -> HttpStatus.FORBIDDEN;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> statusDefault;
        };
    }
}
