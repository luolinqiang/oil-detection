package com.oil.detection.util;

import java.util.Random;

public final class VerifyCodeUtils {

    private static final char[] codeArrays = {'1','2','3','4','5','6','7','8','9','0'};
    private static final Random random = new Random();

    public static String generateCode(int num){
        if(num<=0)throw new IllegalArgumentException("error:num->"+num);
        StringBuilder sb = new StringBuilder(num);
        for(int i=0;i<num;i++){
            sb.append(codeArrays[random.nextInt(codeArrays.length)]);
        }
        return sb.toString();
    }


    public static void main(String[] args){
        System.out.println(generateCode(5));
    }


}
