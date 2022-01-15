package pl.ngalda.adventofcode2021.solutions.day2;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubmarinePilot {
    public static int calculatePosition() {
        List<String> instructionsAsStrings = FileReader.readLines("day2.txt");
        List<Instruction> instructions = mapInstructions(instructionsAsStrings);
        Map<Direction, Integer> directionToPositionChange = Map.of(Direction.DOWN, 1, Direction.UP, -1, Direction.FORWARD, 1);
        int horizontal = 0;
        int depth = 0;
        for (Instruction instruction : instructions) {
            if (instruction.direction.equals(Direction.FORWARD)) {
                horizontal += instruction.amount * directionToPositionChange.get(instruction.direction);
            } else {
                depth += instruction.amount * directionToPositionChange.get(instruction.direction);
            }
        }
        return horizontal * depth;
    }

    public static int calculatePositionWithAim() {
        List<String> instructionsAsStrings = FileReader.readLines("day2.txt");
        List<Instruction> instructions = mapInstructions(instructionsAsStrings);
        Map<Direction, Integer> directionToPositionChange = Map.of(Direction.DOWN, 1, Direction.UP, -1, Direction.FORWARD, 1);
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (Instruction instruction : instructions) {
            if (instruction.direction.equals(Direction.FORWARD)) {
                horizontal += instruction.amount * directionToPositionChange.get(instruction.direction);
                depth += aim * instruction.amount;
            } else {
                aim += instruction.amount * directionToPositionChange.get(instruction.direction);
            }
        }
        return horizontal * depth;
    }

    private static List<Instruction> mapInstructions(List<String> instructionsAsStrings) {
        return instructionsAsStrings
                .stream()
                .map(stringInstruction -> Instruction.builder()
                        .direction(Direction.valueOf(stringInstruction.split(" ")[0].toUpperCase()))
                        .amount(Integer.parseInt(stringInstruction.split(" ")[1]))
                        .build())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(calculatePosition());
        System.out.println(calculatePositionWithAim());
    }
}
