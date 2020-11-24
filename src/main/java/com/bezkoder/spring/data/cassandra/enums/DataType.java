package com.bezkoder.spring.data.cassandra.enums;

public enum  DataType {
    bean_content_news(101),

    bean_content_news_comment(102),

    bean_facebook_profile(103),

    bean_content_facebook_post(104),

    bean_content_facebook_comment(105),

    bean_content_youtube(106),

    bean_content_google( 107),

    bean_content_book(108),

    bean_content_manga( 109),

    bean_others( 110);

    private final Integer typeId;

    DataType( Integer typeId) {
        this.typeId= typeId;
    }

    public static DataType parse(int typeId){
        for (DataType userPackageStatus : DataType.values()){
            if (userPackageStatus.typeId == typeId){
                return userPackageStatus;
            }
        }
        throw new IllegalArgumentException("Unknown data type");
    }

    public Integer getTypeId() {

        return typeId;
    }
}
