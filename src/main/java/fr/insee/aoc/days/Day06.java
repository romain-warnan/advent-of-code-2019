package fr.insee.aoc.days;

import fr.insee.aoc.utils.DayException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fr.insee.aoc.utils.Days.*;
import static java.util.stream.Collectors.*;

public class Day06 implements Day {

    @Override
    public String part1(String input, Object... params) {
        Set<Orbit> orbits = buildOrbits(input);
        int totalNumberOfOrbits = orbits.stream().mapToInt(Orbit::numberOfOrbits).sum();
        return String.valueOf(totalNumberOfOrbits);
    }

    @Override
    public String part2(String input, Object... params) {
        Set<Orbit> orbits = buildOrbits(input);
        Orbit start = findOrbit("YOU", orbits).axe;
        Orbit end = findOrbit("SAN", orbits).axe;
        List<Orbit> parents = start.parentAxes();
        return null;
    }

    private static Set<Orbit> buildOrbits(String input) {
        Set<Orbit> orbits = streamOfLines(input)
                .map(line -> line.split("\\)"))
                .map(tokens -> Orbit.of(tokens[1], tokens[0]))
                .collect(toSet());
        orbits.forEach(orbit -> orbit.axe = findAxe(orbit, orbits));
        return orbits;
    }

    private static Orbit findAxe(Orbit orbit, Set<Orbit> orbits) {
        return orbits.parallelStream().filter(o -> o.name.equals(orbit.axeName)).findFirst().orElse(Orbit.COM);
    }

    private static Orbit findOrbit(String name, Set<Orbit> orbits) {
        return orbits.parallelStream().filter(o -> o.name.equals(name)).findFirst().orElseThrow(DayException::new);
    }

    static class Orbit {
        static final Orbit COM = Orbit.of("COM", null);

        Orbit axe;
        String name;
        String axeName;

        static Orbit of(String name, String axeName) {
            Orbit orbit = new Orbit();
            orbit.name = name;
            orbit.axeName = axeName;
            return orbit;
        }

        private List<Orbit> parentAxes(List<Orbit> parentAxes) {
            if(axe != null) {
                parentAxes.addAll(axe.parentAxes(parentAxes));
            }
            return parentAxes;
        }

        List<Orbit> parentAxes() {
            return parentAxes(new ArrayList<>());
        }

        int numberOfOrbits() {
            if(axe == null) return 0;
            return 1 + axe.numberOfOrbits();
        }
    }
}
