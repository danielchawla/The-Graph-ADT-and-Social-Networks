package twitterAnalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class fileReader {
    FileInputStream twitterStream;
    Scanner in = new Scanner(System.in);
    
    try {
        twitterStream = new FileInputStream("twitter.txt");
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
    try {
        // We will use a BufferedReader to read the data from the file.
        BufferedReader twitterReader = new BufferedReader(
                new InputStreamReader(twitterStream));
        String line;
        while ((line = twitterReader.readLine()) != null) {
            
        }
        twitterReader.close();
        twitterStream.close();
    }
}
