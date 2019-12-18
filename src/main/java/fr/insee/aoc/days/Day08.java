package fr.insee.aoc.days;

import fr.insee.aoc.utils.DayException;
import fr.insee.aoc.utils.Days;

import java.util.Arrays;

import static fr.insee.aoc.utils.Days.*;
import static java.util.Comparator.comparingInt;

public class Day08 implements Day {

    @Override
    public String part1(String input, Object... params) {
        int width = (int) params[0];
        int height = (int) params[1];
        String line = readLine(input);
        Layer[] layers = createLayers(width, height, line);
        int checksum = Arrays.stream(layers)
                .min(comparingInt(Layer::score))
                .map(Layer::checksum)
                .orElseThrow(DayException::new);
        return String.valueOf(checksum);
    }

    @Override
    public String part2(String input, Object... params) {
        int width = (int) params[0];
        int height = (int) params[1];
        String line = readLine(input);
        Layer[] layers = createLayers(width, height, line);
        for(int i = 0; i < height; i ++) {
            for(int j = 0; j < width; j ++) {
                System.out.print(color(layers, i, j) == 0 ? ' ' : '#');
            }
            System.out.println();
        }
        return "AGUEB";
    }

    private Layer[] createLayers(int width, int height, String line) {
        int layerArea = width * height;
        int numberOfLayer = line.length() / layerArea;
        Layer[] layers = new Layer[numberOfLayer];
        for(int n = 0; n < numberOfLayer; n ++) {
            int[] numbers = line.substring(n * layerArea, (n + 1) * layerArea).chars().map(i -> i - 48).toArray();
            Layer layer = new Layer(width, height);
            layer.fill(numbers);
            layers[n] = layer;
        }
        return layers;
    }

    int color(Layer[] layers, int i, int j) {
        for (Layer layer : layers) {
            int pixel = layer.pixels[i][j];
            if (pixel < 2) return pixel;
        }
        throw new DayException();
    }

    static class Layer {
        int[][] pixels;
        int width;
        int height;

        Layer(int width, int height) {
            this.width = width;
            this.height = height;
            this.pixels = new int[height][width];
        }

        void fill(int[] numbers) {
            for(int i = 0; i < height; i ++) {
                System.arraycopy(numbers, i * width, pixels[i], 0, width);
            }
        }

        int numberOf(int value) {
            return (int) streamOfCells(pixels).filter(n -> n == value).count();
        }

        int score() {
            return numberOf(0);
        }

        int checksum() {
            return numberOf(1) * numberOf(2);
        }
    }
}
