package fr.insee.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03Test {
    
	private Day day = new Day03();
	
	@Test
	public void case1_0() {
		assertEquals("6", day.part1("src/test/resources/03-0.txt"));
	}
	
	@Test
	public void case1_1() {
		assertEquals("159", day.part1("src/test/resources/03-1.txt"));
	}

	@Test
	public void case1_2() {
		assertEquals("135", day.part1("src/test/resources/03-2.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("30", day.part2("src/test/resources/03-0.txt"));
	}

	@Test
	public void case2_1() {
		assertEquals("610", day.part2("src/test/resources/03-1.txt"));
	}

	@Test
	public void case2_2() {
		assertEquals("410", day.part2("src/test/resources/03-2.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/03.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		assertEquals("245", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/03.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		assertEquals("48262", answer);
	}
}
