package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.ANDROID;
import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.IOS;

// 类型安全
public class PhoneType {
    @Type
    String mType;

    @StringDef({ANDROID, IOS}) // 限定值
    @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在.class 文件
    public @interface Type {//接口，定义新的注解类型
    }

    public @Type
    String getType() {
        return mType;
    }

    public void setType(@Type String type) {
        mType = type;
    }

    public PhoneType(@Type String type) {
        mType = type;
    }

    public static PhoneType create(@Type String type) {
        PhoneType color = new PhoneType(type);
        getValues().add(color);
        return color;
    }

    private static List<PhoneType> mValues;

    public static List<PhoneType> getValues() {
        if (null == mValues) {
            mValues = new ArrayList<>();
        }
        return mValues;
    }
}