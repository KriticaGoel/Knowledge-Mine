package designPattern.creational.singleton.loggerDemo.advance.Solution1;

import java.util.HashMap;
import java.util.Map;

public class LogFactoryImpl implements LogFactory {

    Map<String, LogAppender> logMap = new HashMap<>();

    LogFactoryImpl(String[] fileNames) {
        for (String fileName : fileNames) {
            logMap.put("file", new FileAppender(fileName));
        }

        logMap.put("console", new ConsoleAppender());
    }

    @Override
    public LogAppender getAppender(String type) {
        if(logMap.containsKey(type)) {
            return logMap.get(type);
        }

        throw new IllegalArgumentException("Invalid type");
    }
}
