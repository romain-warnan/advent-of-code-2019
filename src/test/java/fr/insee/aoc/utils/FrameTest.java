package fr.insee.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;


public class FrameTest  {
    
	@Test
	public void testFrame_smallestFrameContaining() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		Point c = Point.of(0, 0);
		Frame frame = Frame.smallestFrameContaining(Arrays.asList(a, b, c));
		assertThat(frame)
			.hasFieldOrPropertyWithValue("top", 0)
			.hasFieldOrPropertyWithValue("bottom", 3)
			.hasFieldOrPropertyWithValue("left", -1)
			.hasFieldOrPropertyWithValue("right", 2);
	}
	
	@Test
	public void testFrame_inBetween() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		Frame frame = Frame.inBetween(a, b);
		assertThat(frame)
			.hasFieldOrPropertyWithValue("top", 1)
			.hasFieldOrPropertyWithValue("bottom", 3)
			.hasFieldOrPropertyWithValue("left", -1)
			.hasFieldOrPropertyWithValue("right", 2);
	}

	@Test
	public void testFrame_overlapping_same() {
		Frame fA = Frame.frameOf(0, 3, 0, 2);
		Frame fB = Frame.frameOf(0, 3, 0, 2);
		Optional<Frame> frame = Frame.overlappingFrame(fA, fB);
		assertThat(frame)
			.isNotEmpty()
			.get()
			.hasFieldOrPropertyWithValue("top", 0)
			.hasFieldOrPropertyWithValue("bottom", 3)
			.hasFieldOrPropertyWithValue("left", 0)
			.hasFieldOrPropertyWithValue("right", 2);
	}

	@Test
	public void testFrame_overlapping_empty() {
		Frame fA = Frame.frameOf(0, 3, 0, 2);
		Frame fB = Frame.frameOf(0, 3, 10, 12);
		Optional<Frame> frame = Frame.overlappingFrame(fA, fB);
		assertThat(frame).isEmpty();
	}

	@Test
	public void testFrame_overlapping_point() {
		Frame fA = Frame.frameOf(0, 0, -5, 5);
		Frame fB = Frame.frameOf(-5, 5, 0, 0);
		Optional<Frame> frame = Frame.overlappingFrame(fA, fB);
		assertThat(frame)
				.isNotEmpty()
				.get()
				.hasFieldOrPropertyWithValue("top", 0)
				.hasFieldOrPropertyWithValue("bottom", 0)
				.hasFieldOrPropertyWithValue("left", 0)
				.hasFieldOrPropertyWithValue("right", 0);
	}

	@Test
	public void testFrame_overlapping_frame() {
		Frame fA = Frame.frameOf(-1, 1, -5, 5);
		Frame fB = Frame.frameOf(-5, 5, -1, 1);
		Optional<Frame> frame = Frame.overlappingFrame(fA, fB);
		assertThat(frame)
				.isNotEmpty()
				.get()
				.hasFieldOrPropertyWithValue("top", -1)
				.hasFieldOrPropertyWithValue("bottom", 1)
				.hasFieldOrPropertyWithValue("left", -1)
				.hasFieldOrPropertyWithValue("right", 1);
	}

	@Test
	public void testFrame_isOnTheEdge() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		Frame frame = Frame.frameOf(0, 3, 0, 2);
		assertThat(frame.isOnTheEdge(a)).isFalse();
		assertThat(frame.isOnTheEdge(b)).isTrue();
	}
	
	@Test
	public void testFrame_width() {
		Frame frame = Frame.frameOf(0, 3, 0, 2);
		assertThat(frame.width()).isEqualTo(2);
	}
	
	@Test
	public void testFrame_height() {
		Frame frame = Frame.frameOf(0, 3, 0, 2);
		assertThat(frame.height()).isEqualTo(3);
	}
}
