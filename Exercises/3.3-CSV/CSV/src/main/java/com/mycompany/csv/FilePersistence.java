package com.mycompany.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FilePersistence {
    public void saveToFile(String text, String filePath) {
        try {
            FileWriter file = new FileWriter(filePath);
            PrintWriter saveFile = new PrintWriter(file);
            saveFile.print(text);
            file.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public String loadFromFile(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            Scanner read = new Scanner(file);
            read.useDelimiter("\\Z");
            while (read.hasNext()) {
                content += read.next();
            }
            read.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
        return content;
    }
}
