package fr.insee.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08Test {
    
	private Day day = new Day08();
	
	@Test
	public void case1_0() {
		assertEquals("1", day.part1("src/test/resources/08-0.txt", 3, 2));
	}

	@Test
	public void case2_0() {
		assertEquals("AGUEB", day.part2("src/test/resources/08-1.txt", 2, 2));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/08.txt", 25, 6);
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		assertEquals("2760", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/08.txt", 25, 6);
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		assertEquals("AGUEB", answer);
	}
}
