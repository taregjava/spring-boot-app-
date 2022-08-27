package com.tareg.entity.error;

import com.tareg.constant.ConstantValues;

public enum Error {


    ERROR_CREATE(ConstantValues.CREATE_ERROR_CODE, ConstantValues.CREATE_ERROR),
    ERROR_UPDATE(ConstantValues.UPDATE_ERROR_CODE, ConstantValues.UPDATE_ERROR),
    ERROR_DELETE(ConstantValues.DELETE_ERROR_CODE, ConstantValues.DELETE_ERROR),
    ERROR_NOT_EXIST(ConstantValues.RECORD_DOES_NOT_EXIST_CODE, ConstantValues.RECORD_DOES_NOT_EXIST),
    ERROR_EXIST(ConstantValues.RECORD_ALREADY_EXISTS_CODE, ConstantValues.RECORD_ALREADY_EXISTS),
    ERROR_NO_RECORD(ConstantValues.NO_RECORDS_FOUND_CODE, ConstantValues.NO_RECORDS_FOUND),
    ERROR_INVALID_REQ(ConstantValues.INVALID_REQUEST_CODE, ConstantValues.INVALID_REQUEST),
    ERROR_INVALID_JSON(ConstantValues.INVALID_JSON_CODE, ConstantValues.INVALID_JSON),
    ERROR_GENERAL_MSG(ConstantValues.GENERAL_ERROR_CODE, ConstantValues.GENERAL_ERROR);

    private final String code;
    private final String description;

    private Error(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

}