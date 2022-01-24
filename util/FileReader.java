package pl.ngalda.adventofcode2021.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    private static InputStream readFile(final String fileName){
        InputStream ioStream = FileReader.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public static List<String> readLines(final String fileName)
    {
        List<String> lines = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(readFile(fileName));
             BufferedReader br = new BufferedReader(isr))
        {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<Integer> readLinesAsInt(final String fileName)
    {
        return readLines(fileName)
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public static List<Integer> readComaSeperatedValuesAsInt(final String fileName)
    {
        String line = null;
        try (InputStreamReader isr = new InputStreamReader(readFile(fileName));
             BufferedReader br = new BufferedReader(isr))
        {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line != null ? Arrays.stream(line.split(","))
                .map(Integer::valueOf)
                .toList() : null;
    }
}
