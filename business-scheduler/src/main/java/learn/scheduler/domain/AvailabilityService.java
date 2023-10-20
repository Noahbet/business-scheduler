package learn.scheduler.domain;

import learn.scheduler.data.AppointmentRepository;
import learn.scheduler.data.AvailabilityRepository;
import learn.scheduler.data.BusinessRepository;
import learn.scheduler.models.Appointment;
import learn.scheduler.models.Availability;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class AvailabilityService {

    private final AvailabilityRepository repository;

    private final BusinessRepository businessRepository;

    private final Validator validator;

    public AvailabilityService(AvailabilityRepository repository,BusinessRepository businessRepository, Validator validator) {
        this.repository = repository;
        this.businessRepository = businessRepository;
        this.validator = validator;
    }

    public Availability searchByBusinessId(int businessId) {
        return repository.searchByBusinessId(businessId);
    }

    public Result<Availability> addAvailability(Availability availability) {
        Result<Availability> result = validate(availability);
        if (!result.isSuccess()) {
            return result;
        }

        if (availability.getAvailabilityId() != 0) {
            result.addMessage(ActionStatus.INVALID, "AvailabilityId cannot be set for `add` operation");
            return result;
        }

        availability = repository.addAvailability(availability);
        result.setPayload(availability);
        return result;
    }

    public Result<Availability> updateAvailability(Availability availability) {

        Result<Availability> result = validate(availability);
        if (result.getStatus() != ActionStatus.SUCCESS) {
            return result;
        }

        if (!repository.updateAvailability(availability)) {
            result.addMessage(ActionStatus.NOT_FOUND, "Availability for businessId `" + availability.getBusinessId() + "` not found.");
        }

        return result;
    }

    private Result<Availability> validate(Availability availability) {

        Result<Availability> result = new Result<>();

        if (availability == null) {
            result.addMessage(ActionStatus.INVALID, "Availability cannot be null.");
            return result;
        }

        Set<ConstraintViolation<Availability>> violations = validator.validate(availability);

        for (ConstraintViolation<Availability> violation : violations) {
            result.addMessage(ActionStatus.INVALID, violation.getMessage());
            return result;
        }

        if (availability.getMondayStart().isAfter(availability.getMondayEnd())) {
            result.addMessage(ActionStatus.INVALID, "MondayStart cannot be after MondayEnd.");
            return result;
        }

        if (availability.getMondayBreakStart().isAfter(availability.getMondayBreakEnd())) {
            result.addMessage(ActionStatus.INVALID, "MondayBreakStart cannot be after MondayBreakEnd.");
            return result;
        }

        if (availability.getTuesdayStart().isAfter(availability.getTuesdayEnd())) {
            result.addMessage(ActionStatus.INVALID, "TuesdayStart cannot be after TuesdayEnd.");
            return result;
        }

        if (availability.getTuesdayBreakStart().isAfter(availability.getTuesdayBreakEnd())) {
            result.addMessage(ActionStatus.INVALID, "TuesdayBreakStart cannot be after TuesdayBreakEnd.");
            return result;
        }

        if (availability.getWednesdayStart().isAfter(availability.getWednesdayEnd())) {
            result.addMessage(ActionStatus.INVALID, "WednesdayStart cannot be after WednesdayEnd.");
            return result;
        }

        if (availability.getWednesdayBreakStart().isAfter(availability.getWednesdayBreakEnd())) {
            result.addMessage(ActionStatus.INVALID, "WednesdayBreakStart cannot be after WednesdayBreakEnd.");
            return result;
        }

        if (availability.getThursdayStart().isAfter(availability.getThursdayEnd())) {
            result.addMessage(ActionStatus.INVALID, "ThursdayStart cannot be after ThursdayEnd.");
            return result;
        }

        if (availability.getThursdayBreakStart().isAfter(availability.getThursdayBreakEnd())) {
            result.addMessage(ActionStatus.INVALID, "ThursdayBreakStart cannot be after ThursdayBreakEnd.");
            return result;
        }

        if (availability.getFridayStart().isAfter(availability.getFridayEnd())) {
            result.addMessage(ActionStatus.INVALID, "FridayStart cannot be after FridayEnd.");
            return result;
        }

        if (availability.getFridayBreakStart().isAfter(availability.getFridayBreakEnd())) {
            result.addMessage(ActionStatus.INVALID, "FridayBreakStart cannot be after FridayBreakEnd.");
            return result;
        }

        if (availability.getSaturdayStart().isAfter(availability.getSaturdayEnd())) {
            result.addMessage(ActionStatus.INVALID, "SaturdayStart cannot be after SaturdayEnd.");
            return result;
        }

        if (availability.getSaturdayBreakStart().isAfter(availability.getSaturdayBreakEnd())) {
            result.addMessage(ActionStatus.INVALID, "SaturdayBreakStart cannot be after SaturdayBreakEnd.");
            return result;
        }

        if (availability.getSundayStart().isAfter(availability.getSundayEnd())) {
            result.addMessage(ActionStatus.INVALID, "SundayStart cannot be after SundayEnd.");
            return result;
        }

        if (availability.getSundayBreakStart().isAfter(availability.getSundayBreakEnd())) {
            result.addMessage(ActionStatus.INVALID, "SundayBreakStart cannot be after SundayBreakEnd.");
            return result;
        }

        if (businessRepository.searchById(availability.getBusinessId()) == null) {
            result.addMessage(ActionStatus.NOT_FOUND, "Business with id " + availability.getBusinessId() + " not found.");
        }

        return result;
    }
}
