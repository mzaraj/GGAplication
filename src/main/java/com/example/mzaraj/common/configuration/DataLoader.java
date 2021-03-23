package com.example.mzaraj.common.configuration;

import com.example.mzaraj.constance.GenderConstants;
import com.example.mzaraj.entity.GuessGenderEntity;
import com.example.mzaraj.repository.GuessGenderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
@Component
public class DataLoader {

    @Autowired
    private final GuessGenderRepository guessGenderRepository;

    @PostConstruct
    private void postConstruct() {
        loadData("src/main/resources/files/maleList.txt", GenderConstants.MALE);
        loadData("src/main/resources/files/femaleList.txt", GenderConstants.FEMALE);
    }

    public void loadData(String filePath, String gender) {

        Path path = Paths.get(filePath);
        try {
            BufferedReader firstReader = Files.newBufferedReader(path);
            BufferedReader secondReader = Files.newBufferedReader(path);

            String line;
            String nextLine = secondReader.readLine();

            while (nextLine != null) {
                line = firstReader.readLine();
                nextLine = secondReader.readLine();

                if (nextLine != null) {
                    line = line.substring(0, line.length() - 1);
                    guessGenderRepository.save(GuessGenderEntity.of(line.toUpperCase(), gender));
                    System.out.println("załadowano imie: " + line);
                } else {
                    guessGenderRepository.save(GuessGenderEntity.of(line.toUpperCase(), gender));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
