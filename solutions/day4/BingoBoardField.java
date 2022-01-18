package pl.ngalda.adventofcode2021.solutions.day4;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BingoBoardField {
    String number;
    boolean crossed;
    int positionRow;
    int positionColumn;
}
