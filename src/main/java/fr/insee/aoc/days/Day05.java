package fr.insee.aoc.days;

import fr.insee.aoc.cpu.IntCode;
import fr.insee.aoc.utils.Days;

public class Day05 implements Day {

	@Override
	public String part1(String input, Object... params) {
	    int[] program = Days.arrayOfInt(input, ",");
        IntCode intCode = new IntCode(program, 1);
        return String.valueOf(intCode.execute());
    }

	@Override
	public String part2(String input, Object... params) {
        int[] program = Days.arrayOfInt(input, ",");
        IntCode intCode = new IntCode(program, 5);
        return String.valueOf(intCode.execute());
    }
}
