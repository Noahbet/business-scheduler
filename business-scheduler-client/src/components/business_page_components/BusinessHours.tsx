
type availability = {mondayStart: string, mondayEnd: string, mondayBreakStart: string, mondayBreakEnd: string, 
    tuesdayStart: string, tuesdayEnd: string, tuesdayBreakStart: string, tuesdayBreakEnd: string, 
    wednesdayStart: string, wednesdayEnd: string, wednesdayBreakStart: string, wednesdayBreakEnd: string, 
    thursdayStart: string, thursdayEnd: string, thursdayBreakStart: string, thursdayBreakEnd: string, 
    fridayStart: string, fridayEnd: string, fridayBreakStart: string, fridayBreakEnd: string, 
    saturdayStart: string, saturdayEnd: string, saturdayBreakStart: string, saturdayBreakEnd: string, 
    sundayStart: string, sundayEnd: string, sundayBreakStart: string, sundayBreakEnd: string
  }

  function formatTime(time:string) {
    let hours = Number(time.slice(0, 2))
    let after = ""

    if (hours > 12) {
        after = "PM";
        hours = hours - 12;
    } else {
        after = "AM";
    }

    let string = hours + ":" + time.slice(3, 5) + after;
    return string;
  }

function BusinessHours({availability} : {availability:availability}) {  
  
    return (
    <div className="flex flex-wrap items-center m-5">
        <div className="flex flex-col">
            <p>Monday hours: {formatTime(availability.mondayStart)}-{formatTime(availability.mondayBreakStart)} {formatTime(availability.mondayBreakEnd)}-{formatTime(availability.mondayEnd)}</p>
            <p>Tuesday hours: {formatTime(availability.tuesdayStart)}-{formatTime(availability.tuesdayBreakStart)} {formatTime(availability.tuesdayBreakEnd)}-{formatTime(availability.tuesdayEnd)}</p>
            <p>Wednesday hours: {formatTime(availability.wednesdayStart)}-{formatTime(availability.wednesdayBreakStart)} {formatTime(availability.wednesdayBreakEnd)}-{formatTime(availability.wednesdayEnd)}</p>
            <p>Thursday hours: {formatTime(availability.thursdayStart)}-{formatTime(availability.thursdayBreakStart)} {formatTime(availability.thursdayBreakEnd)}-{formatTime(availability.thursdayEnd)}</p>
            <p>Friday hours: {formatTime(availability.fridayStart)}-{formatTime(availability.fridayBreakStart)} {formatTime(availability.fridayBreakEnd)}-{formatTime(availability.fridayEnd)}</p>
            <p>Saturday hours: {formatTime(availability.saturdayStart)}-{formatTime(availability.saturdayBreakStart)} {formatTime(availability.saturdayBreakEnd)}-{formatTime(availability.saturdayEnd)}</p>
            <p>Sunday hours: {formatTime(availability.sundayStart)}-{formatTime(availability.sundayBreakStart)} {formatTime(availability.sundayBreakEnd)}-{formatTime(availability.sundayEnd)}</p>
        </div>
    </div>
    );
  }
  
  export default BusinessHours;