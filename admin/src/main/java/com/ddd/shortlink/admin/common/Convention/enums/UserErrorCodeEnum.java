package com.ddd.shortlink.admin.common.Convention.enums;

import com.ddd.shortlink.admin.common.Convention.errorcode.IErrorCode;

/**
 * @param
 * @return
 **/
public enum UserErrorCodeEnum implements IErrorCode {

    USER_NULL("b000200", "user is not exist");

    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
