package com.gabr.pos.Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Ahmed Gabr
 */
public class logging {
    // Logger instance for this class
    // Define loggers
    private static final Logger logger = LogManager.getLogger(logging.class);
    private static final Logger sqlLogger = LogManager.getLogger("SQL");
    private static final Logger errorLogger = LogManager.getLogger("errors");

    // Define logger type
    public static String INFO = "info";
    public static String ERROR = "error";
    public static String WARN = "warn";
    public static String DEBUG = "debug";

    /**
     * Custom log method to handle the message and parameters before logging
     *
     * @param level   The log level (e.g., "info", "debug", "error")
     * @param msg     The log message
     * @param params  The parameters to be inserted into the message
     */
    public static void logMessage(String level,String className,String method,String msg, Object... params) {

        StringBuilder MSG = new StringBuilder();
        boolean newLine = false ;
        if(level.toLowerCase().equals("error")){
            newLine = true ;
        }
        // Perform any pre-processing or manipulation on the message here
        MSG.append("#Class   : "+className+"\n");
        MSG.append("#Method  : "+method+"\n");
        MSG.append("#Message : "+processMessage(msg,params)+"\n");
        String processedMessage = "#Class : "+className +"\n"+
                "                                                        #Method : "+method+"\n"+
                "                                                        #Message : "+processMessage(msg,newLine, params);
    //    String processedMessage = processMessage(msg, params);
        // Check if there's an exception (Throwable) passed as an argument
//        Throwable exception = (params.length > 0 && params[params.length - 1] instanceof Throwable)
//                ? (Throwable) params[params.length - 1] : null;

        // Log based on the provided log level
        switch (level.toLowerCase()) {
            case "info":
                logger.info(MSG);
                break;
            case "debug":
                sqlLogger.debug(MSG);
                break;
            case "error":
            //    if (exception != null) {
            //        errorLogger.error(processedMessage, exception);  // Log message + stack trace
            //    } else {
                    errorLogger.error(MSG);  // Log message only (no exception)
            //    }
                break;
            case "warn":
                logger.warn(MSG);
                break;
            default:
                logger.info(MSG);  // Default to INFO level if invalid level
                break;
        }
    }
    public static void logExpWithMessage(String level,String className,String method,Throwable ex ,String msg, Object... params) {
        StringBuilder MSG = new StringBuilder();
        boolean newLine = false ;
        if(level.toLowerCase().equals("error")){
            newLine = true ;
        }
        // Perform any pre-processing or manipulation on the message here
        MSG.append("#Class   : "+className+"\n");
        MSG.append("#Method  : "+method+"\n");
        MSG.append("#Message : "+processMessage(msg,params)+"\n");
        if (ex != null){
            MSG.append("#EXCEPTION : "+ex.getMessage()+"\n");
            MSG.append("#EXCEPTION : "+ex.getCause()+"\n");
        }
        String processedMessage = "#Class : "+className +"\n"+
                "                                                        #Method : "+method+"\n"+
                "                                                        #Message : "+processMessage(msg,newLine, params);
        //    String processedMessage = processMessage(msg, params);
        // Check if there's an exception (Throwable) passed as an argument
//        Throwable exception = (params.length > 0 && params[params.length - 1] instanceof Throwable)
//                ? (Throwable) params[params.length - 1] : null;

        // Log based on the provided log level
        switch (level.toLowerCase()) {
            case "info":
                logger.info(MSG);
                break;
            case "debug":
                sqlLogger.debug(MSG);
                break;
            case "error":
                //    if (exception != null) {
                //        errorLogger.error(processedMessage, exception);  // Log message + stack trace
                //    } else {
                errorLogger.error(MSG);  // Log message only (no exception)
                //    }
                break;
            case "warn":
                logger.warn(MSG);
                break;
            default:
                logger.info(MSG);  // Default to INFO level if invalid level
                break;
        }
    }
    public static void logException(String level,String className,String method,Throwable ex) {
        StringBuilder MSG = new StringBuilder();
        boolean newLine = false ;
        if(level.toLowerCase().equals("error")){
            newLine = true ;
        }
        // Perform any pre-processing or manipulation on the message here
        MSG.append("#Class   : "+className+"\n");
        MSG.append("#Method  : "+method+"\n");
        if (ex != null){
            MSG.append("#EXCEPTION : "+ex.getMessage()+"\n");
            MSG.append("#EXCEPTION : "+ex.getCause()+"\n");
        }

        switch (level.toLowerCase()) {
            case "info":
                logger.info(MSG);
                break;
            case "debug":
                sqlLogger.debug(MSG);
                break;
            case "error":
                errorLogger.error(MSG);  // Log message only (no exception)
                break;
            case "warn":
                logger.warn(MSG);
                break;
            default:
                logger.info(MSG);  // Default to INFO level if invalid level
                break;
        }
    }


    /**
     * Custom method to process the message and parameters (e.g., formatting)
     * @param msg The log message
     * @param params The parameters to be inserted
     * @return The formatted message
     */
    private static String processMessage(String msg, Object... params) {
//        if (params != null && params.length > 0) {
//            // Custom processing logic (e.g., replace placeholders in the message)
//            return String.format(msg, params);
//        }
        if (params != null && params.length > 0) {
//            if(newLine){
//                String formattedMessage = msg;
//                for (Object param : params) {
//                    formattedMessage = formattedMessage.replaceFirst("%s", param.toString() + "\n");
//                }
//                return formattedMessage;
//            }else {
                return String.format(msg, params);
//            }

        }
        return msg;
    }

}
