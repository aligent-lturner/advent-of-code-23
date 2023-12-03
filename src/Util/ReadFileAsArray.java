package Util;

import java.io.*;
import java.util.ArrayList;

public class ReadFileAsArray {

    public static ArrayList<String> execute(String filePath) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(fileReader);
        ArrayList<String> fileData = new ArrayList<>();
        try {
            String data = reader.readLine();
            while (data != null) {
                fileData.add(data);
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileData;
    }
}
