package com.example.english_test.utils;

import java.util.Random;

public class CaptchaProduce {
    public String captcha(){
        Random random = new Random();
        String s="1234567890qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
        String s1="";
        for(int i=0;i<5;i++)
        {
            int a=random.nextInt(62);
            s1+=s.charAt(a);
        }
        return s1;
    }
}
