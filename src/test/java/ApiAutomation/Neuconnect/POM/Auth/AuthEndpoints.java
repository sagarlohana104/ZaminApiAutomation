package ApiAutomation.Neuconnect.POM.Auth;

public class AuthEndpoints
{
    public static String SignUp ="/Auth/IAuthFeature/SignUp";
    public static String Login = "/Auth/IAuthFeature/Login";
    public static String LoginPartnerCRM = "/Auth/IAuthFeature/LoginPartnerCRM";
    public static String LoginFranchise = "/Auth/IAuthFeature/LoginFranchise";
    public static String LoginTechnician = "/Auth/IAuthFeature/LoginTechnician";
    public static String RefreshToken = "/Auth/IAuthFeature/RefreshToken";
//    public static String ForgetPassword = "/Auth/IAuthFeature/ForgetPassword";
    public static String ChangePassword = "/Auth/IAuthFeature/ChangePassword";
    public static String GenerateOTP = "/Auth/IAuthFeature/GenerateOTP";
    public static String VerifyOTP = "/Auth/IAuthFeature/VerifyOTP";
    public static String ListOTPs = "/Auth/IAuthFeature/ListOTPs";
    public static String LoginByIqama = "/Auth/IAuthFeature/LoginByIqama";
    public static String LoginSLA = "/Auth/IAuthFeature/LoginSLA";
    public static String LoginViwer="/Auth/IAuthFeature/LoginViewer";
    //role
    public static String CreateRole = "/Auth/IRoleManagementFeature/CreateRole";
    public static String AddRoleToUser = "/Auth/IRoleManagementFeature/AddRoleToUser";
    public static String DeleteRole = "/Auth/IRoleManagementFeature/DeleteRole";
    public static String GetPoliciesByRoleId = "/Auth/IRoleManagementFeature/GetPoliciesByRoleId.feature";
    public static String GetRole = "/Auth/IRoleManagementFeature/GetRole";
    public static String ListRoles = "/Auth/IRoleManagementFeature/ListRoles";
    public static String UpdateRole = "/Auth/IRoleManagementFeature/UpdateRole";
    public static String AddActionInRoles = "/Auth/IRoleManagementFeature/AddActionInRoles";
    public static String AddActionsInRole = "/Auth/IRoleManagementFeature/AddActionsInRole";
    public static String AddMultipleRolesToUsers = "/Auth/IRoleManagementFeature/AddMultipleRolesToUsers";

    // otp
    public static String ForgetPassword="/Auth/IAuthFeature/ForgetPassword";
    public static String VerifyOtp = "/Auth/IAuthFeature/VerifyOTP";
    public static String CreateNewPasswordAfterOtp="/Auth/IAuthFeature/CreateNewPasswordAfterOTP";
//    public static String GenerateOTP = "";

    //actions
//    public static String AddActionsInRole = "/Auth/IActionsFeature/AddActionsInRole";
    public static String AddActions = "/Auth/IActionsFeature/AddActions";
    public static String AppendActionTag = "/Auth/IActionsFeature/AppendActionTag";
    public static String DeleteAction = "/Auth/IActionsFeature/DeleteAction";
    public static String DeleteActions= "/Auth/IActionsFeature/DeleteActions";
    public static String GetAllAction = "/Auth/IActionsFeature/GetAllAction";
    public static String GetOverAllActions = "/Auth/IActionsFeature/GetOverallActions";
    public static String RemoveActionTag = "/Auth/IActionsFeature/RemoveActionTag";
    public static String GetActionById = "/Auth/IActionsFeature/GetActionById";
    public static String GetActionDetailsById = "/Auth/IActionsFeature/GetActionsDetailsById";

    //user management
    public static String AddUser = "/Auth/IUserManagementFeature/AddUser";
    public static String DeleteUser = "/Auth/IUserManagementFeature/DeleteUser";
    public static String GetUser = "/Auth/IUserManagementFeature/GetUser";
    public static String GetUserDetailsWithActions = "/Auth/IUserManagementFeature/GetUserDetailsWithActions";
    public static String ListUsers = "/Auth/IUserManagementFeature/ListUsers";
    public static String UpdateUser = "/Auth/IUserManagementFeature/UpdateUser";

//    NOTIFICATION
public static String AddNotification = "/Auth/INotificationFeature/AddNotification";



}
