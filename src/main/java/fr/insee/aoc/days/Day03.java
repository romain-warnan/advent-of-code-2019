package fr.insee.aoc.days;

import fr.insee.aoc.utils.DayException;
import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

import java.util.*;

import static fr.insee.aoc.utils.Days.*;
import static fr.insee.aoc.utils.Frame.overlappingFrame;
import static java.lang.Math.*;

public class Day03 implements Day {

    @Override
    public String part1(String input, Object... params) {
        Wire[] wires = wires(input);
        int min = intersections(wires[0], wires[1])
                .stream()
                .mapToInt(Point::manhattan)
                .min()
                .orElseThrow(DayException::new);
        return String.valueOf(min);
    }

    @Override
    public String part2(String input, Object... params) {
        Wire[] wires = wires(input);
        int min = intersections(wires[0], wires[1])
                .stream()
                .mapToInt(p -> totalDistanceToPoint(wires[0], wires[1], p))
                .min()
                .orElseThrow(DayException::new);
        return String.valueOf(min);
    }

    private static Wire[] wires(String input) {
        return streamOfLines(input)
                .map(line -> line.split(","))
                .map(Day03::wireFromLines)
                .toArray(Wire[]::new);
    }

    private static Set<Point> intersections(Wire wire1, Wire wire2) {
        Set<Point> intersections = new HashSet<>();
        for (Line line1 : wire1.lines) {
            for (Line line2 : wire2.lines) {
                line1.intersection(line2).ifPresent(intersections::add);
            }
        }
        intersections.remove(Point.ORIGIN);
        return intersections;
    }

    private static Wire wireFromLines(String[] wire) {
        List<Line> lines = new ArrayList<>(wire.length);
        Point start = Point.of(0, 0);
        for(String string : wire) {
            Line line = Line.from(start, string);
            lines.add(line);
            start = line.end;
        }
        return new Wire(lines);
    }

    private static int distanceToPoint(Wire wire, Point point) {
        int distance = 0;
        for(Line line : wire.lines) {
            List<Point> points = line.points();
            if(points.contains(point)) {
                if(point.getX() == line.start.getX()) {
                    return distance + abs(point.getY() - line.start.getY());
                }
                else {
                    return distance + abs(point.getX() - line.start.getX());
                }
            }
            else {
                distance += line.length;
            }
        }
        throw new DayException();
    }

    private static int totalDistanceToPoint(Wire wire1, Wire wire2, Point point) {
        return distanceToPoint(wire1, point) + distanceToPoint(wire2, point);
    }

    static class Wire {

        List<Line> lines;

        Wire(List<Line> lines) {
            this.lines = lines;
        }
    }

    static class Line {

        Way way;
        Point start;
        Point end;
        int length;

        static Line from(Point start, String string) {
            Line line = new Line();
            line.way = Way.valueOf(string.substring(0, 1));
            line.start = start;
            line.length = Integer.parseInt(string.substring(1));
            switch (line.way) {
                case U:
                    line.end = Point.of(start.getX(), start.getY() - line.length);
                    break;
                case R:
                    line.end = Point.of(start.getX() + line.length, start.getY());
                    break;
                case D:
                    line.end = Point.of(start.getX(), start.getY() + line.length);
                    break;
                case L:
                    line.end = Point.of(start.getX() - line.length, start.getY());
                    break;
            }
            return line;
        }

        private Frame asFrame() {
            return Frame.inBetween(start, end);
        }

        Optional<Point> intersection(Line other) {
            return overlappingFrame(this.asFrame(), other.asFrame()).map(f -> Point.of(f.getRight(), f.getTop()));
        }

        List<Point> points() {
            List<Point> points = new ArrayList<>(length);
            for(int n = 0; n < length; n ++) {
                switch (way) {
                    case U:
                        points.add(Point.of(start.getX(), start.getY() - n));
                        break;
                    case R:
                        points.add(Point.of(start.getX() + n, start.getY()));
                        break;
                    case D:
                        points.add(Point.of(start.getX(), start.getY() + n));
                        break;
                    case L:
                        points.add(Point.of(start.getX() - n, start.getY()));
                        break;
                }
            }
            return points;
        }

        enum Way { U, R, D, L }
    }
}
