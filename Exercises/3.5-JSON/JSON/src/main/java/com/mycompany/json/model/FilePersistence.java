package com.mycompany.json.model;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FilePersistence {
    public void saveToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(new File(filePath))) {
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter("\\Z");
            while (scanner.hasNext()) {
                content.append(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
