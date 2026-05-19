package ApiAutomation.Neuconnect.Utils.ExcelUtils;

import ApiAutomation.Neuconnect.Utils.PrintUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

public class CucumberReportToExcel
{

    public static void convertCucumberJsonReportToExcel (String jsonReportPath, String excelReportPath){
        try{

            // Read and parse the JSON file
            JsonNode rootNode = new ObjectMapper().readTree(new File(jsonReportPath));
            PrintUtil.PrintSuccessLog("ROOTE NODE :  "+ rootNode);
            // Create a new Excel workbook
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Test Results");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Test Case Name");
            headerRow.createCell(1).setCellValue("Test Case Status");

            // Write test case data to the Excel sheet
            int rowNum = 1;
            Iterator<JsonNode> testCases = rootNode.path("elements").elements();

            while (testCases.hasNext()) {
                JsonNode testCase = testCases.next();
                String testCaseName = testCase.path("name").asText();
                String status = testCase.path("status").asText();

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(testCaseName);
                row.createCell(1).setCellValue(status);
            }

            // Save the Excel file to Desktop
            try (FileOutputStream fileOut = new FileOutputStream(excelReportPath)) {
                workbook.write(fileOut);
            }

            // Close the workbook
            workbook.close();
            System.out.println("Excel report generated: " + excelReportPath);

        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return;
        }
    }
}
