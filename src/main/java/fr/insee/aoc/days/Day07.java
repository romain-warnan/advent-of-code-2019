package fr.insee.aoc.days;

import fr.insee.aoc.cpu.IntCode;
import fr.insee.aoc.utils.DayException;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

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

        List<int[]> permutations = permutations(new int[]{5, 6, 7, 8, 9});
        for (int[] settings : permutations) {
            IntCode[] intCodes = amplifiersFromSettings(program, settings);
            intCodes[0].sendInput(0);
            while(true) {
                for (int n = 0; n < intCodes.length; n ++) {
                    IntCode intCode = intCodes[n];
                    IntCode nextIntCode = intCodes[(n + 1) % intCodes.length];
                    intCode.execute();
                    nextIntCode.sendInput(intCode.pollOutput());
                }
            }
        }
        return null;
    }

    private static int output(int[] program, int[] settings) {
        int output = 0;
        for (int setting : settings) {
            IntCode intCode = new IntCode(program, setting, output);
            output = intCode.execute();
        }
        return output;
    }

    private static IntCode[] amplifiersFromSettings(int[] program, int[] settings) {
        return Arrays.stream(settings).mapToObj(setting -> new IntCode(program, setting)).toArray(IntCode[]::new);
    }
}
