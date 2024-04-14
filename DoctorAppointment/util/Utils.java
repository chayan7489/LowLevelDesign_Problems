package DoctorAppointment.util;

import java.time.LocalTime;

public class Utils {

    public static LocalTime convertStringToTime(String str){

        String[] time = str.split(":");

        // LocalTime of() = In this method, the hour and minute are passed
        // in the Integer format and it returns time-based on these values.
        // This method throws "DateTimeException" if any parameter value either of hour or minute exceeds the range.
        return LocalTime.of(Integer.parseInt(time[0]),Integer.parseInt(time[1]));
    }
}
