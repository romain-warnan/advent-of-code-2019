package fr.insee.aoc.days;

import fr.insee.aoc.cpu.IntCode;
import fr.insee.aoc.utils.DayException;

import java.util.Arrays;

import static fr.insee.aoc.utils.Days.*;
import static java.lang.Math.*;

public class Day07 implements Day {

    @Override
    public String part1(String input, Object... params) {
        int[] program = arrayOfInt(input, ",");
        int max = permutations(new int[]{ 0, 1, 2, 3, 4 }).stream()
                .mapToInt(settings -> output(program, settings))
                .max()
                .orElseThrow(DayException::new);
        return String.valueOf(max);
    }

    @Override
    public String part2(String input, Object... params) {
        int[] program = arrayOfInt(input, ",");
        int max = permutations(new int[]{5, 6, 7, 8, 9}).stream()
                .mapToInt(settings -> feedbackOutput(program, settings))
                .max()
                .orElseThrow(DayException::new);
        return String.valueOf(max);
    }

    private static int feedbackOutput(int[] program, int[] settings) {

        return 0;
    }

    private static int output(int[] program, int[] settings) {
        int output = 0;
        for (int setting : settings) {
            output = IntCode.withInputs(setting, output).execute(program);
        }
        return output;
    }
}
