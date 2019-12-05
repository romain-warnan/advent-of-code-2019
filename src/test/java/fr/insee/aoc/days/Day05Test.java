package fr.insee.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {
    
	private Day day = new Day05();

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/05.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		assertEquals("5821753", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/05.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		assertEquals("11956381", answer);
	}
}
