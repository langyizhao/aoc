import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://adventofcode.com/2020/day/9
public class Solution {

    private static boolean combinable(Long num, List<Long> last25) {
        Set<Long> candidates = new HashSet<>();
        for (Long n : last25) {
            if (candidates.contains(n)) {
                return true;
            } else {
                candidates.add(num - n);
            }
        }
        return false;
    }

    private static Long part1(List<Long> nums) {
        for (int i = 25; i < nums.size(); i++) {
            Long num = nums.get(i);
            List<Long> last25 = nums.subList(i-25, i);
            if (!combinable(num, last25)) {
                System.out.println(num);
                return num;
            }
        }
        return null;
    }

    private static void part2(List<Long> nums, Long target) {
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j <= nums.size(); j++) {
                List<Long> sub = nums.subList(i, j);
                Long sum = sub.stream().mapToLong(Long::intValue).sum();
                if (sum > target) {
                    break;
                } else if (sum < target) {
                    continue;
                } else {
                    System.out.println(Collections.max(sub) + Collections.min(sub));
                    return;
                }
            }
        }
    }

    private static List<Long> parse(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(Long::valueOf).collect(Collectors.toList());
        }
    }

    public static void main(String[] args) throws IOException {
        List<Long> nums = parse("input.txt");

        Long target = part1(nums);
        part2(nums, target);
    }
}
