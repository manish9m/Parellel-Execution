package com.monys.parellel.execution.test;

public class Class3 {

   public  int method3(int x) {
        System.out.println("Calling: "+this.getClass().getCanonicalName() +" : "+"method3");
        System.out.println("Input is:"+x);
        return 3;
    }
    
}
