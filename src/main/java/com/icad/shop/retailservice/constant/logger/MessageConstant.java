package com.icad.shop.retailservice.constant.logger;

public class MessageConstant {

    public static class SuccessResponse {
        public static final String RETRIEVE_DATA = "Successfully Retrieve Data";
        public static final String UPDATE_DATA = "Successfully Update Data";
        public static final String CHECK_CUSTOMER_DATA = "Successfully Check Customer Data";

        private SuccessResponse() {
        }
    }

    public static class FailedResponse {
        public static final String RETRIEVE_DATA = "Failed to Retrieve Data";
        public static final String UPDATE_DATA = "Failed to Update Data";
        public static final String CHECK_CUSTOMER_DATA = "Failed to Check Customer Data";

        private FailedResponse() {
        }
    }

    public static class CommonResponse {
        public static final String DEFAULT_RESPONSE = "Online Retail Service Response";

        private CommonResponse() {}
    }

    private MessageConstant() {}
}
