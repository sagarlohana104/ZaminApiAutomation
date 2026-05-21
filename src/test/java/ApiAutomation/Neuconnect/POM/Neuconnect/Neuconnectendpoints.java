package ApiAutomation.Neuconnect.POM.Neuconnect;

import org.apache.poi.hssf.record.SaveRecalcRecord;

import javax.mail.search.SearchTerm;

public class Neuconnectendpoints
{

    // category management
    public static String ListUser="/ZCAPI/IUserManagementFeature/ListUsers";
    public static String ListDepartment= "/ZCAPI/IUserManagementFeature/ListDepartments";
    public static String AddUser ="/ZCAPI/IUserManagementFeature/AddUser";
    public static String AddDepartment= "/ZCAPI/IUserManagementFeature/AddDepartment";
    public static String ListAllWarehouse= "/ZCAPI/IWarehouseFeature/ListAllWarehouses";
    public static String Addwarehousetouser="/ZCAPI/IWarehouseFeature/AddWarehousesToUser";
    public static String Listwarehousebyuserid="/ZCAPI/IWarehouseFeature/ListAllWarehousesByUserId";
    public static String ListAllITRS="/ZCAPI/IInventoryTransferRequestFeature/ListAllItr";
    public static String CreateInventoryTransferRequest="/ZCAPI/IInventoryTransferRequestFeature/CreateInventoryTransferRequest";
    public static String ListAllItemsByUserId="/ZCAPI/IInventoryTransferRequestFeature/ListAllItemsByUserId?";
    public static String GetDocumentSeries= "/ZCAPI/ISapFeature/GetDocumentSeries?";
    //public static String ListALlITRS="/ZCAPI/IInventoryTransferRequestFeature/ListAllItr";
    public static String CreateIT="/ZCAPI/IInventoryTransferFeature/CreateIT";
    public static String ListDirectIT="/ZCAPI/IInventoryTransferFeature/ListAllInventoryTransfers";
    public static String TransferReceiveDirect="/ZCAPI/ITransferReceiveFeature/CreateTransferReceive";
    public static String ListAllTransferReceive="/ZCAPI/ITransferReceiveFeature/ListAllTransferReceive";
    public static String CreateGoodsisseue="/ZCAPI/IGoodIssueFeature/CreateGoodIssue";
    public static String ListAllGoodIssue="/ZCAPI/IGoodIssueFeature/ListAllGoodIssue";
    public static String ListAllProductionOrders="/ZCAPI/IProductionOrderFeature/ListAllProductionOrders";
    public static String  CreateProductionOrderItr="/ZCAPI/IProductionOrderFeature/CreateProductionOrderItr";























}
