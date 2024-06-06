package com.icad.shop.retailservice.constant.customer;

public class CustomerConstant {
    public static class ResponseMessage {
        public static final String SUCCESS_ADD_DATA = "Successfully Added Customer Data";
        public static final String SUCCESS_EDIT_DATA = "Successfully Edit the Customer Data";
        public static final String SUCCESS_DELETE_DATA = "Successfully Delete Customer Data";

        public static final String FAILED_ADD_DATA_ALREADY_EXIST = "The Customer Data Already Exist";
        public static final String FAILED_ADD_DATA = "Failed to Added Customer Data";
        public static final String FAILED_EDIT_DATA = "Failed to Edit the Customer Data";
        public static final String FAILED_UPDATE_DATA_CUSTOMER_CANNOT_BE_NULL = "Failed to Update the Customer Data. Customer Data Cannot be Null";
        public static final String FAILED_DELETE_DATA = "Failed to Delete Customer Data";
        public static final String FAILED_DELETE_DATA_DOES_NOT_EXIST = "The Customer Data Does Not Exist or Inactive";

        private ResponseMessage() {
        }
    }

    private CustomerConstant() {
    }
}
