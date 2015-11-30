package com.leychina.value;

/**
 * Created by yuandunlong on 10/26/15.
 */
public class Constant {

    public static final String DOMAIN = "http://www.leychina.com";


    public static interface API_PATH {

        String GET_CHALLENGE = DOMAIN + "/api/private/user/get_challenge";
        String GET_ACESS_TOKEN = DOMAIN + "/api/private/user/get_access_token";
        String  SEND_SMS_CODE = DOMAIN + "/api/public/user/send_sms_code";
        String SIGN_UP=DOMAIN+"/api/public/user/sign_up";
        String GET_USER_INFO=DOMAIN+"/api/private/user/get_user_info?v=1&token=";
        String GET_PROJECTS_BY_PAGE=DOMAIN+"/api/public/project/get_projects_by_page";
        String GET_PAYBACKS_BY_PROJECT_ID=DOMAIN+"/api/public/payback/get_paybacks_by_project_id";

        String GET_PROJECT_POST=DOMAIN+"/api/public/common/get_project_post";

        String GET_ARTIST_BY_PAGE=DOMAIN+"/api/public/artist/get_artist_by_page";

        String GET_ARTIST_CATEGORY=DOMAIN+"/api/public/common/get_artist_category";

        String GET_ACTIVITY_NOTICE=DOMAIN+"/api/public/common/get_activity_notice";

        String GET_PROJECT_BY_ID=DOMAIN+"/api/public/project/get_project_by_id";

        String ADD_USER_ADDRESS=DOMAIN+"/api/private/user/add_user_address?token=";

        String GET_USER_ADDRESSES=DOMAIN+"/api/private/user/get_user_addresses?token=";

        String GET_USER_DEFAULT_ADDRESS=DOMAIN+"/api/private/user/get_user_default_address?token=";

        String SUBMIT_ORDER=DOMAIN+"/api/private/order/submit_order?token=";

    }
}
