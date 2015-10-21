package twitterAnalysis;
import java.util.Scanner;

public class TwitterAnalysis {
    
    public static void main (String inFile, String outFile){
        Scanner file = new Scanner(System.in);
        System.out.print("File name (blank for empty): ");
        String url = file.nextLine().trim();

        // TODO: Duplicate queries should be ignored.
        // TODO: All queries end with ?; lines in the query input file that do not end with a ? can also be ignored.
        
    }
}
