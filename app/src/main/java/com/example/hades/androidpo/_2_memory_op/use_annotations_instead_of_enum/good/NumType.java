package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants;

// 类型安全
public class NumType {
    private String mType;

    //这是一个私有的构造器，意味着在这个类的外部没有办法去实例化一个该类的对象。
    // 类型安全
    private NumType(String value) {
        this.mType = value;
    }

    public String getType() {
        return mType;
    }

//    public void setType(String type) {
//        mType = type;
//    }

    public void setType(NumType type) {
        if (null != type) {
            mType = type.getType();
        } else {
            mType = null;
        }
    }

    // 实例化该类的对象，供外部类使用。
    public static final NumType ONE = new NumType(ColorConstants.ONE);
    public static final NumType TWO = new NumType(ColorConstants.TWO);
}