package pl.ngalda.adventofcode2021.solutions.day6;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class LanternFishSimulator {
    public static void main(String[] args) {
        int dayCount = 256;
        List<Integer> lanternFishDays = FileReader.readComaSeperatedValuesAsInt("day6.txt");
        BigInteger[] fishLivingInDays = new BigInteger[9];
        for (int j = 0; j <= 8; j++) {
            fishLivingInDays[j] = BigInteger.ZERO;
        }

        for (int daysLeft : lanternFishDays) {
            fishLivingInDays[daysLeft] = fishLivingInDays[daysLeft].add(BigInteger.ONE);
        }

        for (int i = 0; i < dayCount; i++) {
            BigInteger zeros = fishLivingInDays[0];
            for (int j = 0; j <= 7; ++j) {
                fishLivingInDays[j] = fishLivingInDays[j + 1];
            }
            fishLivingInDays[6] = fishLivingInDays[6].add(zeros);
            fishLivingInDays[8] = zeros;
        }
        BigInteger totalSum = BigInteger.ZERO;
        for (int i = 0; i < fishLivingInDays.length; i++) {
            totalSum = totalSum.add(fishLivingInDays[i]);
        }
        System.out.println(Arrays.stream(fishLivingInDays).reduce(BigInteger.ZERO, BigInteger::add));
    }
}
