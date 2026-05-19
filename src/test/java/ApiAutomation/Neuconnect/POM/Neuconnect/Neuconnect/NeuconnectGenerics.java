package ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect;

import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;


public class NeuconnectGenerics {
    public static int listCountOfAllApis(String token) {
        try {
            int UserCount = Integer.parseInt(ApiResponseUtils.getApiResponseDataDetails(NeuconnectFunction.ListUsers(token), "totalCount"));
           /* int departmnetCount = Integer.parseInt(ApiResponseUtils.getApiResponseDataDetails(RFIDFunctions.ListDepartment(token), "totalDepartments"));
            int locationCount = Integer.parseInt(ApiResponseUtils.getApiResponseDataDetails(RFIDFunctions.ListLocation(token), "totalCount"));
            int catgeoryCount = Integer.parseInt(ApiResponseUtils.getApiResponseDataDetails(RFIDFunctions.ListCategory(token), "totalCount"));
            int CostCenterCount = Integer.parseInt(ApiResponseUtils.getApiResponseDataDetails(RFIDFunctions.ListCostCenter(token), "totalCount"));
            int subcategoryCount = Integer.parseInt(ApiResponseUtils.getApiResponseDataDetails(RFIDFunctions.ListSubCategory(token), "totalCount"));*/


            // System.out.println(departmnetCount);

            return UserCount;

        } catch (Exception e) {
            PrintUtil.PrintSuccessLog(e.toString());
            throw new RuntimeException(e);
        }
    }
}


