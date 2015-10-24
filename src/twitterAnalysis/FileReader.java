package twitterAnalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class FileReader {

    public static void twitterAdjancyList() throws Exception {
        FileInputStream twitterStream;
        String inFile = "twitter.txt";

        try {
            twitterStream = new FileInputStream(inFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader twitterReader = new BufferedReader(new InputStreamReader(twitterStream));
            String line;
            while ((line = twitterReader.readLine()) != null){
                
            }
                
            twitterReader.close();
            twitterStream.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
    }
}