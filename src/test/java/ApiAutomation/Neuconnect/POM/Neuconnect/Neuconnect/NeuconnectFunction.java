package ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect;

import ApiAutomation.Neuconnect.BasePage;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnectendpoints;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.cucumber.java.zh_tw.假設;
import net.minidev.json.JSONObject;
import org.apache.commons.collections4.Put;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONArray;

public class NeuconnectFunction extends BasePage {

    public static String ListUsers(String bearerToken) {

        try {
            JSONObject payload = new JSONObject();
            String Response = MakeApiCall(payload.toString(), Neuconnectendpoints.ListUser, bearerToken, Constants.GET, false,"" ,"");
            PrintUtil.PrintLog("Response" + Response);
            return Response;

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();

        }
    }

    public static String ListDepartment(String bearerToken) {
        try {
            JSONObject payload = new JSONObject();

            String response = MakeApiCall(payload.toString(), Neuconnectendpoints.ListDepartment, bearerToken, Constants.GET, false, "", "");
            PrintUtil.PrintLog("Response " + response);
            return response;
        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }

    public static String AddUser(String bearerToken, String userName, String password,
                                 String email, String phone, String departmentId, String role) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("userName", userName);
            payload.put("password", password);
            payload.put("email", email);
            payload.put("phone", phone);
            payload.put("departmentId", departmentId);
            payload.put("role", role);

            PrintUtil.PrintSuccessLog("PAYLOAD: " + payload.toString());
            return MakeApiCall(payload.toString(), Neuconnectendpoints.AddUser, bearerToken, Constants.POST, false, "", "");
        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }

    public static String AddDepartment(String bearerToken, String departmentName) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("departmentName", departmentName);


            PrintUtil.PrintSuccessLog("Payload  : " + payload.toString());
            return MakeApiCall(payload.toString(), Neuconnectendpoints.AddDepartment, bearerToken, Constants.POST, false, "", "");

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String ListAllWarehouse(String bearToken){
        try {
            JSONObject payload = new JSONObject();


            return MakeApiCall("",Neuconnectendpoints.ListAllWarehouse,bearToken,Constants.GET,false,"","");
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String AddWarehousetoUser(String bearerToken, String userId, String normalWarehouseIds, String receiverWarehouseIds) {
        try {
            JSONObject payload = new JSONObject();

            JSONArray normalArray = new JSONArray();
            normalArray.put(normalWarehouseIds);

            JSONArray receiverArray = new JSONArray();
            receiverArray.put(receiverWarehouseIds);

            payload.put("userId", userId);
            payload.put("normalWarehouseIds", normalArray);   // ✅ payload not body
            payload.put("receiverWarehouseIds", receiverArray); // ✅ payload not body

            PrintUtil.PrintSuccessLog("Payload  : " + payload.toString());
            return MakeApiCall(payload.toString(), Neuconnectendpoints.Addwarehousetouser, bearerToken, Constants.POST, false, "", "");

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String ListwarehousebyuserId(String bearerToken,String userId) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("userId", userId);





            return MakeApiCall("", Neuconnectendpoints.Listwarehousebyuserid, bearerToken, Constants.GET, true, "userId", "");

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String ListAllITRS(String bearerToken) {
        try {
            JSONObject payload = new JSONObject();


            return MakeApiCall("", Neuconnectendpoints.ListAllITRS, bearerToken, Constants.GET, false, "", "");

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String CreateInventoryTransferRequest(String bearerToken,String fromWareHouseCode, String toWareHouseCode, String itemCode, int quantity, String uom, String barCode, String binCode, String series, String  seriesName){
        try {
            JSONObject payload = new JSONObject();
            payload.put("fromWareHouseCode",fromWareHouseCode);
            payload.put("toWareHouseCode",toWareHouseCode);
            payload.put("itemCode",itemCode);
            payload.put("quantity",quantity);
            payload.put("uom",uom);
            payload.put("barCode",barCode);
            payload.put("binCode",binCode);
            payload.put("series",series);
            payload.put("seriesName",seriesName);

            PrintUtil.PrintSuccessLog("Payload  : " + payload.toString());
            return MakeApiCall(payload.toString(), Neuconnectendpoints.CreateInventoryTransferRequest, bearerToken, Constants.POST, false, "", "");
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }

    public static String listAllItemCodebyUserId(String bearerToken, String userId) {
        try {
            PrintUtil.PrintSuccessLog("userId query param: " + userId);
            return MakeApiCall(
                    "",
                    Neuconnectendpoints.ListAllItemsByUserId, // already has ? at end
                    bearerToken,
                    Constants.GET,
                    true,
                    "userId=",
                    userId
            );
        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String GetDocumentSeries(String bearerToken, int documentType) {
        try {
            PrintUtil.PrintSuccessLog("DocumentType query param: " + documentType);

            return MakeApiCall(
                    "",
                    Neuconnectendpoints.GetDocumentSeries,
                    bearerToken,
                    Constants.GET,
                    true,
                    "documentType=" + documentType,   // ← key fix
                    ""
            );
        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String CreateIT(
            String bearerToken,
            String itrId,
            int quantity,
            String barCode,
            String binCode,
            String series,
            String seriesName) {

        try {

            JSONObject payload = new JSONObject();

            payload.put("itrId", itrId);
            payload.put("quantity", quantity);
            payload.put("barCode", barCode);
            payload.put("binCode", binCode);
            payload.put("series", series);
            payload.put("seriesName", seriesName);

            PrintUtil.PrintSuccessLog("Payload : " + payload.toString());

            return MakeApiCall(
                    payload.toString(),
                    Neuconnectendpoints.CreateIT,
                    bearerToken,
                    Constants.POST,
                    false,
                    "",
                    ""
            );

        } catch (Exception err) {

            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String ListAllInventoryTransfers(String bearerToken) {
        try {
            JSONObject payload = new JSONObject();


            return MakeApiCall("", Neuconnectendpoints.ListDirectIT, bearerToken, Constants.GET, false, "", "");

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String CreateTransferReceiveDirect(
            String bearerToken,
            String fromWareHouseCode,
            String toWareHouseCode,
            String itemCode,
            int quantity,
            String barCode,
            String binCode,
            String series,
            String seriesName
    ) {
        try {

            JSONObject payload = new JSONObject();

            payload.put("fromWareHouseCode", fromWareHouseCode);
            payload.put("toWareHouseCode", toWareHouseCode);
            payload.put("itemCode", itemCode);
            payload.put("quantity", quantity);
            payload.put("barCode", barCode);
            payload.put("binCode", binCode);
            payload.put("series", series);
            payload.put("seriesName", seriesName);

            PrintUtil.PrintSuccessLog("Transfer Receive Payload: " + payload.toString());

            return MakeApiCall(
                    payload.toString(),
                    Neuconnectendpoints.TransferReceiveDirect,
                    bearerToken,
                    Constants.POST,
                    false,
                    "",
                    ""
            );

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
    public static String ListAllTransferReceive(String bearerToken) {
        try {
            JSONObject payload = new JSONObject();


            return MakeApiCall("", Neuconnectendpoints.ListAllTransferReceive, bearerToken, Constants.GET, false, "", "");

        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }




}