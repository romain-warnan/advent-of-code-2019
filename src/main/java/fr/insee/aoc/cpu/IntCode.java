package fr.insee.aoc.cpu;

import fr.insee.aoc.utils.DayException;

import java.util.Arrays;

import static fr.insee.aoc.utils.Days.arrayOfInt;

public class IntCode {

    private int[] inputs;
    private int index = 0;

    private IntCode(int... inputs) {
        this.inputs = inputs;
    }

    public static IntCode withInputs(int... inputs) {
        return new IntCode(inputs);
    }

    public int execute(int[] program) {
        int[] instructions = Arrays.copyOf(program, program.length);
        int position = 0;
        int code = 0;
        OpCode opCode;
        while((opCode = readOpCode(position, instructions)) != null) {
            code = opCode.apply();
            position = opCode.nextPosition();
        }
        return code;
    }

    public int execute(String program) {
        return execute(arrayOfInt(program, ","));
    }

    private OpCode readOpCode(int position, int[] table) {
        String input = String.valueOf(table[position]);
        if(input.length() < 2) {
            input = '0' + input;
        }
        int modesLength = input.length() - 2;
        String code = input.substring(modesLength);
        boolean[] modes = new boolean[modesLength];
        for(int n = 0; n < modesLength; n ++) {
            modes[modesLength - n - 1] = input.charAt(n) == '1';
        }
        switch (code) {
            case "01": return new Add(position, table, modes);
            case "02": return new Multiply(position, table, modes);
            case "03": return new Input(position, table, modes, inputs[index++]);
            case "04": return new Output(position, table, modes);
            case "05": return new JumpIfTrue(position, table, modes);
            case "06": return new JumpIfFalse(position, table, modes);
            case "07": return new LessThan(position, table, modes);
            case "08": return new Equals(position, table, modes);
            case "99": return null;
            default: throw new DayException("Code inconnu : " + code);
        }
    }

    abstract static class OpCode {

        int position;
        int[] table;
        boolean[] modes;

        OpCode(int position, int[] table, boolean[] modes) {
            this.position = position;
            this.table = table;
            this.modes = modes;
        }

        int val(int index) {
            boolean mode = index <= modes.length && modes[index - 1];
            return table[mode ? position + index : table[position + index]];
        }

        abstract int apply();
        abstract int nextPosition();
    }

    static class Input extends OpCode {

        int id;

        Input(int position, int[] table, boolean[] modes, int id) {
            super(position, table, modes);
            this.id = id;
        }

        @Override
        public int apply() {
            table[table[position + 1]] = id;
            return 0;
        }

        @Override
        int nextPosition() {
            return position + 2;
        }
    }

    static class Output extends OpCode {

        Output(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public int apply() {
            return val(1);
        }

        @Override
        int nextPosition() {
            return position + 2;
        }
    }

    static class Multiply extends OpCode {

        Multiply(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public int apply() {
            table[table[position + 3]] = val(1) * val(2);
            return 0;
        }

        @Override
        int nextPosition() {
            return position + 4;
        }
    }

    static class Add extends OpCode {

        Add(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public int apply() {
            table[table[position + 3]] = val(1) + val(2);
            return 0;
        }

        @Override
        int nextPosition() {
            return position + 4;
        }
    }

    static class JumpIfTrue extends OpCode {

        boolean hasJumped = false;

        JumpIfTrue(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public int apply() {
            if(val(1) != 0) {
                table[position] = val(2);
                this.hasJumped = true;
            }
            return 0;
        }

        @Override
        int nextPosition() {
            return hasJumped ? table[position] : position + 3;
        }
    }

    static class JumpIfFalse extends OpCode {

        boolean hasJumped = false;

        JumpIfFalse(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public int apply() {
            if(val(1) == 0) {
                table[position] = val(2);
                this.hasJumped = true;
            }
            return 0;
        }

        @Override
        int nextPosition() {
            return hasJumped ? table[position] : position + 3;
        }
    }

    static class LessThan extends OpCode {

        LessThan(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public int apply() {
            table[table[position + 3]] = val(1) < val(2) ? 1 : 0;
            return 0;
        }

        @Override
        int nextPosition() {
            return position + 4;
        }
    }

    static class Equals extends OpCode {

        Equals(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public int apply() {
            table[table[position + 3]] = val(1) == val(2) ? 1 : 0;
            return 0;
        }

        @Override
        int nextPosition() {
            return position + 4;
        }
    }
}
