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
    public void saveToFile(String text, String pathname) {
        try {
            FileWriter file = new FileWriter(pathname);
            PrintWriter recordFile = new PrintWriter(file);
            recordFile.print(text);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String loadFromFile(String pathname) throws FileNotFoundException {
        String contentRead = "";
        try {
            File file = new File(pathname);
            Scanner reader = new Scanner(file);

            reader.useDelimiter("\\Z");
            while(reader.hasNext()) {
                contentRead += reader.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contentRead;
    }
}
