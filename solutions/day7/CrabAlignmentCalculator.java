package pl.ngalda.adventofcode2021.solutions.day7;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.Collections;
import java.util.List;

public class CrabAlignmentCalculator {
    public static void main(String[] args) {
        List<Integer> positions = FileReader.readComaSeperatedValuesAsInt("day7.txt");
        int bestPosition = -1;
        int usedFuel = Integer.MAX_VALUE;
        for (int i = Collections.min(positions); i <= Collections.max(positions); i++) {
            int calculatedFuel = calculateFuelNeededToMoveAllToPosition(positions, i);
            if (calculatedFuel < usedFuel) {
                bestPosition = i;
                usedFuel = calculatedFuel;
            }
        }
        System.out.println(bestPosition + " " + usedFuel);
    }

    private static int calculateFuelNeededToMoveAllToPosition(List<Integer> positions, int i) {
        return positions.stream()
                .mapToInt(x -> Math.abs(x - i))
                .sum();
    }

}
