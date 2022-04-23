package com.example.androidtest.tools;

import java.io.IOException;

public class Suffix {
    String val1;
    int val2;

    public Suffix(String val1, int val2) {
        this.val1=val1;
        this.val2=val2;
    }

    public String getSuffix() throws Exception {
        if (this.val2==0){
            throw new IOException("The second input should be bigger than zero");
        }

        if (this.val2>this.val1.length()){
            throw new IOException("You cannot give a number that exceeds the length of the input one!");
        }

        if (this.val2<0){
            throw new IOException("The second input should be a natural number");
        }

        return val1.substring(val1.length()-this.val2);
    }
}
