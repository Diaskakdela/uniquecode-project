package kz.geekpartners.uniquecodeservice.util;

import org.springframework.stereotype.Component;

@Component
public class UniqueCodeGenerator {

    public String generateNewCode(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();

        int maxNine = str.length() / 2;
        int currentNinesCount = 0;
        int currentZCount = 0;

        for (int i = 0; i < str.length(); i++) {
            if (chars[i] == '9') {
                currentNinesCount++;
            } else if (chars[i] == 'z') {
                currentZCount++;
            }
        }

        if (currentNinesCount == maxNine) {
            if (currentZCount == (str.length() / 2)) {
                stringBuilder.append("a0".repeat((str.length() / 2) + 1));
                return stringBuilder.toString();
            }
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(chars[i])) {
                    chars[i] = '0';
                }
            }
            for (int i = chars.length - 1; i >= 0; i--) {
                if (!Character.isDigit(chars[i])) {
                    if (chars[i] != 'z') {
                        chars[i] = (char) (chars[i] + 1);
                        return stringBuilder.append(chars).toString();
                    } else {
                        chars[i] = 'a';
                    }
                }
            }
        }

        for (int i = chars.length - 1; i >= 0; i--) {
            if (Character.isDigit(chars[i]) && chars[i] != '9') {
                chars[i] = (char) (chars[i] + 1);
                return stringBuilder.append(chars).toString();
            } else if (chars[i] == '9') {
                chars[i] = '0';
            }
        }
        return "";
    }
}
