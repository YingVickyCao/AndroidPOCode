package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad;

public class ColorTestCase {
    public void test() {
//        Color color = new Color(); // ERROR: private access

        Color colorType = Color.BLUE;
        System.out.println(colorType.getType()); // BLUE

        colorType.setType(Color.GREEN.name());
        System.out.println(colorType.getType()); // GREEN

        // Type is not safe
        colorType.setType("B");
        System.out.println(colorType.getType()); // B
    }
}
