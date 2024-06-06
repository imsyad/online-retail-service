package com.icad.shop.retailservice.constant.item;

public class ItemConstant {
    public static class ResponseMessage {
        public static final String SUCCESS_ADD_DATA = "Successfully add the item data";
        public static final String SUCCESS_EDIT_DATA = "Successfully edit the item data";
        public static final String FAILED_ADD_DATA_ALREADY_EXIST = "The item already exist";
        public static final String FAILED_EDIT_DATA_DOES_NOT_EXIST = "The item data does not exist";
        public static final String FAILED_EDIT_DATA_NAME_OR_CODE_ALREADY_USED = "The item name or/and code already used";
        public static final String SUCCESS_DELETE_DATA = "Successfully delete the item data";
        public static final String FAILED_DELETE_DATA_DOES_NOT_EXIST = "The item does not exist or already deleted";

        private ResponseMessage() {
        }
    }

    private ItemConstant() {
    }
}
