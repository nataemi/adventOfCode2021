package pl.ngalda.adventofcode2021.solutions.day5;

import lombok.Builder;

@Builder
public class Line {
    Integer a;
    Integer b;
    Integer x;

    static Line calculateLineFromPoints(int x1, int y1, int x2, int y2) {
        if((x2-x1) != 0) {
            int a = (y2 - y1) / (x2 - x1);
            return Line.builder()
                    .a(a)
                    .b(y1 - a * x1)
                    .build();
        } else{
            return workAroundForWhenLineHasOnlyX(x1);
        }

    }
    // example: (2;2) and (2;1)
    private static Line workAroundForWhenLineHasOnlyX(int x1) {
        return Line.builder()
                .x(x1)
                .build();
    }

    boolean checkIfPointInLine(int x, int y) {
        return (this.x == null) ? (y == a * x + b) : x == this.x;
    }
}
