package edu.acc.java2.hw2;

import java.io.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Download the staff.csv file, which contains records for all the employees of Widgets, Inc.
 * The file is in csv format and has been massaged for your convenience. The columns are defined
 * on line one and all subsequent lines are employee records.
 * Write a Java program to load the data set into an in-memory collection and use the collection
 * to answer the following questions:
 *
 * How many employees earned over $100,000 last year?
 * What was Widgets, Inc.'s gross payroll expenditure last year?
 * How many chief executives did Widgets, Inc. employ last year?
 * Which employees took no vacation last year?
 * Which employees used all their vacation last year?
 * How much did Widgets, Inc. deduct from employee earnings for taxes last year (at a 17% tax rate)?
 * How much did Widgets, Inc. pay out in bonuses last year?
 *
 * @author alexanderelman
 */
public class Main {

    public static void main(String[] args) {
        final String USAGE = "Usage: java -jar HW2.jar {path-to-staff.csv}";

        if (args.length != 1) {
            System.out.println(USAGE);
            return;
        }
        String[] linearray;
        String line;
        Map<String, ArrayList<String>> staffMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            // Read the column headers from the first line
            String[] columns = reader.readLine().split(",");
            for (int i=0; i<columns.length; i++) {
                staffMap.put(columns[i], new ArrayList<>()); // create array lists
            }
            line = reader.readLine();
            while (line != null) {
                linearray = line.split(",");
                for (int i=0; i<columns.length; i++) {
                    staffMap.get(columns[i]).add(linearray[i]);
                }
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException filenotfoundexception) {
            System.out.printf("File %s could not be found. \n\n%s\n", args[0], USAGE);
        }
        catch (IOException ioexception) {
            System.out.printf("IOException trying to access file %s" +
                    "\n\n %s\n", args[0], USAGE);
        }
        // Employees who earned over $100,000 in the last year
        // get iterator
        ListIterator<String> wagesIterator = staffMap.get("Gross Wages").listIterator();
        Integer highlyPaidCount = 0;
        Integer totalPaid = 0;
        Integer wage;
        while (wagesIterator.hasNext()) {
            wage = Integer.parseInt(wagesIterator.next());
            if( wage > 100000 ) {
                ++highlyPaidCount;
            }
            totalPaid += wage;
        }
        System.out.printf("%d employees earned more than $100,000.\n\n",highlyPaidCount);
        System.out.printf("The total gross payroll expenditure for Widgets, Inc was $%d.\n\n",totalPaid);

        // Number of CEOs
        ListIterator<String> titleIterator = staffMap.get("Title").listIterator();
        Integer ceoCount = 0;
        while (titleIterator.hasNext()) {
            if (titleIterator.next().equals("Chief Executive Officer")) {
                ++ceoCount;
            }
        }
        System.out.printf("The number of CEOs employed by Widgets, Inc was %d.\n\n",ceoCount);

        // Employees who took no vacation last year
        ListIterator<String> vacationSpentIterator = staffMap.get("Vacation Spent").listIterator();
        ListIterator<String> vacationEarnedIterator = staffMap.get("Vacation Earned").listIterator();
        Integer vacationSpent;
        String firstName;
        String lastName;
        while (vacationSpentIterator.hasNext()){
            vacationSpent = Integer.parseInt(vacationSpentIterator.next());
            firstName = staffMap.get("First").get(vacationSpentIterator.previousIndex());
            lastName =  staffMap.get("Last").get(vacationSpentIterator.previousIndex());
            if (vacationSpent == 0) {
                System.out.printf("%s %s didn't take any vacation last year. Bummer.\n", firstName, lastName);
            }
            if (vacationSpent >= Integer.parseInt(staffMap.get("Vacation Earned")
                    .get(vacationSpentIterator.previousIndex()))) {
                System.out.printf("%s %s exhausted all of their vacation last year.\n", firstName, lastName);
            }
        }

        // Total bonuses
        ListIterator<String> bonusIterator = staffMap.get("Bonused").listIterator();
        Double totalBonuses = 0.0;
        while (bonusIterator.hasNext()) {
            if (bonusIterator.next().equals("Y")) {
                totalBonuses += Integer.parseInt(staffMap.get("Bonus %").get(bonusIterator.previousIndex())) *
                        Integer.parseInt(staffMap.get("Gross Wages").get(bonusIterator.previousIndex())) / 100.0;
            }
        }
        System.out.printf("Total bonuses paid out by Widgets, Inc was $%.2f\n\n", totalBonuses);

        Double totalDeductions = (totalBonuses + totalPaid) * 17 / 100.0;
        System.out.printf("Widgets, Inc. deducted a total amount of $%.2f from earnings last year at a 17%% rate.",
                totalDeductions);
    }
}
