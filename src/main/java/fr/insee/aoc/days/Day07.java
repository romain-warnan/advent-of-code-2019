package fr.insee.aoc.days;

import fr.insee.aoc.cpu.IntCode;
import fr.insee.aoc.utils.DayException;

import java.util.List;

import static fr.insee.aoc.utils.Days.*;
import static java.lang.Math.*;

public class Day07 implements Day {

    @Override
    public String part1(String input, Object... params) {
        int[] program = arrayOfInt(input, ",");
        int max = permutations(new int[]{0, 1, 2, 3, 4}).stream()
                .mapToInt(settings -> simpleOutput(program, settings))
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
        int output = simpleOutput(program, settings);
        int max = output;
        int n = 0;
        while (n < 1000) {
            output = output(program, settings, output, false);
            max = max(max, output);
            n ++;
        }
        return max;
    }

    private static int simpleOutput(int[] program, int[] settings) {
        return output(program, settings, 0, true);
    }

    private static int output(int[] program, int[] settings, int input, boolean restart) {
        int output = input;
        for(int setting : settings) {
            output = IntCode.withInputs(setting, output).execute(program, restart);
        }
        return output;
    }
}
