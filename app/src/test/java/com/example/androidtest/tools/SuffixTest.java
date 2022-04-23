package com.example.androidtest.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuffixTest {


    @Test
    public void suffixTestValidTestCase() {
        Suffix s1= new Suffix("abcd",2);
        try {
        assertEquals("cd",s1.getSuffix());
        }
        catch (Exception e){
            fail();
        }
    }
    @Test
    public void suffixTestSecondInputNegative() {
        Suffix s2= new Suffix("abcd",-1);
        try {
            s2.getSuffix();
            fail();
        }
        catch (Exception e){
            assertEquals("The second input should be a natural number",e.getMessage());
        }
    }
    @Test
    public void suffixTestSecondInputZero() {
        Suffix s3= new Suffix("abcd",0);
        try {
            s3.getSuffix();
            fail();
        }
        catch (Exception e){
            assertEquals("The second input should be bigger than zero",e.getMessage());
        }
    }
    @Test
    public void suffixTestSecondInputLargerThanFirstInputLength() {
        Suffix s4= new Suffix("abcd",10);
        try {
            s4.getSuffix();
            fail();
        }
        catch (Exception e){
            assertEquals("You cannot give a number that exceeds the length of the input one!",e.getMessage());
        }
    }
}
