package com.capstone.newtral.common;

public enum CommonResponse {


    SUCCESS("Success"), FAIL("Fail");

    String msg;

    CommonResponse(String msg){
        this.msg = msg;
    }



}
