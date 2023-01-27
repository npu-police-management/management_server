package com.nwpu.managementserver.util;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
public class PAdminNameGenerateUtil {

    private static final int Modular = 10000;

    private static long timestamp = 0;

    public static synchronized String nextName(String prisonName) {

        long currentTime = System.currentTimeMillis();
        if (currentTime == timestamp) {
            currentTime++;
        }
        timestamp = currentTime;
        long n = currentTime % Modular;
        return prisonName + n;
    }
}
