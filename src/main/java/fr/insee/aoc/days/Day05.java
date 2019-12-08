package fr.insee.aoc.days;

import fr.insee.aoc.cpu.IntCode;
import fr.insee.aoc.utils.Days;

public class Day05 implements Day {

	@Override
	public String part1(String input, Object... params) {
	    int[] program = Days.arrayOfInt(input, ",");
        return String.valueOf(IntCode.withInputs(1).execute(program));
    }

	@Override
	public String part2(String input, Object... params) {
        int[] program = Days.arrayOfInt(input, ",");
        return String.valueOf(IntCode.withInputs(5).execute(program));
    }
}
