package com.mycompany.main.model.file;

// Io
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

// Scanner
import java.util.Scanner;

/**
 *
 * @author guilh
 */
public class FilePersistenceJSON {
    public void saveToFile(String text, String pathname) throws IOException {
        try (FileWriter file = new FileWriter(pathname);
            PrintWriter recordFile = new PrintWriter(file)) {
            recordFile.print(text);
        }
    }
    
    public String loadFromFile(String pathname) throws FileNotFoundException {
        File file = new File(pathname);
        if (!file.exists()) return "";

        try (Scanner reader = new Scanner(file)) {
            reader.useDelimiter("\\Z");
            return reader.hasNext() ? reader.next() : "";
        }
    }
}
