package pl.ngalda.adventofcode2021.solutions.day4;

import io.vavr.Tuple2;
import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BingoGameplay {

    static List<String> numbersToDraw;
    static List<BingoBoard> boards = new ArrayList<>();

    static void initializeBingo() {
        List<String> lines = FileReader.readLines("day4.txt");
        numbersToDraw = List.of(lines.get(0).split(","));
        BingoBoard bingoBoard = new BingoBoard();
        int positionRow = 0;
        for (int i = 2; i < lines.size(); i++) {
            if ("".equals(lines.get(i))) {
                positionRow = -1;
                boards.add(bingoBoard);
                bingoBoard = new BingoBoard();
            } else {
                List<String> numberOnBoard = List.of(lines.get(i).split("\\s+"));
                numberOnBoard = numberOnBoard.stream()
                        .filter(numberString -> !numberString.isEmpty())
                        .collect(Collectors.toList());
                for (int j = 0; j < numberOnBoard.size(); j++) {
                    bingoBoard.getBingoBoardFieldList()
                            .add(BingoBoardField.builder()
                                    .positionColumn(j)
                                    .positionRow(positionRow)
                                    .crossed(false)
                                    .number(numberOnBoard.get(j))
                                    .build());
                }
            }
            positionRow++;
        }
        boards.add(bingoBoard);

    }

    static void play() {
        for (String drawnNumber : numbersToDraw) {
            crossFields(drawnNumber);
            for (BingoBoard bingoBoard : boards) {
                checkRows(bingoBoard, drawnNumber);
                checkColumns(bingoBoard, drawnNumber);
            }
        }
    }

    private static void printOnWin(BingoBoard bingoBoard, String drawnNumber, boolean bingo) {
        if (bingo) {
            System.out.println("Bingo on number:" + drawnNumber + "sum of allNonMarkedNumbers " +
                    calculateNonCrossedFieldsSum(bingoBoard) +
                    "answer: " + (Integer.parseInt(drawnNumber) * calculateNonCrossedFieldsSum(bingoBoard)));
        }
    }

    private static void checkColumns(BingoBoard bingoBoard, String drawnNumber) {
        for (int columnNumber = 0; columnNumber < 5; columnNumber++) {
            boolean bingoOnRow = true;
            for (BingoBoardField bingoBoardField : bingoBoard.getBingoBoardFieldList()) {
                if (columnNumber == bingoBoardField.getPositionColumn() && !bingoBoardField.isCrossed()) {
                    bingoOnRow = false;
                    break;
                }
            }
            printOnWin(bingoBoard, drawnNumber, bingoOnRow);
        }
    }

    private static void checkRows(BingoBoard bingoBoard, String drawnNumber) {
        for (int rowNumber = 0; rowNumber < 5; rowNumber++) {
            boolean bingoOnRow = true;
            for (BingoBoardField bingoBoardField : bingoBoard.getBingoBoardFieldList()) {
                if (rowNumber == bingoBoardField.getPositionRow() && !bingoBoardField.isCrossed()) {
                    bingoOnRow = false;
                    break;
                }
            }
            printOnWin(bingoBoard, drawnNumber, bingoOnRow);
        }
    }

    private static int calculateNonCrossedFieldsSum(BingoBoard bingoBoard) {
        return bingoBoard.getBingoBoardFieldList()
                .stream()
                .filter(field -> !field.isCrossed())
                .map(BingoBoardField::getNumber)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    private static void crossFields(String drawnNumber) {
        for (BingoBoard bingoBoard : boards) {
            for (BingoBoardField bingoBoardField : bingoBoard.getBingoBoardFieldList()) {
                if (drawnNumber.equals(bingoBoardField.getNumber())) {
                    bingoBoardField.setCrossed(true);
                }
            }
        }
    }

    public static void main(String[] args) {
        initializeBingo();
        play();
    }
}
