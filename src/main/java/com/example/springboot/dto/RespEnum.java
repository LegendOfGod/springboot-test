package com.example.springboot.dto;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lqb
 * @date 2022/1/14 11:42
 */
public enum RespEnum {
    SUCESS("000","成功"),
    FAIL("111","失败")
    ;


    private String code;
    private String msg;

    RespEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static List<String> getCodes(){
        List<String> codes = new ArrayList<>();
        RespEnum[] enums = RespEnum.values();
        for (RespEnum anEnum : enums) {
            codes.add(anEnum.code);
        }
        return codes;
    }

}
