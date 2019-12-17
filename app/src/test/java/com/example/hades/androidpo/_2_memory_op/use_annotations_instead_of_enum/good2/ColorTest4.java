package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good2;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.dev_use.Color;

import org.junit.Test;

public class ColorTest4 {
    @Test
    public void getType() throws Exception {
        // 类型安全
        Color color = Color.BLUE;
        System.out.println(color.getType());

        color.setType("A");
        System.out.println(color.getType()); // A
    }
}