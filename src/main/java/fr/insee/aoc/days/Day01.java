package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;
import static java.lang.Math.*;

import java.util.HashSet;
import java.util.Set;

public class Day01 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int sum = streamOfInt(input).map(Day01::fuelRequired).sum();
		return String.valueOf(sum);
	}

	@Override
	public String part2(String input, Object... params) {
		int sum = streamOfInt(input).map(Day01::totalFuelRequired).sum();
		return String.valueOf(sum);
	}

	private static int fuelRequired(int mass) {
		return (int) (floor((double) mass / 3) - 2);
	}

	private static int totalFuelRequired(int mass) {
		int fuelRequired = fuelRequired(mass);
		if(fuelRequired <= 0) return 0;
		return fuelRequired + totalFuelRequired(fuelRequired);
	}
}
