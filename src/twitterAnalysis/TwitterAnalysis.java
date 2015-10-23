package twitterAnalysis;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwitterAnalysis {
    
    public static void main (String inFile, String outFile){
        FileInputStream tdfStream;
        Scanner in = new Scanner(System.in);
        
        try {
            tdfStream = new FileInputStream("tdf.txt");
        } catch (FileNotFoundException e) {
        


        // TODO: Duplicate queries should be ignored.
        // TODO: All queries end with ?; lines in the query input file that do not end with a ? can also be ignored.
        
        .close(); 
    }
}
