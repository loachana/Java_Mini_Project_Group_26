package com.journaldev.csv.model;

import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static String[] DataEntryWindow() {


        String[] DataArray = new String[2];

        Scanner scan = new Scanner(System.in);

        System.out.print("Name: ");
        DataArray[0] = scan.nextLine();

        System.out.print("Age: ");
        DataArray[1] = scan.nextLine();

        return DataArray;
    }

    public static int InterfaceMainMenu() {

        Scanner scan = new Scanner(System.in);

        System.out.println("The Student Management System");
        System.out.println("------------------------------");
        System.out.println("1 - To view current database");
        System.out.println("2 - To add new entry");
        System.out.println("3 - To remove existing entry");

        System.out.print("-->> ");

        int SelectionOption = scan.nextInt();

        System.out.println(SelectionOption);

        return SelectionOption;
    }

    public static void readData() {
        // specifying the CSV file path
        String filePath = "database.csv";

        // creating File object with the file location specified
        File file = new File(filePath);

        try {
            // creating FileReader object with file as parameter
            FileReader fileReader = new FileReader(file);

            // creating CSVReader object using FileReader object
            CSVReader csvReader = new CSVReader(fileReader);

            // creating the list (containing both header and data) using String array
            List<String[]> data = csvReader.readAll();

            // printing data
            for (String[] row : data) {
                for (String cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }



    public static void Writer(String name, String age) {

        String csvFile = "database.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {

            String[] Details = {name, age};

            writer.writeNext(Details);

        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        int SwitchNumber = InterfaceMainMenu();

        switch (SwitchNumber) {
            case 1:
                System.out.println("Copy 1");
                break;
            case 2:
                String[] DataList = DataEntryWindow();
                Writer(DataList[0], DataList[1]);
                break;
        }






        //Writer("Nandana","60");
        //readData();

        /*
        System.out.println("The Student Management System");
        System.out.println("------------------------------");
        System.out.println("1 - To view current database");
        System.out.println("2 - To add new entry");
        System.out.println("3 - To remove existing entry");


        String csvFile = "database.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {

            //String[] header = {"ID", "Name", "Age", "Country"};
            //writer.writeNext(header);

            String[] entry1 = {"a", "b", "c", "d"};
            writer.writeNext(entry1);

            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }
}