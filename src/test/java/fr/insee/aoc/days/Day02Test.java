package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day02Test  {
    
	private Day day = new Day02();
	
	@Test
	public void case1_0() {
		assertEquals("3500", day.part1("src/test/resources/02-0.txt"));
	}
	
	@Test
	public void case1_1() {
		assertEquals("2", day.part1("src/test/resources/02-1.txt"));
	}

	@Test
	public void case1_4() {
		assertEquals("30", day.part1("src/test/resources/02-2.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/02.txt",12, 2);
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		assertEquals("3790689", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/02.txt", 19690720);
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		assertEquals("6533", answer);
	}
}
