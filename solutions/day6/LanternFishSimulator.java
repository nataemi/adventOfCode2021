package pl.ngalda.adventofcode2021.solutions.day6;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LanternFishSimulator {
    public static void main(String[] args) {
        int dayCount = 256;
        List<Integer> lanternFishDays = FileReader.readComaSeperatedValuesAsInt("day6.txt");

        for (int i = 0; i < dayCount; i++) {
            List<Integer> newFish = new ArrayList<>();
            lanternFishDays.stream()
                    .map(fish -> {
                        if(fish > 0) {
                            newFish.add(fish-1);
                        }
                        else{
                            newFish.add(8);
                            newFish.add(6);
                        }
                        return newFish;
                    })
                    .collect(Collectors.toList());
            lanternFishDays = newFish;
        }
        System.out.println(lanternFishDays.size());
    }
}
