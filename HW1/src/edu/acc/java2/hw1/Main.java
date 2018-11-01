package edu.acc.java2.hw1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Write a Java program to print the average of all the ints in the file, the sum of
 * all the doubles, and the count of trues and the count of falses that occur in the
 * file <a href="http://www.bytecaffeine.com/acc/java/2/frags/day1.dat">day1.dat</a>.
 * 
 * The file contains ten thousand records where each record is an int followed by a
 * double followed by a boolean.
 * @author kaffn
 */
public class Main {

    public static void main(String[] args) {
        final String USAGE = "Usage: java -jar HW1.jar {path-to-day1.dat}";
        final int RECORD_COUNT = 10_000;
        
        if (args.length != 1) {
            System.out.println(USAGE);
            return;
        }
        
        double average = 0.0, sum = 0.0;
        int trues = 0, falses = 0;
        
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(args[0])))) {
            for (int n = 0; n < RECORD_COUNT; n++) {
                average += dis.readInt();
                sum += dis.readDouble();
                boolean b = dis.readBoolean();
                if (b) trues++; else falses++;
            }
        } catch (FileNotFoundException fnfe) {
            System.out.printf("No such file: %s\n\n%s\n", args[0], USAGE);
            return;
        } catch (IOException ioe) {
            System.out.printf("Error reading from file %s\n\n%s\n", args[0],
                    ioe.getMessage());
        }
        average /= RECORD_COUNT;
        System.out.printf("The average int was: %.2f\n", average);
        System.out.printf("The sum of doubles was: %f\n", sum);
        System.out.printf("There were %d trues and %d falses.\n", trues, falses);
    }
    
}
