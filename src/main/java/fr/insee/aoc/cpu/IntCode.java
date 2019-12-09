package fr.insee.aoc.cpu;

import fr.insee.aoc.utils.DayException;

import java.util.*;

import static java.util.Collections.*;

public class IntCode {

    private List<Integer> inputs = new ArrayList<>();
    private Deque<Integer> outputs = new ArrayDeque<>();
    private int indexOfInput = 0;
    private boolean waitingForInput = false;
    private int position = 0;
    private int[] program;

    public IntCode(int[] program, int... inputs) {
        this.program = Arrays.copyOf(program, program.length);
        Arrays.stream(inputs).boxed().forEach(this.inputs::add);
    }

    public Integer execute() {
        OpCode opCode;
        while((opCode = readOpCode(position, program)) != null && !waitingForInput) {
            opCode.apply();
            if(!waitingForInput) {
                position = opCode.nextPosition();
            }
        }
        return outputs.peekLast();
    }

    public void sendInput(List<Integer> inputs) {
        this.inputs.addAll(inputs);
        waitingForInput = false;
    }

    public void sendInput(int input) {
        sendInput(singletonList(input));
    }

    public List<Integer> pollOutput() {
        List<Integer> out = new ArrayList<>();
        out.addAll(outputs);
        outputs.clear();
        return out;
    }

    public List<Integer> getInputs() {
        return inputs;
    }

    public Deque<Integer> getOutputs() {
        return outputs;
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
            case "03": return new Input(position, table, modes);
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

        abstract void apply();
        abstract int nextPosition();
    }

    class Input extends OpCode {

        Input(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public void apply() {
            if(indexOfInput < inputs.size()) {
                table[table[position + 1]] = inputs.get(indexOfInput++);
            }
            else {
                waitingForInput = true;
            }
        }

        @Override
        int nextPosition() {
            return position + 2;
        }
    }

    class Output extends OpCode {

        Output(int position, int[] table, boolean[] modes) {
            super(position, table, modes);
        }

        @Override
        public void apply() {
            outputs.add(val(1));
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
        public void apply() {
            table[table[position + 3]] = val(1) * val(2);
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
        public void apply() {
            table[table[position + 3]] = val(1) + val(2);
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
        public void apply() {
            if(val(1) != 0) {
                table[position] = val(2);
                this.hasJumped = true;
            }
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
        public void apply() {
            if(val(1) == 0) {
                table[position] = val(2);
                this.hasJumped = true;
            }
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
        public void apply() {
            table[table[position + 3]] = val(1) < val(2) ? 1 : 0;
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
        public void apply() {
            table[table[position + 3]] = val(1) == val(2) ? 1 : 0;
        }

        @Override
        int nextPosition() {
            return position + 4;
        }
    }
}