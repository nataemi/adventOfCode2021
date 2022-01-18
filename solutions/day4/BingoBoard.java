package pl.ngalda.adventofcode2021.solutions.day4;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BingoBoard {
    List<BingoBoardField> bingoBoardFieldList = new ArrayList<>();
    boolean won;
}
