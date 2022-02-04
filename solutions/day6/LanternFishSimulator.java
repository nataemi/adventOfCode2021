package pl.ngalda.adventofcode2021.solutions.day6;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LanternFishSimulator {
    public static void main(String[] args) {
        int dayCount = 256;
        List<Integer> lanternFishDays = FileReader.readComaSeperatedValuesAsInt("day6.txt");
        Map<Integer, Integer> fishLivingInDays = new HashMap<>();
        fishLivingInDays.put(0,0);
        fishLivingInDays.put(1,0);
        fishLivingInDays.put(2,0);
        fishLivingInDays.put(3,0);
        fishLivingInDays.put(4,0);
        fishLivingInDays.put(5,0);
        fishLivingInDays.put(6,0);
        fishLivingInDays.put(7,0);
        fishLivingInDays.put(8,0);

        for(int daysLeft : lanternFishDays){
            fishLivingInDays.put(daysLeft, fishLivingInDays.get(daysLeft) + 1);
        }

        for (int i = 0; i < dayCount; i++) {
            Map<Integer,Integer> fishLivingInDaysCopy =
                    fishLivingInDays.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            int zeros = fishLivingInDays.get(0);
            for(int j = 0; j<= 7; j++){
                fishLivingInDays.put(j, fishLivingInDaysCopy.get(j+1));
            }
            fishLivingInDays.put(8,0);
            for(int k = 0; k< zeros; k++) {
                fishLivingInDays.put(6, fishLivingInDays.get(6) + 1);
                fishLivingInDays.put(8, fishLivingInDays.get(8) + 1);
            }
        }
        int totalSum = 0;
        for(Integer i : fishLivingInDays.keySet()){
            totalSum += fishLivingInDays.get(i);
        }
        System.out.println(totalSum);
    }
}
