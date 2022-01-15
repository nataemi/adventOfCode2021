package pl.ngalda.adventofcode2021.solutions.day1;

import pl.ngalda.adventofcode2021.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DeepnessMeasurementCalculator {

    public static int calculateDepths(){
        List<Integer> deepness = FileReader.readLinesAsInt("day1.txt");
        return getDeepnessIncreasedCounter(deepness);
    }

    private static int getDeepnessIncreasedCounter(List<Integer> deepness) {
        int deepnessIncreasedCounter = 0;
        for(int i = 0; i< deepness.size(); i++){
            if(i>0){
                if (deepness.get(i-1) < deepness.get(i)){
                    deepnessIncreasedCounter++;
                }
            }
        }
        return deepnessIncreasedCounter;
    }

    public static int calculateDepthsSumsOfThree(){
        List<Integer> deepness = FileReader.readLinesAsInt("day1.txt");
        List<Integer> sumsOfThree = new ArrayList<>();
        for(int i=0 ; i< deepness.size(); i++){
            if(i>1){
                sumsOfThree.add(deepness.get(i) + deepness.get(i-1) + deepness.get(i-2));
            }
        }
        return getDeepnessIncreasedCounter(sumsOfThree);
    }

    public static void main(String[] args) {
        System.out.println(calculateDepths());
        System.out.println(calculateDepthsSumsOfThree());
    }
}
