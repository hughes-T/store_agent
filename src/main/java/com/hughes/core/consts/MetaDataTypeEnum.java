package com.hughes.core.consts;

public enum MetaDataTypeEnum {

    HEADER("1"), //请求头
    MAIN_BODY("2"), //请求正文
    FORM_MULTIPART("3"); //FORM表单 multipart/form-data

    private String type;

    MetaDataTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
