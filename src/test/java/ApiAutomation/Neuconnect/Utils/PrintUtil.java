package ApiAutomation.Neuconnect.Utils;

import ApiAutomation.Neuconnect.Utils.env.envConfig;

public class PrintUtil
{
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    private static final boolean isConsoleLogsEnabled =
            System.getenv("CONSOLE_LOGS") != null
            ? Boolean.parseBoolean(System.getenv("CONSOLE_LOGS"))
            : envConfig.getEnvBoolean("CONSOLE_LOGS");

    public static void PrintLog (String log){
        if(isConsoleLogsEnabled){
            System.out.println(log);
        }
    }

    public static void PrintSuccessLog (String log){
        if(isConsoleLogsEnabled){
            System.out.println(GREEN +log + RESET);
        }
    }

    public static void PrintErrorLog (String log){
        if(isConsoleLogsEnabled){
            System.out.println(RED +log + RESET);
        }
    }

    public static void PrintWarningLog (String log){
        if(isConsoleLogsEnabled){
            System.out.println(YELLOW +log + RESET);
        }
    }

}
