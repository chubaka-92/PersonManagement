package com.example.personmenegement;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMaskingLayout extends PatternLayout {
    public static final String MASK = "*******";
    private Pattern multilinePattern;
    private List<String> maskPatterns = new ArrayList<>();

    public void addMaskPattern(String maskPattern) {
        maskPatterns.add(maskPattern);
        multilinePattern = Pattern.compile(
                String.join("|", maskPatterns),
                Pattern.MULTILINE
        );
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(sb);
        while (matcher.find()) {
            if (matcher.group().contains("salary")
                    || matcher.group().contains("email")
                    || matcher.group().contains("password")) {
                maskSalary(sb, matcher);
            }
        }
        return sb.toString();
    }

    private void maskSalary(StringBuilder sb, Matcher matcher) {
        String targetExpression = matcher.group();
        String[] split = null;
        if (targetExpression.contains("=")) {
            split = targetExpression.split("=");
        } else {
            split = targetExpression.split(":");
        }

/*        String pan = split[1];
        String maskedPan = getGenerateMaskedPan(pan);*/
        int start = matcher.start() + split[0].length() + 1;
        int end = matcher.end();
        sb.replace(start, end, MASK);
        //sb.replace(start, end, maskedPan);
    }

/*    private String getGenerateMaskedPan(String pan) {
        return pan.replaceAll(pan, MASK);
    }*/
}
