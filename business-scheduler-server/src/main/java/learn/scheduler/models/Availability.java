package learn.scheduler.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Availability {

    private int availabilityId;
    @Positive
    private int businessId;
    @NotNull(message ="MondayStart should not be null.")
    private LocalTime mondayStart;
    @NotNull(message ="MondayEnd should not be null.")
    private LocalTime mondayEnd;
    @NotNull(message ="MondayBreakStart should not be null.")
    private LocalTime mondayBreakStart;
    @NotNull(message ="MondayBreakEnd should not be null.")
    private LocalTime mondayBreakEnd;
    @NotNull(message ="TuesdayStart should not be null.")
    private LocalTime tuesdayStart;
    @NotNull(message ="TuesdayEnd should not be null.")
    private LocalTime tuesdayEnd;
    @NotNull(message ="TuesdayBreakStart should not be null.")
    private LocalTime tuesdayBreakStart;
    @NotNull(message ="TuesdayBreakEnd should not be null.")
    private LocalTime tuesdayBreakEnd;
    @NotNull(message ="WednesdayStart should not be null.")
    private LocalTime wednesdayStart;
    @NotNull(message ="WednesdayEnd should not be null.")
    private LocalTime wednesdayEnd;
    @NotNull(message ="WednesdayBreakStart should not be null.")
    private LocalTime wednesdayBreakStart;
    @NotNull(message ="WednesdayBreakEnd should not be null.")
    private LocalTime wednesdayBreakEnd;
    @NotNull(message ="ThursdayStart should not be null.")
    private LocalTime thursdayStart;
    @NotNull(message ="ThursdayEnd should not be null.")
    private LocalTime thursdayEnd;
    @NotNull(message ="ThursdayBreakStart should not be null.")
    private LocalTime thursdayBreakStart;
    @NotNull(message ="ThursdayBreakEnd should not be null.")
    private LocalTime thursdayBreakEnd;
    @NotNull(message ="FridayStart should not be null.")
    private LocalTime fridayStart;
    @NotNull(message ="FridayEnd should not be null.")
    private LocalTime fridayEnd;
    @NotNull(message ="FridayBreakStart should not be null.")
    private LocalTime fridayBreakStart;
    @NotNull(message ="FridayBreakEnd should not be null.")
    private LocalTime fridayBreakEnd;
    @NotNull(message ="SaturdayStart should not be null.")
    private LocalTime saturdayStart;
    @NotNull(message ="SaturdayEnd should not be null.")
    private LocalTime saturdayEnd;
    @NotNull(message ="SaturdayBreakStart should not be null.")
    private LocalTime saturdayBreakStart;
    @NotNull(message ="SaturdayBreakEnd should not be null.")
    private LocalTime saturdayBreakEnd;
    @NotNull(message ="SundayStart should not be null.")
    private LocalTime sundayStart;
    @NotNull(message ="SundayEnd should not be null.")
    private LocalTime sundayEnd;
    @NotNull(message ="SundayBreakStart should not be null.")
    private LocalTime sundayBreakStart;
    @NotNull(message ="SundayBreakEnd should not be null.")
    private LocalTime sundayBreakEnd;

    public Availability(int businessId,
            LocalTime mondayStart, LocalTime mondayEnd, LocalTime mondayBreakStart,
                        LocalTime mondayBreakEnd, LocalTime tuesdayStart, LocalTime tuesdayEnd,
                        LocalTime tuesdayBreakStart, LocalTime tuesdayBreakEnd, LocalTime wednesdayStart,
                        LocalTime wednesdayEnd, LocalTime wednesdayBreakStart, LocalTime wednesdayBreakEnd,
                        LocalTime thursdayStart, LocalTime thursdayEnd, LocalTime thursdayBreakStart,
                        LocalTime thursdayBreakEnd, LocalTime fridayStart, LocalTime fridayEnd,
                        LocalTime fridayBreakStart, LocalTime fridayBreakEnd, LocalTime saturdayStart,
                        LocalTime saturdayEnd, LocalTime saturdayBreakStart, LocalTime saturdayBreakEnd,
                        LocalTime sundayStart, LocalTime sundayEnd, LocalTime sundayBreakStart,
                        LocalTime sundayBreakEnd) {
        this.businessId = businessId;
        this.mondayStart = mondayStart;
        this.mondayEnd = mondayEnd;
        this.mondayBreakStart = mondayBreakStart;
        this.mondayBreakEnd = mondayBreakEnd;
        this.tuesdayStart = tuesdayStart;
        this.tuesdayEnd = tuesdayEnd;
        this.tuesdayBreakStart = tuesdayBreakStart;
        this.tuesdayBreakEnd = tuesdayBreakEnd;
        this.wednesdayStart = wednesdayStart;
        this.wednesdayEnd = wednesdayEnd;
        this.wednesdayBreakStart = wednesdayBreakStart;
        this.wednesdayBreakEnd = wednesdayBreakEnd;
        this.thursdayStart = thursdayStart;
        this.thursdayEnd = thursdayEnd;
        this.thursdayBreakStart = thursdayBreakStart;
        this.thursdayBreakEnd = thursdayBreakEnd;
        this.fridayStart = fridayStart;
        this.fridayEnd = fridayEnd;
        this.fridayBreakStart = fridayBreakStart;
        this.fridayBreakEnd = fridayBreakEnd;
        this.saturdayStart = saturdayStart;
        this.saturdayEnd = saturdayEnd;
        this.saturdayBreakStart = saturdayBreakStart;
        this.saturdayBreakEnd = saturdayBreakEnd;
        this.sundayStart = sundayStart;
        this.sundayEnd = sundayEnd;
        this.sundayBreakStart = sundayBreakStart;
        this.sundayBreakEnd = sundayBreakEnd;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public List<LocalTime> getAvailabilityByDay(DayOfWeek day) {
        List<LocalTime> times = new ArrayList<>();

        switch (day) {
            case MONDAY ->
                    times = List.of(getMondayStart(), getMondayEnd(), getMondayBreakStart(), getMondayBreakEnd());
            case TUESDAY ->
                    times = List.of(getTuesdayStart(), getTuesdayEnd(), getTuesdayBreakStart(), getTuesdayBreakEnd());
            case WEDNESDAY ->
                    times = List.of(getWednesdayStart(), getWednesdayEnd(), getWednesdayBreakStart(), getWednesdayBreakEnd());
            case THURSDAY ->
                    times = List.of(getThursdayStart(), getThursdayEnd(), getThursdayBreakStart(), getThursdayBreakEnd());
            case FRIDAY ->
                    times = List.of(getFridayStart(), getFridayEnd(), getFridayBreakStart(), getFridayBreakEnd());
            case SATURDAY ->
                    times = List.of(getSaturdayStart(), getSaturdayEnd(), getSaturdayBreakStart(), getSaturdayBreakEnd());
            case SUNDAY ->
                    times = List.of(getSundayStart(), getSundayEnd(), getSundayBreakStart(), getSundayBreakEnd());
            default -> {
            }
        }
        return times;
    }

    public LocalTime getMondayStart() {
        return mondayStart;
    }

    public void setMondayStart(LocalTime mondayStart) {
        this.mondayStart = mondayStart;
    }

    public LocalTime getMondayEnd() {
        return mondayEnd;
    }

    public void setMondayEnd(LocalTime mondayEnd) {
        this.mondayEnd = mondayEnd;
    }

    public LocalTime getMondayBreakStart() {
        return mondayBreakStart;
    }

    public void setMondayBreakStart(LocalTime mondayBreakStart) {
        this.mondayBreakStart = mondayBreakStart;
    }

    public LocalTime getMondayBreakEnd() {
        return mondayBreakEnd;
    }

    public void setMondayBreakEnd(LocalTime mondayBreakEnd) {
        this.mondayBreakEnd = mondayBreakEnd;
    }

    public LocalTime getTuesdayStart() {
        return tuesdayStart;
    }

    public void setTuesdayStart(LocalTime tuesdayStart) {
        this.tuesdayStart = tuesdayStart;
    }

    public LocalTime getTuesdayEnd() {
        return tuesdayEnd;
    }

    public void setTuesdayEnd(LocalTime tuesdayEnd) {
        this.tuesdayEnd = tuesdayEnd;
    }

    public LocalTime getTuesdayBreakStart() {
        return tuesdayBreakStart;
    }

    public void setTuesdayBreakStart(LocalTime tuesdayBreakStart) {
        this.tuesdayBreakStart = tuesdayBreakStart;
    }

    public LocalTime getTuesdayBreakEnd() {
        return tuesdayBreakEnd;
    }

    public void setTuesdayBreakEnd(LocalTime tuesdayBreakEnd) {
        this.tuesdayBreakEnd = tuesdayBreakEnd;
    }

    public LocalTime getWednesdayStart() {
        return wednesdayStart;
    }

    public void setWednesdayStart(LocalTime wednesdayStart) {
        this.wednesdayStart = wednesdayStart;
    }

    public LocalTime getWednesdayEnd() {
        return wednesdayEnd;
    }

    public void setWednesdayEnd(LocalTime wednesdayEnd) {
        this.wednesdayEnd = wednesdayEnd;
    }

    public LocalTime getWednesdayBreakStart() {
        return wednesdayBreakStart;
    }

    public void setWednesdayBreakStart(LocalTime wednesdayBreakStart) {
        this.wednesdayBreakStart = wednesdayBreakStart;
    }

    public LocalTime getWednesdayBreakEnd() {
        return wednesdayBreakEnd;
    }

    public void setWednesdayBreakEnd(LocalTime wednesdayBreakEnd) {
        this.wednesdayBreakEnd = wednesdayBreakEnd;
    }

    public LocalTime getThursdayStart() {
        return thursdayStart;
    }

    public void setThursdayStart(LocalTime thursdayStart) {
        this.thursdayStart = thursdayStart;
    }

    public LocalTime getThursdayEnd() {
        return thursdayEnd;
    }

    public void setThursdayEnd(LocalTime thursdayEnd) {
        this.thursdayEnd = thursdayEnd;
    }

    public LocalTime getThursdayBreakStart() {
        return thursdayBreakStart;
    }

    public void setThursdayBreakStart(LocalTime thursdayBreakStart) {
        this.thursdayBreakStart = thursdayBreakStart;
    }

    public LocalTime getThursdayBreakEnd() {
        return thursdayBreakEnd;
    }

    public void setThursdayBreakEnd(LocalTime thursdayBreakEnd) {
        this.thursdayBreakEnd = thursdayBreakEnd;
    }

    public LocalTime getFridayStart() {
        return fridayStart;
    }

    public void setFridayStart(LocalTime fridayStart) {
        this.fridayStart = fridayStart;
    }

    public LocalTime getFridayEnd() {
        return fridayEnd;
    }

    public void setFridayEnd(LocalTime fridayEnd) {
        this.fridayEnd = fridayEnd;
    }

    public LocalTime getFridayBreakStart() {
        return fridayBreakStart;
    }

    public void setFridayBreakStart(LocalTime fridayBreakStart) {
        this.fridayBreakStart = fridayBreakStart;
    }

    public LocalTime getFridayBreakEnd() {
        return fridayBreakEnd;
    }

    public void setFridayBreakEnd(LocalTime fridayBreakEnd) {
        this.fridayBreakEnd = fridayBreakEnd;
    }

    public LocalTime getSaturdayStart() {
        return saturdayStart;
    }

    public void setSaturdayStart(LocalTime saturdayStart) {
        this.saturdayStart = saturdayStart;
    }

    public LocalTime getSaturdayEnd() {
        return saturdayEnd;
    }

    public void setSaturdayEnd(LocalTime saturdayEnd) {
        this.saturdayEnd = saturdayEnd;
    }

    public LocalTime getSaturdayBreakStart() {
        return saturdayBreakStart;
    }

    public void setSaturdayBreakStart(LocalTime saturdayBreakStart) {
        this.saturdayBreakStart = saturdayBreakStart;
    }

    public LocalTime getSaturdayBreakEnd() {
        return saturdayBreakEnd;
    }

    public void setSaturdayBreakEnd(LocalTime saturdayBreakEnd) {
        this.saturdayBreakEnd = saturdayBreakEnd;
    }

    public LocalTime getSundayStart() {
        return sundayStart;
    }

    public void setSundayStart(LocalTime sundayStart) {
        this.sundayStart = sundayStart;
    }

    public LocalTime getSundayEnd() {
        return sundayEnd;
    }

    public void setSundayEnd(LocalTime sundayEnd) {
        this.sundayEnd = sundayEnd;
    }

    public LocalTime getSundayBreakStart() {
        return sundayBreakStart;
    }

    public void setSundayBreakStart(LocalTime sundayBreakStart) {
        this.sundayBreakStart = sundayBreakStart;
    }

    public LocalTime getSundayBreakEnd() {
        return sundayBreakEnd;
    }

    public void setSundayBreakEnd(LocalTime sundayBreakEnd) {
        this.sundayBreakEnd = sundayBreakEnd;
    }
}
