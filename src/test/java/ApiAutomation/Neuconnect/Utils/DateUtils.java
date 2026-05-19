package ApiAutomation.Neuconnect.Utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils
{
    public static String getDateTime (){
        try{
            LocalDateTime now = LocalDateTime.now();

            // Define the custom format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a d MMMM yyyy");

            // Format the current date and time
            String formattedDate = now.format(formatter);

            // Handle the suffix for the day (e.g., 1st, 2nd, 3rd, etc.)
            String day = String.valueOf(now.getDayOfMonth());
            String daySuffix = getDaySuffix(now.getDayOfMonth());
            return formattedDate.replace(day, day + daySuffix);
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return null;
        }
    }

    public static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";  // Special case for 11th, 12th, 13th
        }
        switch (day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }
}
