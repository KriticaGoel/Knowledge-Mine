package designPattern.structural.adapter;

public class BasicThirdPartyConfigMapper implements ThirdPartyConfigMapper {

    @Override
    public SystemConfiguration mapToSystemConfiguration(String rawPayload) {
        if (rawPayload == null) {
            rawPayload = "";
        }
        int systemName = parseInt(rawPayload, "systemName", 0);
        int organization = parseInt(rawPayload, "organization", 0);
        double scheduleEnable = parseDouble(rawPayload, "scheduleEnable", 0.0);
        String scheduleTime = parseString(rawPayload, "scheduleTime", "00:00");
        return new SystemConfiguration(systemName, organization, scheduleEnable, scheduleTime);
    }

    private int parseInt(String payload, String key, int fallback) {
        String value = parseToken(payload, key);
        if (value == null || value.isBlank()) {
            return fallback;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }

    private double parseDouble(String payload, String key, double fallback) {
        String value = parseToken(payload, key);
        if (value == null || value.isBlank()) {
            return fallback;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }

    private String parseString(String payload, String key, String fallback) {
        String value = parseToken(payload, key);
        if (value == null || value.isBlank()) {
            return fallback;
        }
        return value;
    }

    private String parseToken(String payload, String key) {
        String quotedKey = "\"" + key + "\"";
        int keyIndex = payload.indexOf(quotedKey);
        if (keyIndex < 0) {
            return null;
        }
        int colonIndex = payload.indexOf(':', keyIndex + quotedKey.length());
        if (colonIndex < 0) {
            return null;
        }
        int valueStart = colonIndex + 1;
        while (valueStart < payload.length() && Character.isWhitespace(payload.charAt(valueStart))) {
            valueStart++;
        }
        if (valueStart >= payload.length()) {
            return null;
        }
        char firstChar = payload.charAt(valueStart);
        if (firstChar == '"') {
            int endQuote = payload.indexOf('"', valueStart + 1);
            if (endQuote < 0) {
                return null;
            }
            return payload.substring(valueStart + 1, endQuote);
        }
        int endIndex = valueStart;
        while (endIndex < payload.length()) {
            char current = payload.charAt(endIndex);
            if (current == ',' || current == '}' || Character.isWhitespace(current)) {
                break;
            }
            endIndex++;
        }
        if (endIndex <= valueStart) {
            return null;
        }
        return payload.substring(valueStart, endIndex);
    }
}

