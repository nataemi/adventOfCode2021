package pl.ngalda.adventofcode2021.solutions.day2;

import lombok.Builder;

@Builder
public class Instruction {
    Direction direction;
    int amount;
}
