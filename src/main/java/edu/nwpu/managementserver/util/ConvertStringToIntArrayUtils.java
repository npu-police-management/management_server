package edu.nwpu.managementserver.util;

/**
 * @author GengXuelong
 * @version 1.0
 * @Mail 3349495429@qq.com
 * @time 2023/2/8 17:10
 * @className CommonUitls
 * @description:
 */
public class ConvertStringToIntArrayUtils {
    public static int[] convertStringToIntArray(String intsString){
        int[] ints = new int[6];
        intsString = intsString.substring(1,intsString.length()-1);
        String[] split = intsString.split(",");
        for (int i = 0; i < 6; i++) {
            ints[i] =Integer.parseInt(split[i]);
        }
        return ints;
    }
}
