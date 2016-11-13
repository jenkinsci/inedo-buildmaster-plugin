package com.inedo.http;

public class HttpEasyLogWriter {
    LogWriter logWriter;
    
    public HttpEasyLogWriter(LogWriter logWriter) {
        this.logWriter = logWriter;
        
        if (HttpEasyDefaults.getDefaultLogWriter() != null) {
            this.logWriter = HttpEasyDefaults.getDefaultLogWriter();
        }
    }
    
    public void log(final String message, Object... args) {
        if (logWriter != null) {
            String msg = message.replace("{}", "%s");
            
            logWriter.info(String.format(msg, args));
        } else {
            HttpEasy.LOGGER.trace(message);
        }       
    }
}
