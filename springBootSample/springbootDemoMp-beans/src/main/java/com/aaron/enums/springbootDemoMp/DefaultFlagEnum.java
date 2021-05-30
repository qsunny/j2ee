package com.aaron.enums.springbootDemoMp;


import java.util.HashMap;
import java.util.Map;

/**
 * <p>默认值枚举类</p>
 * <p>Author: Aaron</p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date：2020/05/30</p>
 * <p>Modified By：</p>
 * <p>Modified Date:</p>
 * <p>Copyright (c) 2021 ~ 2025 版权所有</p>
 */
public enum DefaultFlagEnum {
    //默认值：10默认，20非默认
    DEFAULT(10,"default","默认"),
    NON_DEFAULT(20,"Non-default","非默认");
    //状态码
    private int code;
    //状态英文
    private String descEn;
    //状态中文
    private String descCn;
    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<Integer, DefaultFlagEnum> lookup = new HashMap<>();
    static {
        for (DefaultFlagEnum lte : DefaultFlagEnum.values()) {
            lookup.put(lte.getCode(), lte);
        }
    }
    private DefaultFlagEnum(int code, String descEn, String descCn) {
        this.code = code;
        this.descEn = descEn;
        this.descCn = descCn;
    }
    public int getCode() {
        return code;
    }
    public String getDescEn() {
        return descEn;
    }
    public String getDescCn() {
        return descCn;
    }
    public static DefaultFlagEnum get(int code) {
        return lookup.get(code);
    }
}