package com.knoldus.stocks.util;

public class CommonUtil {

    public static int getIntStatus(String statusCodeStr) {
        try {
            return Integer.parseInt(statusCodeStr);
        } catch (Exception nfe) {
            return 503;
        }
    }

}
