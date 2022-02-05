package pl.ngalda.adventofcode2021.solutions.day8;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SevenSegmentSearcher {

    // 7 uses 3 segments
    // 4 uses 4 segments
    // 1 uses 2 segments
    // 8 uses 7 segments

    public static void main(String[] args) {
        List<String> lines = FileReader.readLines("day8.txt");
        List<String> splitLines = lines
                .stream()
                .map(line -> line.split("\\|"))
                .flatMap(Stream::of)
                .collect(Collectors.toList());
        List<String> outputValues = IntStream
                .range(0, splitLines.size())
                .filter(i -> i%2 != 0)
                .mapToObj(splitLines::get)
                .collect(Collectors.toList());
        outputValues = outputValues
                .stream()
                .map(String::trim)
                .map(line -> line.split(" "))
                .flatMap(Stream::of)
                .collect(Collectors.toList());
        int countOccurencesOf1478 = 0;
        for(int i=0; i< outputValues.size(); i++){
                System.out.println(outputValues.get(i));
                if (Set.of(2, 3, 4, 7).contains(outputValues.get(i).length())) {
                    countOccurencesOf1478++;
                }
        }
        System.out.println(countOccurencesOf1478);
    }
}
