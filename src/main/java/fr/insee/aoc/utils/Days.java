package fr.insee.aoc.utils;

import static java.lang.Math.*;
import static java.util.Spliterators.*;
import static java.util.stream.StreamSupport.*;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public class Days {

	private Days() {
		//
	}
	
	public static Stream<String> streamOfLines(String input) {
		try {
			return Files.lines(Paths.get(input));
		} catch (IOException e) {
			//
		}
		throw new DayException("Erreur dans la lecture du fichier");
	}

	public static Stream<String> streamOfLines(String input, String regex) {
		return Arrays.stream(readLine(input).split(regex));
	}

	public static IntStream streamOfInt(String input) {
		return streamOfLines(input).mapToInt(Integer::parseInt);
	}

	public static IntStream streamOfInt(String input, String regex) {
		return streamOfLines(input, regex).mapToInt(Integer::valueOf);
	}

	public static <T> Stream<T> streamOfCells(T[][] array) {
		return Arrays.stream(array).flatMap(Arrays::stream);
	}
	
	public static IntStream streamOfCells(int[][] array) {
		return Arrays.stream(array).flatMapToInt(Arrays::stream);
	}
	
	public static <T> Stream<T> reverseStream(Deque<T> deque) {
		return stream(spliterator(deque.descendingIterator(), deque.size(), Spliterator.ORDERED), false);
	}
	
	public static String readLine(String input) {
		return streamOfLines(input).findFirst().orElse("");
	}

	public static List<String> listOfLines(String input) {
		return streamOfLines(input).collect(toList());
	}

	public static List<String> listOfLines(String input, String regex) {
		return streamOfLines(input, regex).collect(toList());
	}

	public static List<Integer> listOfIntegers(String input) {
		return streamOfInt(input).boxed().collect(toList());
	}

	public static List<Integer> listOfIntegers(String input, String regex) {
		return streamOfInt(input, regex).boxed().collect(toList());
	}

	public static String[] arrayOfLines(String input) {
		return streamOfLines(input).toArray(String[]::new);
	}

	public static String[] arrayOfLines(String input, String regex) {
		return streamOfLines(input, regex).toArray(String[]::new);
	}

	public static int[] arrayOfInt(String input) {
		return streamOfInt(input).toArray();
	}

	public static int[] arrayOfInt(String input, String regex) {
		return streamOfInt(input, regex).toArray();
	}

	public static char[][] tableOfChars(String input) {
		return streamOfLines(input).map(String::toCharArray).toArray(char[][]::new);
	}
	
	public static <T> boolean in(T element, Collection<T> collection) {
		return collection != null && collection.contains(element);
	}

	public static <T> boolean notIn(T element, Collection<T> collection) {
		return !in(element, collection);
	}
	
	public static int readInt(int group, Matcher matcher) {
		return Integer.parseInt(matcher.group(group));
	}
	
	public static char readChar(int group, Matcher matcher) {
		return readString(group, matcher).charAt(0);
	}

	public static String readString(int group, Matcher matcher) {
		return matcher.group(group);
	}

	public static LocalDateTime readDate(int group, Matcher matcher, DateTimeFormatter formatter) {
		return LocalDateTime.parse(matcher.group(group), formatter);
	}
	
	public static int indexOfMax(int[] array) {
		int maxAt = 0;
		for (int i = 0; i < array.length; i++) {
		    maxAt = array[i] > array[maxAt] ? i : maxAt;
		}
		return maxAt;
	}
	
	public static int maxOf(int[] array) {
		return Arrays.stream(array).max().getAsInt();
	}
	
	public static int indexOfMin(int[] array) {
		int minAt = 0;
		for (int i = 0; i < array.length; i++) {
			minAt = array[i] < array[minAt] ? i : minAt;
		}
		return minAt;
	}
	
	public static int minOf(int[] array) {
		return Arrays.stream(array).min().getAsInt();
	}

	public static int[] swap(int[] array, int i, int j) {
	    int element = array[i];
	    array[i] = array[j];
	    array[j] = element;
	    return array;
    }

    public static List<int[]> permutations(int[] array) {
        List<int[]> permutations = new ArrayList<>((int) pow(2, array.length));
        permute(array, 0, permutations);
        return permutations;
    }

    private static void permute(int[] array, int k, List<int[]> arrays){
        for(int i = k; i < array.length; i++){
            swap(array, i, k);
            permute(array, k+1, arrays);
            swap(array, k, i);
        }
        if (k == array.length - 1){
            arrays.add(Arrays.copyOf(array, array.length));
        }
    }
}
