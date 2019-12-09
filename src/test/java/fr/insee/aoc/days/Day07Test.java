package fr.insee.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day07Test {
    
	private Day day = new Day07();
	
	@Test
	public void case1_0() {
		assertEquals("43210", day.part1("src/test/resources/07-0.txt"));
	}

	@Test
	public void case1_1() {
		assertEquals("54321", day.part1("src/test/resources/07-1.txt"));
	}

	@Test
	public void case1_2() {
		assertEquals("65210", day.part1("src/test/resources/07-2.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("139629729", day.part2("src/test/resources/07-3.txt"));
	}

	@Test
	public void case2_1() {
		assertEquals("18216", day.part2("src/test/resources/07-4.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/07.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		assertEquals("34686", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/07.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		assertEquals("_____", answer);
	}
}
