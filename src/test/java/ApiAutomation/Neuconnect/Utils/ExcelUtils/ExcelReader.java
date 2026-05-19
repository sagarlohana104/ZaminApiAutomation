package ApiAutomation.Neuconnect.Utils.ExcelUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader
{
    public static List<Object[]> readCsvData(String filePath) throws IOException {
        List<Object[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        // Skip the header line
        br.readLine();

        // Read each line of the CSV
        while ((line = br.readLine()) != null) {
            // Split each line into columns based on commas
            String[] columns = line.split(",");

            // Add the columns as an Object[] array to the data list
            data.add(columns);
        }

        br.close();
        return data;
    }
}
