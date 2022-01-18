package pl.ngalda.adventofcode2021.solutions.day3;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PowerConsumptionCalculator {

    static int calculateConsumption() {
        List<String> lines = FileReader.readLines("day3.txt");
        Map<Integer, Map<Character, Integer>> consumptionInPosition = mostCommonBitsInPosition(lines);
        String gammaRate = "";
        for (Integer bitPosition : consumptionInPosition.keySet()) {
            gammaRate = consumptionInPosition.get(bitPosition).get('0') > consumptionInPosition.get(bitPosition).get('1')
                    ? gammaRate.concat("0") : gammaRate.concat("1");
        }
        String epsilonRate = gammaRate.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");
        return Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2);
    }

    static int calculateC02() {
        List<String> lines = FileReader.readLines("day3.txt");
        Map<Integer, Map<Character, Integer>> consumptionInPosition = mostCommonBitsInPosition(lines);
        List<String> oxygenRating = new ArrayList<>(lines);
        List<String> co2Rating = new ArrayList<>(lines);
        for (int bitPosition = 0; bitPosition < lines.get(0).length(); bitPosition++) {
            if(oxygenRating.size() > 1) oxygenRating = calculateOxygenRating(oxygenRating, bitPosition);
            if(co2Rating.size() > 1) co2Rating = calculateCO2Rating(co2Rating, bitPosition);
        }
        return Integer.parseInt(oxygenRating.get(0), 2) * Integer.parseInt(co2Rating.get(0), 2);
    }

    private static List<String> calculateCO2Rating(List<String> co2Rating, int bitPosition) {
        char finalMostCommonBit1 = getMostCommonBit(co2Rating, bitPosition);
        co2Rating = co2Rating
                .stream()
                .filter(bitString -> bitString.charAt(bitPosition) != finalMostCommonBit1)
                .collect(Collectors.toList());
        return co2Rating;
    }

    private static char getMostCommonBit(List<String> co2Rating, int bitPosition) {
        char mostCommonBit;
        Map<Integer, Map<Character, Integer>> consumptionInPosition;
        consumptionInPosition = mostCommonBitsInPosition(co2Rating);
        mostCommonBit = consumptionInPosition.get(bitPosition).get('0') >
                consumptionInPosition.get(bitPosition).get('1')
                ? '0' : '1';
        return mostCommonBit;
    }

    private static List<String> calculateOxygenRating(List<String> oxygenRating, int bitPosition) {
        char finalMostCommonBit = getMostCommonBit(oxygenRating, bitPosition);
        oxygenRating = oxygenRating
                .stream()
                .filter(bitString -> bitString.charAt(bitPosition) == finalMostCommonBit)
                .collect(Collectors.toList());
        return oxygenRating;
    }

    private static Map<Integer, Map<Character, Integer>> mostCommonBitsInPosition(List<String> lines) {
        Map<Integer, Map<Character, Integer>> consumptionInPosition = new HashMap<>();
        for (String line : lines) {
            char[] bits = line.toCharArray();
            for (int i = 0; i < bits.length; i++) {
                if (!consumptionInPosition.containsKey(i)) {
                    consumptionInPosition.put(i, new HashMap<Character, Integer>() {{
                        put('0', 0);
                        put('1', 0);
                    }});
                }
                consumptionInPosition.get(i).put(bits[i], consumptionInPosition.get(i).get(bits[i]) + 1);
            }
        }
        return consumptionInPosition;
    }

    public static void main(String[] args) {
        System.out.println(PowerConsumptionCalculator.calculateC02());
    }
}
