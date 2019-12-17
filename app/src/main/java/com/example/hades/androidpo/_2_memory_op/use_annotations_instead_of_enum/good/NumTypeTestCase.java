package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good;

public class NumTypeTestCase {
    public void test() {
//        NumType value = new NumType();// ERROR: private access

        NumType value2 = NumType.ONE;
        System.out.println(value2.getType()); // ONE

        value2.setType(NumType.TWO);
        System.out.println(value2.getType()); // TWO

//        color2.setType("A");    // ERROR
    }
}
