package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readLine;

import java.util.Arrays;

import fr.insee.aoc.utils.DayException;

public class Day02 implements Day {

	@Override
	public String part1(String input, Object... params) {
        int[] table = readTable(input);
        if(params.length == 2) table = initializeTable(table, (int) params[0], (int) params[1]);
        return String.valueOf(output(table));
    }

    @Override
    public String part2(String input, Object... params) {
        int[] table = readTable(input);
	    int output = (int) params[0];
	    for(int noun = 0; noun < 100; noun ++) {
            for(int verb = 0; verb < 100; verb ++) {
                int[] initializedTable = initializeTable(table, noun, verb);
                if(output(initializedTable) == output) return String.valueOf(100 * noun + verb);
            }
        }
	    throw new DayException();
    }

    private static int output(int[] table) {
        int index = 0;
        while (index < table.length) {
            switch (table[index]) {
                case 1:
                    table[table[index + 3]] = table[table[index + 1]] + table[table[index + 2]];
                    break;
                case 2:
                    table[table[index + 3]] = table[table[index + 1]] * table[table[index + 2]];
                    break;
                case 99:
                    return table[0];
                default:
                    throw new DayException();
            }
            index += 4;
        }
        throw new DayException();
    }

    private static int[] readTable(String input) {
        return Arrays.stream(readLine(input).split(",")).mapToInt(Integer::valueOf).toArray();
    }

    private static int[] initializeTable(int[] table, int noun, int verb) {
	    int[] initializedTable = Arrays.copyOf(table, table.length);
        initializedTable[1] = noun;
        initializedTable[2] = verb;
        return initializedTable;
    }
}
