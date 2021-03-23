package com.example.mzaraj;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class DataTests {

    @Test
    public void shouldReadFirstLineFromFileAndCompereWithCorrectString() throws IOException {
        String expected_value = "Jan,";

        Path path = Paths.get("src/main/resources/files/maleList.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        assertEquals(expected_value, line);
    }

    @Test
    public void shouldReadFirstLineFromFileAndCompereWithNotCorrectString() throws IOException {
        String expected_value = "Marcin";

        Path path = Paths.get("src/main/resources/files/maleList.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        assertNotEquals(expected_value, line);
    }

    @Test
    public void shouldReadMenAndFemaleLists() throws IOException {
        String maleValue = "Jan,";
        String femaleValue = "Katarzyna,";

        Path pathMale = Paths.get("src/main/resources/files/maleList.txt");
        Path pathFemale = Paths.get("src/main/resources/files/femaleList.txt");

        BufferedReader readerM = Files.newBufferedReader(pathMale);
        String lineM = readerM.readLine();
        assertEquals(maleValue, lineM);

        BufferedReader readerFM = Files.newBufferedReader(pathFemale);
        String lineFm = readerFM.readLine();
        assertEquals(femaleValue, lineFm);
    }
}
