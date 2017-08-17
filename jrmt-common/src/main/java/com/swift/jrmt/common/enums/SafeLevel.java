package com.swift.jrmt.common.enums;


/**
 * @Description: 安全级别
 * @Company: SFXD
 */
public enum SafeLevel implements BaseEnum<SafeLevel> {
	LOW("低"),
    NORMAL("一般"),
    HIGH("高"),
    ERROR("非正常")
    ;
    
    private String chineseName;

    private SafeLevel(String chineseName){
        this.chineseName = chineseName;
    }
    
    @Override
    public String getName() {return name();}
    
    @Override
    public int getOrdinal() {return ordinal();}
    
    @Override
    public String getChineseName() {
        return chineseName;
    }

    
    @Override
    public SafeLevel parsing(String element) {
        try {
            return SafeLevel.valueOf(element.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
