package ru.filenko.servicedataentities.common;

import java.util.Map;

public class ParserHeaders {
    public static String getHeaderValue(Map<String, String> map, String key) {
        if (map.containsKey(key)) {
            String headerValue = extractHeaderValue(map.get(key));
            if (!headerValue.isBlank()) {
                return headerValue;
            }
            return null;
        }
        return null;
    }

    public static String extractHeaderValue(String valueHeader) {
        int equalsIndex = valueHeader.indexOf('=');
        return valueHeader.substring(equalsIndex + 1);
    }
}
