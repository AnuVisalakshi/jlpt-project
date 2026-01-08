package com.test.anu.component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.test.anu.model.WordContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.test.anu.repository.JlptRepository;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JlptRepository repository;

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() == 0) {
            System.out.println("==== JLPT data import====");
            String csvPath = "src/main/resources/jlpt_vocab.csv";

            try (CSVReader reader = new CSVReaderBuilder(
                    new InputStreamReader(
                            new FileInputStream(csvPath), StandardCharsets.UTF_8)
            ).withSkipLines(1).build()
            ) {
                String[] columns;
                int rowCount = 0;

                while ((columns = reader.readNext()) != null) {
                    if (columns.length >= 4) {
                        WordContent rowData = new WordContent();
                        rowData.setKanji(columns[0]);
                        rowData.setFurigana(columns[1]);
                        rowData.setMeaning(columns[2]);

                        String levelFromFile = columns[3].trim().toUpperCase();
                        rowData.setLevel(levelFromFile);

                        repository.save(rowData);
                        rowCount++;
                    }
                }
                System.out.println("====Import finished ! Total: " + rowCount + " words saved to Docker");
            }
        } else {
            System.out.println("====Data already available====");
        }
    }
}
