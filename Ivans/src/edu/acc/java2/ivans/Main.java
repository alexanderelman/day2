package edu.acc.java2.ivans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar ivans.jar {path to a textfile");
            return;
        }
        
        int ivanCount = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words)
                    if (word.contains("Vodka"))
                        ivanCount++;
            }
        
            String verb = ivanCount == 1 ? "is" : "are";
            String plural = ivanCount == 1 ? "" : "s";
            System.out.printf("There %s %d occurrence%s of \"Vodka\" in %s\n",
                verb, ivanCount, plural, args[0] );
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
    
}

