package ApiAutomation.Neuconnect.Utils.env;


import io.github.cdimascio.dotenv.Dotenv;

public class envConfig
{
    private static final Dotenv dotenv = Dotenv.load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }

    public static int getEnvInteger(String key) {
        String value = dotenv.get(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The value for " + key + " is not a valid integer.");
            }
        }
        throw new IllegalArgumentException("The key " + key + " does not exist in the .env file.");
    }

    public static boolean getEnvBoolean(String key) {
        String value = dotenv.get(key);
        if (value != null) {
            return Boolean.parseBoolean(value); // This will return true/false based on the string value
        }
        throw new IllegalArgumentException("The key " + key + " does not exist in the .env file or is not a valid boolean.");
    }

}
