package ApiAutomation.Neuconnect.Utils.Dumps;

import ApiAutomation.Neuconnect.Utils.PrintUtil;

import java.util.ArrayList;
import java.util.List;

public class AuthActionDumps
{

    public static List<String> ListTechnicianActions (){
        try{
            List<String> apiEndpoints = new ArrayList<>();
            apiEndpoints.add("/Auth/IAuthFeature/AddOrUpdate2FA");
            apiEndpoints.add("/Auth/IAuthFeature/Remove2FA");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/GetSuggestedExtraMaterials");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/GetTechnician");
            apiEndpoints.add("/Auth/IAuthFeature/ChangePassword");
            apiEndpoints.add("/FranchiseAPI/IServicesManagmentFeature/SearchServices");
            apiEndpoints.add("/Auth/IAuthFeature/ForgetPassword");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/GetSparePartsRequestByID");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/SearchSpareParts");
            apiEndpoints.add("/CSAPI/ITicketFeature/UpdateTicketProduct");
            apiEndpoints.add("/ProductApi/ICustomerProductManagementFeature/UpdateCustomerWarranty");
            apiEndpoints.add("/FranchiseAPI/IDashboardManagement/GetTechnicianTicketKPIs");
            apiEndpoints.add("/FranchiseAPI/IJobScheduleManagementFeature/UpdateExistingJob");
            apiEndpoints.add("/Auth/IAuthFeature/LoginByIqama");
            apiEndpoints.add("/FranchiseAPI/IServicesManagmentFeature/GetSuggestedServices");
            apiEndpoints.add("/FranchiseAPI/IJobScheduleManagementFeature/CreateJobQuotation");
            apiEndpoints.add("/FranchiseAPI/IJobScheduleManagementFeature/UpdateJob");
            apiEndpoints.add("/FranchiseAPI/IJobScheduleManagementFeature/CompleteJob");
            apiEndpoints.add("/FranchiseAPI/IJobScheduleManagementFeature/GetJobByTicketAndTechnicianId");
            apiEndpoints.add("/FranchiseAPI/IInvoiceManagementFeature/GetInvoiceByJobId");
            apiEndpoints.add("/FranchiseAPI/IJobScheduleManagementFeature/GetJob");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/ApproveSparePartsRequest");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/GetInStockTechnicianSpareParts");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/GetSparePartsRequestByTeamID");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/AddSparePartsRequest");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/GetSuggestedSpareParts");
            apiEndpoints.add("/CSAPI/ITicketFeature/ListTicketsByTechnicianId");
            apiEndpoints.add("/FranchiseAPI/ITechnicianTicketFeature/UpdateTicketStatusByTechnician");
            apiEndpoints.add("/CSAPI/ITicketFeature/GetTicket");
            apiEndpoints.add("/FranchiseAPI/IJobScheduleManagementFeature/AddJob");
            apiEndpoints.add("/CSAPI/ITicketFeature/ListNonAssignedTicketsByTechnicianId");
            apiEndpoints.add("/CSAPI/ITicketFeature/UpdateTicketDateTime");
            apiEndpoints.add("/Messenger/IMessagingFeature/AddChatAndUserToIt");
            apiEndpoints.add("/Messenger/IMessagingFeature/AddUserMessageToChat");
            apiEndpoints.add("/Messenger/IMessagingFeature/ListLastChatMessagesByUserId");
            apiEndpoints.add("/Messenger/IMessagingFeature/ListAllMessagesWithUserByChatId");
            return apiEndpoints;
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return null;
        }
    }

    public static List<String> ListFranchiseActions(){
        try{
            List<String> apiEndpoints = new ArrayList<>();
            apiEndpoints.add("/Auth/IAuthFeature/AddOrUpdate2FA");
            apiEndpoints.add("/Auth/IAuthFeature/Remove2FA");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/UpdateIqama");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/UpdateTechnician");
            apiEndpoints.add("/CSAPI/ITicketFeature/GetTicketLogs");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/RemoveTechnician");
            apiEndpoints.add("/Auth/IAuthFeature/ForgetPassword");
            apiEndpoints.add("/FranchiseAPI/IDashboardManagement/GetActiveTechnicians");
            apiEndpoints.add("/FranchiseAPI/IDashboardManagement/GetSparePartKPI");
            apiEndpoints.add("/FranchiseAPI/IDashboardManagement/GetCompletedJobs");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/GetTicketStatsMonthWiseByTechnicianId");
            apiEndpoints.add("/NotificationService/INotificationFeature/ListUserNotifications");
            apiEndpoints.add("/FranchiseAPI/IDashboardManagement/GetTechnicianProductivityByID");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/GetTicketStatsWeekWise");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/GetAverageDetails");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/GetReschedulingAnalysis");
            apiEndpoints.add("/SLAAPI/IFranchiseFeature/UpdateFranchiseAddress");
            apiEndpoints.add("/FranchiseAPI/IDashboardManagement/GetTechnicianProductivity");
            apiEndpoints.add("/FranchiseAPI/IDashboardManagement/GetTechnicianTicketKPIs");
            apiEndpoints.add("/SLAAPI/IFranchiseFeature/UpdateFranchise");
            apiEndpoints.add("/SLAAPI/IFranchiseFeature/GetFranchiseById");
            apiEndpoints.add("/FranchiseAPI/ISkillManagementFeature/GetSkillsByCategory");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/GetAddTechnicianValidity");
            apiEndpoints.add("/Auth/IAuthFeature/LoginFranchise");
            apiEndpoints.add("/Auth/IAuthFeature/RefreshToken");
            apiEndpoints.add("/CSAPI/IIssueManagementFeature/ListIssues");
            apiEndpoints.add("/CSAPI/IIssueManagementFeature/GetIssueById");
            apiEndpoints.add("/CSAPI/IIssueManagementFeature/AddIssue");
            apiEndpoints.add("/CSAPI/IIssueManagementFeature/UpdateIssueById");
            apiEndpoints.add("/CSAPI/IIssueManagementFeature/DeleteissueById");
            apiEndpoints.add("/CSAPI/ITicketFeature/ListTicketByFranchiseId");
            apiEndpoints.add("/FranchiseAPI/ISparePartsManagementFeature/GetSparePartsRequestByTeamID");
            apiEndpoints.add("/CSAPI/ITicketFeature/ListTicketsByTechnicianId");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketInformation");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketbystatusandPriority");
            apiEndpoints.add("/CSAPI/ITicketFeature/DeleteTicketByTicketId");
            apiEndpoints.add("/CSAPI/ITicketFeature/GetTicket");
            apiEndpoints.add("/CSAPI/ITicketFeature/UpdateTicketStatus");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketByFranchiseIdAndTicketId");
            apiEndpoints.add("/Messenger/IMessagingFeature/AddChatAndUserToIt");
            apiEndpoints.add("/Messenger/IMessagingFeature/AddUserMessageToChat");
            apiEndpoints.add("/Messenger/IMessagingFeature/ListLastChatMessagesByUserId");
            apiEndpoints.add("/Messenger/IMessagingFeature/ListAllMessagesWithUserByChatId");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/GetRelevantTechnicians");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/ListTechniciansByFranchise");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/GetTechnician");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/GetTechnicianSkills");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/ListTechniciansByExp");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/SearchTechnician");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/AddTechnician");
            apiEndpoints.add("/FranchiseAPI/IServicesManagmentFeature/GetServicesInformation");
            apiEndpoints.add("/FranchiseAPI/ISkillManagementFeature/AddSkill");
            apiEndpoints.add("/FranchiseAPI/ISkillManagementFeature/UpdateSkill");
            apiEndpoints.add("/FranchiseAPI/ISkillManagementFeature/RemoveSkill");
            apiEndpoints.add("/FranchiseAPI/ISkillManagementFeature/SearchSkill");
            apiEndpoints.add("/FranchiseAPI/ISkillManagementFeature/ListSkills");
            apiEndpoints.add("/ProductApi/IProductManagementFeature/ListProducts");
            apiEndpoints.add("/ProductApi/IProductCategoryManagementFeature/ListProductCategory");
            apiEndpoints.add("/CSAPI/ICountryManagementFeature/ListCountry");
            apiEndpoints.add("/CSAPI/IStateManagementFeature/GetStateByCountryId");
            apiEndpoints.add("/CSAPI/ICityManagementFeature/GetCityByStateId");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketByFranchiseIdAndStatusAndPriority");
            return apiEndpoints;
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return null;
        }
    }

    public static List<String>  ListCspActions (){
        try{
            List<String> apiEndpoints = new ArrayList<>();
            apiEndpoints.add("/ProductApi/IProductManagementFeature/UpdateProduct");
            apiEndpoints.add("/ProductApi/IProductManagementFeature/DeleteProduct");
            apiEndpoints.add("/Auth/IAuthFeature/Remove2FA");
            apiEndpoints.add("/Auth/IAuthFeature/AddOrUpdate2FA");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/GetTicketByPriorityByCompanyIdYearMonthWeek");
            apiEndpoints.add("/CSAPI/ITicketFeature/CheckCompanyIdAndOrderId");
            apiEndpoints.add("/ProductApi/IProductManagementFeature/SearchProductByNameAndCompanyId");
            apiEndpoints.add("/ProductApi/IProductCategoryManagementFeature/ListProductCategory");
            apiEndpoints.add("/ProductApi/IModelManagementFeature/SearchModelByName");
            apiEndpoints.add("/ProductApi/IBrandManagementFeature/SearchBrandByName");
            apiEndpoints.add("/ProductApi/IProductManagementFeature/ListProductByCompanyId");
            apiEndpoints.add("/ProductApi/IProductManagementFeature/RegisteredMultipleProducts");
            apiEndpoints.add("/ProductApi/ICustomerProductManagementFeature/SearchCustomerProductBySerialNumber");
            apiEndpoints.add("/Auth/IAuthFeature/ForgetPassword");
            apiEndpoints.add("/CSAPI/ITicketFeature/GetTicketLogs");
            apiEndpoints.add("/Messenger/IMessagingFeature/ListAllMessagesWithUserByChatId");
            apiEndpoints.add("/Messenger/IMessagingFeature/AddUserMessageToChat");
            apiEndpoints.add("/Messenger/IMessagingFeature/AddChatAndUserToIt");
            apiEndpoints.add("/Auth/IRoleManagementFeature/GetRoleByTag");
            apiEndpoints.add("/Messenger/IMessagingFeature/ListLastChatMessagesByUserId");
            apiEndpoints.add("/SLAAPI/IPartnerFeature/ListAllPartnerUsers");
            apiEndpoints.add("/SLAAPI/IPartnerFeature/AddPartnerUser");
            apiEndpoints.add("/FranchiseAPI/ITechnicianManagementFeature/GetRelevantTechnicians");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketByCompanyIdAndCustomerIdAndTicketId");
            apiEndpoints.add("/NotificationService/INotificationFeature/ListUserNotifications");
            apiEndpoints.add("/SLAAPI/IPartnerFeature/UpdatePartnerAddress");
            apiEndpoints.add("/CSAPI/ITicketFeature/ListTicketsByCompanyAndCustomerId");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/DashBoardEndPoint");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/GetTicketStatsYearWise");
            apiEndpoints.add("/CSAPI/IDashboardManagementFeature/GetTicketStatsMonthWise");
            apiEndpoints.add("/SLAAPI/IPartnerFeature/UpdatePartner");
            apiEndpoints.add("/SLAAPI/IPartnerFeature/GetPartnerById");
            apiEndpoints.add("/CSAPI/IIssueManagementFeature/ListIssuesByProductCategoryId");
            apiEndpoints.add("/CSAPI/IAddressManagement/SearchAddressByCustomerIdAndAddress");
            apiEndpoints.add("/Auth/IAuthFeature/LoginPartnerCRM");
            apiEndpoints.add("/Auth/IAuthFeature/RefreshToken");
            apiEndpoints.add("/CSAPI/IAddressManagement/GetAddressByCustomerId");
            apiEndpoints.add("/CSAPI/IAddressManagement/AddAddress");
            apiEndpoints.add("/CSAPI/ICountryManagementFeature/ListCountry");
            apiEndpoints.add("/CSAPI/IStateManagementFeature/GetStateByCountryId");
            apiEndpoints.add("/CSAPI/ICityManagementFeature/GetCityByStateId");
            apiEndpoints.add("/CSAPI/ICustomerManagementFeature/GetCustomerById");
            apiEndpoints.add("/CSAPI/ICustomerManagementFeature/AddCustomerInUser");
            apiEndpoints.add("/CSAPI/ICustomerManagementFeature/SearchCustomerInformation");
            apiEndpoints.add("/CSAPI/IIssueManagementFeature/ListIssues");
            apiEndpoints.add("/CSAPI/ITicketFeature/ListTicketsByCompanyId");
            apiEndpoints.add("/CSAPI/ITicketFeature/ListTicketsByTechnicianId");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketByCompanyIdAndTicketId");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketByCompanyId");
            apiEndpoints.add("/CSAPI/ITicketFeature/DeleteTicketByTicketId");
            apiEndpoints.add("/CSAPI/ITicketFeature/GetTicket");
            apiEndpoints.add("/CSAPI/ITicketFeature/RegisterTicket");
            apiEndpoints.add("/CSAPI/ITicketFeature/SearchTicketInformation");
            apiEndpoints.add("/CSAPI/ITicketFeature/UpdateTicketStatus");
            apiEndpoints.add("/ProductApi/ICustomerProductManagementFeature/GetCustomerProductByCustomerId");
            apiEndpoints.add("/ProductApi/IProductManagementFeature/SearchProduct");
            apiEndpoints.add("/ProductApi/ICustomerProductManagementFeature/AddCustomerProduct");
            apiEndpoints.add("/ProductApi/ICustomerProductManagementFeature/SearchCustomerProduct");

            return apiEndpoints;
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return null;
        }
    }

    public static List<String> ListSlaActions (){
        try{
            List<String> apiEndpoints = new ArrayList<>();

            apiEndpoints.add("/SLAAPI/IFranchiseFeature/AddFranchise");
            apiEndpoints.add("/SLAAPI/IPartnerFeature/AddPartner");
            apiEndpoints.add("/CSAPI/ICountryManagementFeature/ListCountry");
            apiEndpoints.add("/CSAPI/IStateManagementFeature/GetStateByCountryId");
            apiEndpoints.add("/CSAPI/ICityManagementFeature/GetCityByStateId");

            return apiEndpoints;
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return null;
        }
    }

}
