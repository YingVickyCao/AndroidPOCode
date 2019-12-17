package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good;

import org.junit.Test;


public class PhoneTypeTest {

    @Test
    public void getType() {
        new PhoneTypeTestCase().test();
    }
}