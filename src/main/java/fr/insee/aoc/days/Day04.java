package fr.insee.aoc.days;

import java.util.Arrays;
import java.util.stream.IntStream;

import static fr.insee.aoc.utils.Days.readLine;
import static java.lang.Integer.parseInt;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Day04 implements Day {

    @Override
    public String part1(String input, Object... params) {
        long numberOfMatches = range(input)
                .mapToObj(Day04::arrayOfDigits)
                .filter(Day04::matchesRequirements)
                .count();
        return String.valueOf(numberOfMatches);
    }

    @Override
    public String part2(String input, Object... params) {
        long numberOfMatches = range(input)
                .mapToObj(Day04::arrayOfDigits)
                .filter(Day04::matchesRequirements)
                .filter(Day04::exactlyADouble)
                .count();
        return String.valueOf(numberOfMatches);
    }

    private static IntStream range(String input) {
        String[] tokens = readLine(input).split("-");
        int from = parseInt(tokens[0]);
        int to = parseInt(tokens[1]);
        return IntStream.range(from, to);
    }

    private static boolean matchesRequirements(int[] digits) {
        boolean containsDouble = false;
        for(int n = 0; n < 5; n ++) {
            if(digits[n] > digits[n + 1]) return false;
            if(digits[n] == digits[n + 1]) containsDouble = true;
        }
        return containsDouble;
    }

    private static boolean exactlyADouble(int[] digits) {
        return Arrays.stream(digits)
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .containsValue(2L);
    }

    private static int[] arrayOfDigits(int number) {
        int[] digits = new int[6];
        for(int i = 0; i < 6; i ++){
            digits[5 - i] = number % 10;
            number /= 10;
        }
        return digits;
    }
}
