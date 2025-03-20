package com.gabr.pos.Services;

import java.time.LocalDateTime;

public class exceptionHandling {
    public static void LOG_EXCEP(String className, String method, Exception ex) {
        System.out.println(LocalDateTime.now() + " #Class : " + className);
        System.out.println("                        #Method : " + method);
        System.out.println("                        #Message: " + ex.getMessage());
        System.out.println("                        #Cause: " + ex.getCause());
        System.out.println("                        #Trace: ");
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            System.out.println("                      - " + trace[0]);
        }
        System.out.println("          ************************************************************                 \n\n");
    }

    public static void LOG_ERROR(String className, String method, String msg) {
        System.out.println(LocalDateTime.now() + " #Class : " + className);
        System.out.println("                        #Method : " + method);
        System.out.println("                        #Message: " + msg);
        System.out.println("          ************************************************************                 \n\n");
        System.out.println("");
    }

    public static void LOG_EXCEP_PARAM(String className, String method, Exception ex,String param) {
        System.out.println(LocalDateTime.now() + " #Class : " + className);
        System.out.println("                        #Method : " + method);
        System.out.println("                        #Message: " + ex.getMessage());
        System.out.println("                        #Cause: " + ex.getCause());
        System.out.println("                        #param: " + param);
        System.out.println("                        #Trace: ");
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            System.out.println("                      - " + trace[0]);
        }
        System.out.println("          ************************************************************                 \n\n");
    }
}
