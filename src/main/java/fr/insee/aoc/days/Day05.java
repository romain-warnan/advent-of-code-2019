package fr.insee.aoc.days;

import fr.insee.aoc.cpu.IntCode;

public class Day05 implements Day {

	@Override
	public String part1(String input, Object... params) {
        IntCode intCode = IntCode.withInputs(1);
        return String.valueOf(intCode.execute(input));
    }

	@Override
	public String part2(String input, Object... params) {
        IntCode intCode = IntCode.withInputs(5);
        return String.valueOf(intCode.execute(input));
    }
}
