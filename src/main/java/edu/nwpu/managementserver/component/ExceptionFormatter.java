package edu.nwpu.managementserver.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ExceptionFormatter {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public String formatException(Exception e) {
        return formatException(e, e.getMessage(), true);
    }

    @SneakyThrows
    public String formatException(Exception e, String message, boolean enableStackTrace) {
        ExceptionInfo info = new ExceptionInfo();
        info.setExceptionName(e.getClass().toString());
        info.setMessage(message == null ? e.getMessage() : message);
        List<String> stackTraceList = new ArrayList<>();
        if (enableStackTrace) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement ele : stackTrace) {
                stackTraceList.add(MessageFormat.format("at {0}.{1}({2}:{3})",
                        ele.getClassName(), ele.getMethodName(), ele.getFileName(), ele.getLineNumber()));
            }
        }
        info.setStackTrace(stackTraceList);
        return objectMapper.writeValueAsString(info);
    }

    @Data
    public static class ExceptionInfo {
        private String exceptionName;
        private String message;
        private List<String> stackTrace;
    }
}

