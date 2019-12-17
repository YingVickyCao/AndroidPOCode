package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad;

import junit.framework.Assert;

public class EmployeeTypeTestCase {
    public void test() {
//        EmployeeType employeeType = new EmployeeType(); // ERROR

        EmployeeType employeeType = EmployeeType.get("A"); // null
        Assert.assertNull(employeeType);

        EmployeeType employeeType2 = EmployeeType.get(EmployeeType.VENDOR.name());
        System.out.println(employeeType2.getType());  // VENDOR

//        EmployeeType employeeType3 = new EmployeeType('M'); // ERROR: private access

        EmployeeType employeeType4 = EmployeeType.DIRECT;
        System.out.println(employeeType4.getType());  // DIRECT
    }
}
