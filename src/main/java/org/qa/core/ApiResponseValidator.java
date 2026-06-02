
// Валидация ответов API
package org.qa.core;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiResponseValidator {

    public static boolean isValidTraceId(String traceId) {

        String uuidRegex = "(?i)[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
        return traceId != null && traceId.matches(uuidRegex);
    }

    public static String extractOrderId(String payload) {

        if (payload == null || payload.isEmpty()) {
            return "UNKNOWN";
        }

        String orderRegex = "orderId:\\s*(ORD-\\d{5}-[A-Z]{2})";

        Pattern pattern = Pattern.compile(orderRegex);
        Matcher matcher = pattern.matcher(payload);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "UNKNOWN";
    }
}