import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;
import java.util.function.*;

public class Solution {

    private static void part1(String[] passports) {
        List<String> requiredFields = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        int count = 0;
        for (String pass : passports) {
            boolean valid = true;
            for (String f : requiredFields) {
                if (!pass.contains(f + ":")) {
                    valid = false;
                }
            }
            if (valid) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isValid(String pass, String pattern, Predicate<String> validCondition) {
        Matcher m = Pattern.compile(pattern).matcher(pass);
        if (!m.find()) {
            return false;
        } else {
            String valStr = m.group(1);
            return validCondition.test(valStr);
        }
    }

    private static void part2(String[] passports) {

        int count = 0;
        for (String pass : passports) {
            // byr (Birth Year) - four digits; at least 1920 and at most 2002.
            boolean byrValid = isValid(pass, "\\bbyr:(\\d{4})\\b", valStr -> {
                int val = Integer.parseInt(valStr);
                return val >= 1920 && val <= 2002;
            });

            // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
            boolean iyrValid = isValid(pass, "\\biyr:(\\d{4})\\b", valStr -> {
                int val = Integer.parseInt(valStr);
                return val >= 2010 && val <= 2020;
            });

            // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
            boolean eyrValid = isValid(pass, "\\beyr:(\\d{4})\\b", valStr -> {
                int val = Integer.parseInt(valStr);
                return val >= 2020 && val <= 2030;
            });

            // hgt (Height) - a number followed by either cm or in:
            // If cm, the number must be at least 150 and at most 193.
            // If in, the number must be at least 59 and at most 76.
            boolean hgtValid = isValid(pass, "\\bhgt:(\\d+cm|\\d+in)\\b", valStr -> {
                if (valStr.endsWith("cm")) {
                    int val = Integer.parseInt(valStr.substring(0, valStr.length() - 2));
                    return val >= 150 && val <= 193;
                } else {
                    int val = Integer.parseInt(valStr.substring(0, valStr.length() - 2));
                    return val >= 59 && val <= 76;
                }
            });

            // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
            boolean hclValid = isValid(pass, "\\bhcl:(#[0-9a-f]{6})\\b", valStr -> true);

            // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
            boolean eclValid = isValid(pass, "\\becl:(amb|blu|brn|gry|grn|hzl|oth)\\b", valStr -> true);

            // pid (Passport ID) - a nine-digit number, including leading zeroes.
            boolean pidValid = isValid(pass, "\\bpid:(\\d{9})\\b", valStr -> true);

            if (byrValid && iyrValid && eyrValid && hgtValid && hclValid && eclValid && pidValid) {
                count++;
            }
        }
        System.out.println(count);
    }


    private static String[] parsePass(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        String raw = Files.readString(path);
        return raw.split("\n\n");
    }

    public static void main(String[] args) throws IOException {
        String[] passports = parsePass("input.txt");

        part1(passports);
        part2(passports);
    }
}
