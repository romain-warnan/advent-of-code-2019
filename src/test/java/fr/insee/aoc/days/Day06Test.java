package fr.insee.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06Test {
    
	private Day day = new Day06();
	
	@Test
	public void case1_0() {
		assertEquals("42", day.part1("src/test/resources/06-0.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/06.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		assertEquals("315757", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/06.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		assertEquals("---", answer);
	}
}
