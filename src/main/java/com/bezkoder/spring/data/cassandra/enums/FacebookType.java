package com.bezkoder.spring.data.cassandra.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum FacebookType {

    GROUP(1,"Group"),
    PROFILE(0,"Profile"),
    PAGE(2,"Page");
    private int value;
    private String sValue;

    FacebookType(int value, String sValue){
        this.value = value;
        this.sValue = sValue;
    }

    public static FacebookType parse(int value){
        for (FacebookType businessPackageStatus : FacebookType.values()){
            if (businessPackageStatus.value == value){
                return businessPackageStatus;
            }
        }
        throw new IllegalArgumentException("Unknown facebookType type");
    }

    public static FacebookType parse(String sValue){
        for (FacebookType businessPackageStatus : FacebookType.values()){
            if (businessPackageStatus.sValue.equalsIgnoreCase(sValue)){
                return businessPackageStatus;
            }
        }
        throw new IllegalArgumentException("Unknown facebookType type");
    }

    public int getValue() {
        return value;
    }

    public String getsValue() {
        return sValue;
    }
}
