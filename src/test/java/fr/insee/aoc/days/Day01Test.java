package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day01Test  {
    
	private Day day = new Day01();
	
	@Test
	public void case1_0() {
		assertEquals("34241", day.part1("src/test/resources/01-0.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("51316", day.part2("src/test/resources/01-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/01.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		assertEquals("3563458", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/01.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		assertEquals("5342292", answer);
	}
}
