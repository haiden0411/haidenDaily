package com.huawei.springboot.exception;


public class BusinessException extends RuntimeException{

    private final int messageCode;

    private final String messageDefault;

    public BusinessException(int messageCode,String message ) {
        super(message);
        this.messageCode = messageCode;
        this.messageDefault = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getMessageDefault() {
        return messageDefault;
    }
}
