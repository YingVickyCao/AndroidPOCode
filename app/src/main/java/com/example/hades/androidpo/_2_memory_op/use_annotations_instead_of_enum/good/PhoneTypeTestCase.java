package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants;


public class PhoneTypeTestCase {
    public void test() {
        PhoneType type = new PhoneType(ColorConstants.ANDROID);
        System.out.println(type.getType());    // ANDROID

//        color.setType("A");                   // ERROR: must be none of
//        System.out.println(type.getType());

        type.setType(ColorConstants.IOS);
        System.out.println(type.getType());    // IOS

//        PhoneType type2 = new PhoneType("B");  // ERROR: must be none of
//        System.out.println(type2.getType());

//        PhoneType type3 = PhoneType.create("C");    // ERROR: must be none of
//        System.out.println(type3);


        PhoneType type4 = new PhoneType(ColorConstants.IOS);
        System.out.println(type4.getType());    // ANDROID
    }
}
