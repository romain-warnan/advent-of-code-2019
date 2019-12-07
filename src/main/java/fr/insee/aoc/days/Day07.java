package fr.insee.aoc.days;

import fr.insee.aoc.cpu.IntCode;
import fr.insee.aoc.utils.DayException;

import static fr.insee.aoc.utils.Days.*;

public class Day07 implements Day {

    private static final int[] values = {0, 1, 2, 3, 4};

    @Override
    public String part1(String input, Object... params) {
        int[] program = arrayOfInt(input, ",");
        int max = permutations(values).stream()
                .mapToInt(settings -> output(program, settings))
                .max()
                .orElseThrow(DayException::new);
        return String.valueOf(max);
    }

    private static int output(int[] program, int[] settings) {
        int output = 0;
        for(int setting : settings) {
            output = IntCode.withInputs(setting, output).execute(program);
        }
        return output;
    }
}
