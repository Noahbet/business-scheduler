package learn.scheduler.models;

import java.time.LocalTime;

public class Availability {

    private LocalTime mondayStart;
    private LocalTime mondayEnd;
    private LocalTime mondayBreakStart;
    private LocalTime mondayBreakEnd;
    private LocalTime tuesdayStart;
    private LocalTime tuesdayEnd;
    private LocalTime tuesdayBreakStart;
    private LocalTime tuesdayBreakEnd;
    private LocalTime wednesdayStart;
    private LocalTime wednesdayEnd;
    private LocalTime wednesdayBreakStart;
    private LocalTime wednesdayBreakEnd;
    private LocalTime thursdayStart;
    private LocalTime thursdayEnd;
    private LocalTime thursdayBreakStart;
    private LocalTime thursdayBreakEnd;
    private LocalTime fridayStart;
    private LocalTime fridayEnd;
    private LocalTime fridayBreakStart;
    private LocalTime fridayBreakEnd;
    private LocalTime saturdayStart;
    private LocalTime saturdayEnd;
    private LocalTime saturdayBreakStart;
    private LocalTime saturdayBreakEnd;
    private LocalTime sundayStart;
    private LocalTime sundayEnd;
    private LocalTime sundayBreakStart;
    private LocalTime sundayBreakEnd;

    public Availability(LocalTime mondayStart, LocalTime mondayEnd, LocalTime mondayBreakStart,
                        LocalTime mondayBreakEnd, LocalTime tuesdayStart, LocalTime tuesdayEnd,
                        LocalTime tuesdayBreakStart, LocalTime tuesdayBreakEnd, LocalTime wednesdayStart,
                        LocalTime wednesdayEnd, LocalTime wednesdayBreakStart, LocalTime wednesdayBreakEnd,
                        LocalTime thursdayStart, LocalTime thursdayEnd, LocalTime thursdayBreakStart,
                        LocalTime thursdayBreakEnd, LocalTime fridayStart, LocalTime fridayEnd,
                        LocalTime fridayBreakStart, LocalTime fridayBreakEnd, LocalTime saturdayStart,
                        LocalTime saturdayEnd, LocalTime saturdayBreakStart, LocalTime saturdayBreakEnd,
                        LocalTime sundayStart, LocalTime sundayEnd, LocalTime sundayBreakStart,
                        LocalTime sundayBreakEnd) {
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
