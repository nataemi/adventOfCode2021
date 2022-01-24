package pl.ngalda.adventofcode2021.solutions.day5;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.Arrays;
import java.util.List;

public class LinesOverlappingCalculator {

    public static void main(String[] args) {
        int[][] positions = new int[1000][1000];
        for(int i=0;i<positions.length;i++){
            for(int y=0;y<positions.length;y++){
                positions[i][y] = 0;
            }
        }
        List<String> lines = FileReader.readLines("day5.txt");
        for(String lineFromFile: lines){
            String[] coordinates = lineFromFile.split(" -> ");
            int x1 = Integer.parseInt(coordinates[0].split(",")[0]);
            int y1 = Integer.parseInt(coordinates[0].split(",")[1]);
            int x2 = Integer.parseInt(coordinates[1].split(",")[0]);
            int y2 = Integer.parseInt(coordinates[1].split(",")[1]);
            if(x1==x2){
                if(y1 < y2){
                    for(int i=y1;i<=y2;i++){
                        positions[x1][i]+=1;
                    }
                } else{
                    for(int i=y2;i<=y1;i++){
                        positions[x1][i]+=1;
                    }
                }
            } else if (y1 == y2){
                if(x1 < x2){
                    for(int i=x1;i<=x2;i++){
                        positions[i][y1]+=1;
                    }
                } else{
                    for(int i=x2;i<=x1;i++){
                        positions[i][y1]+=1;
                    }
                }
            }
        }
        for(int i=0; i<positions.length; i++){
            System.out.println(Arrays.toString(positions[i]));
        }
        int countOver1 = 0;
        for(int i=0; i<positions.length; i++){
            for(int j=0 ; j< positions.length; j++) {
                if(positions[i][j]>1) countOver1++;
            }
        }
        System.out.println(countOver1);
    }
}
