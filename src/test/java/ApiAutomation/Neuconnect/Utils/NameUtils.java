package ApiAutomation.Neuconnect.Utils;


import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NameUtils
{
    private static final Set<String> generatedAddresses = new HashSet<>();
    private static final Random random = new Random();
    private static final Set<String> generatedNumbers = new HashSet<>();

    public static String getUniqueName (String name){
        try{
            String prefix = name + "_";
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            return prefix + timeStamp;
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static int getCount (){
        try{
            Random random = new Random();

            // Generate a random number between 1 and 10 (inclusive)
            int count = random.nextInt(4) + 1;

            // Return the count as a string
            return count;
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return 0;
        }
    }

    public static String getUniqueType (String type){

        try {

            if (type.equalsIgnoreCase("number")) {
                // Generate a unique 11-digit number
                Random random = new Random();
                long number = (long)(random.nextDouble() * 1_000_000_0000L); // 11 digits
                return String.format("%011d", number);
            }
            else if (type.equalsIgnoreCase("cnic")) {
                // Generate a unique 13-digit CNIC
                Random random = new Random();
                long cnic = (long)(random.nextDouble() * 1_000_000_000_000L); // 13 digits
                return String.format("%013d", cnic);
            }
            else if (type.equalsIgnoreCase("email")) {
                // Generate a unique email
                String prefix = "user.";
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                return prefix + timeStamp + "@example.com";
            }
            else if(type.equalsIgnoreCase("itemId")){
                Random random = new Random();
                int itemId = random.nextInt(100) + 1;
                return String.valueOf(itemId);
            }
            else if (type.equalsIgnoreCase("deviceId")) {
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                StringBuilder deviceId = new StringBuilder();
                Random random = new Random();

                // Generate a random 5-character string consisting of letters and digits
                for (int i = 0; i < 5; i++) {
                    int index = random.nextInt(characters.length());
                    deviceId.append(characters.charAt(index));
                }

                return deviceId.toString();
            }

            else {
                return "Invalid type! Please use 'number', 'cnic', or 'email'.";
            }
        }
        catch (Exception err) {
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }

    }

    public static String generateUniquePhoneNumber() {
        String prefix = "92"; // Start with a specific prefix
        Random random = new Random();

        String phoneNumber;
        do {
            // Generate the remaining 7 digits randomly
            StringBuilder sb = new StringBuilder(prefix);
            for (int i = 0; i < 7; i++) {
                sb.append(random.nextInt(10)); // Append a random digit (0-9)
            }
            phoneNumber = sb.toString();
        } while (generatedNumbers.contains(phoneNumber)); // Ensure uniqueness

        // Add to the set of generated numbers
        generatedNumbers.add(phoneNumber);
        return phoneNumber;
    }


        public static String generateUniqueAddress() {
            String address;
            do {
                char letter = (char) ('A' + random.nextInt(26)); // Random letter A-Z
                int number = 100 + random.nextInt(900); // Random 3-digit number (100-999)
                address = letter + "-" + number;
            } while (generatedAddresses.contains(address)); // Ensure uniqueness

            generatedAddresses.add(address);
            return address;
        }

    private static final Set<String> generatedEmails = new HashSet<>();
    private static final String DOMAIN = "@example.com";

    public static String generateUniqueEmail() {
        Random random = new Random();
        String email;

        do {
            // Create a unique username part using random digits
            StringBuilder sb = new StringBuilder("user");
            for (int i = 0; i < 5; i++) { // Generate 5 random digits
                sb.append(random.nextInt(10));
            }
            email = sb.toString() + DOMAIN;

        } while (generatedEmails.contains(email)); // Ensure uniqueness

        // Add email to set for uniqueness tracking
        generatedEmails.add(email);
        return email;
    }
    private static Set<String> generatedCodes = new HashSet<>();

    public static String generateUniqueAlphanumericCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        String code;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                // Pick a random character from the allowed set
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            code = sb.toString();
        } while (generatedCodes.contains(code)); // Ensure uniqueness

        generatedCodes.add(code);
        return code;
    }

    public static int generateRandomSingleDigit() {
        Random random = new Random();
        return random.nextInt(10); // Returns 0 to 9
    }
    public static String generateRandomCarName() {
        String[] brands = {"Toyota", "Honda", "Suzuki", "BMW", "Audi"};
        String[] models = {"Civic", "Corolla", "Sport", "LX", "X"};

        Random random = new Random();

        String brand = brands[random.nextInt(brands.length)];
        String model = models[random.nextInt(models.length)];
        int number = 100 + random.nextInt(900);

        return brand + "-" + model + "-" + number;
    }
    public class DateUtils {

        private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

        // ✅ Automatically returns current date and time
        public static String getCurrentDate() {
            return ZonedDateTime.now(ZoneOffset.UTC)
                    .format(FORMATTER);
        }

        // ✅ Future date by custom days
        public static String getFutureDateByDays(int days) {
            if (days <= 0) {
                throw new IllegalArgumentException("Days must be greater than 0. Provided: " + days);
            }
            return ZonedDateTime.now(ZoneOffset.UTC)
                    .plusDays(days)
                    .format(FORMATTER);
        }
    }




}
