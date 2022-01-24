package pl.ngalda.adventofcode2021.solutions.day5;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.Arrays;
import java.util.List;

public class LinesOverlappingCalculator {

    public static void main(String[] args) {
        int[][] positions = new int[1000][1000];
        for (int i = 0; i < positions.length; i++) {
            for (int y = 0; y < positions.length; y++) {
                positions[i][y] = 0;
            }
        }
        List<String> lines = FileReader.readLines("day5.txt");
        for (String lineFromFile : lines) {
            String[] coordinates = lineFromFile.split(" -> ");
            int x1 = Integer.parseInt(coordinates[0].split(",")[0]);
            int y1 = Integer.parseInt(coordinates[0].split(",")[1]);
            int x2 = Integer.parseInt(coordinates[1].split(",")[0]);
            int y2 = Integer.parseInt(coordinates[1].split(",")[1]);

            Line line = Line.calculateLineFromPoints(x1, y1, x2, y2);
            for (int i = 0; i < positions.length; i++) {
                for (int j = 0; j < positions.length; j++) {
                    if (line.checkIfPointInLine(i, j)
                            && checkIfInBounds(x1, y1, x2, y2, i, j)) {
                        positions[i][j]++;
                    }
                }
            }
        }
        printTable(positions);
        sumOverOne(positions);
    }

    private static void printTable(int[][] positions) {
        for (int[] position : positions) {
            System.out.println(Arrays.toString(position));
        }
    }

    private static int sumOverOne(int[][] positions) {
        int countOver1 = 0;
        for (int[] position : positions) {
            for (int j = 0; j < positions.length; j++) {
                if (position[j] > 1) countOver1++;
            }
        }
        System.out.println(countOver1);
        return countOver1;
    }

    static boolean checkIfInBounds(int x1, int y1, int x2, int y2, int i, int j) {
        return ((x1 < x2 && i >= x1 && i <= x2)
                || (i >= x2 && i <= x1))
                &&
                ((y1 < y2 && j >= y1 && j <= y2)
                        || j >= y2 && j <= y1);
    }


}
