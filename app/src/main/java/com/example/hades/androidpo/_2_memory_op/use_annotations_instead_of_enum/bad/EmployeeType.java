package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad;

import java.util.HashMap;
import java.util.Map;

/**
 * Dev use
 * 类型安全
 */
public enum EmployeeType {
    VENDOR("VENDOR"), DIRECT("DIRECT");

    private String mType;

    EmployeeType(String value) {
        this.mType = value;
    }

    public String getType() {
        return mType;
    }

    @Override
    public String toString() {
        return mType;
    }

    private static Map<String, EmployeeType> mMap = new HashMap<>();

    public static EmployeeType get(String name) {
        return mMap.get(name);
    }

    static {
        mMap.put(VENDOR.getType(), VENDOR);
        mMap.put(DIRECT.getType(), DIRECT);
    }
}