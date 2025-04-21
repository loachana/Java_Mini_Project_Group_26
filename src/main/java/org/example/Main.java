package com.journaldev.csv.model;


import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.*;


public class Main {

    public static void SearchByID() {

        String database = "database.csv";

        Scanner scan = new Scanner(System.in);

        System.out.print("ID: ");

        int UniqueID = scan.nextInt();

        try ( CSVReader reader = new CSVReader(new FileReader(database))
        ) {
            List<String[]> allRows = reader.readAll();

            //System.out.println(allRows.get(0)[0]);

            AsciiTable at = new AsciiTable();

            at.addRule();
            at.addRow("ID", "FIRST NAME","SURNAME", "AGE", "SCHOOL GRADE", "Birth Day [MM/DD]", "GENDER [M/F]");

            for (String[] row: allRows) {

                //System.out.println(row[0]);

                int IntRow = Integer.parseInt(row[0]);

                if(UniqueID == IntRow) {

                    //System.out.println(row[0]);
                    //System.out.println(row[1]);
                    //System.out.println(row[2]);

                    at.addRule();
                    at.addRow(row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
                    at.addRule();

                    at.setTextAlignment(TextAlignment.CENTER);

                    String rend = at.render();
                    System.out.println(rend);

                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }


    }

    public static void ModifyData() throws FileNotFoundException{

        String database = "database.csv";

        Scanner scan = new Scanner(System.in);


        System.out.print("ID to Modify: ");
        int EditID = scan.nextInt();

        String[] Updated = new String[7];

        scan.nextLine();

        /*
        System.out.print("New Name: ");
        Updated[1] = scan.nextLine();

        System.out.print("New Age: ");
        Updated[2] = scan.nextLine();
        */

        try ( CSVReader reader = new CSVReader(new FileReader(database))
        ) {
            List<String[]> allRows = reader.readAll();

            //String CurrentID = allRows.get(EditID-1)[0];

            //System.out.println(CurrentID);


            //for (String[] row : data)

            int RowIndex = 0;

            for (String[] row : allRows){
                //System.out.println(row[0]);


                int IntRow = Integer.parseInt(row[0]);
                if(IntRow == EditID) {
                    //System.out.println(row[1]);

                    Updated[0] = String.valueOf(EditID);

                    System.out.print("New First Name: ");
                    Updated[1] = scan.nextLine();

                    System.out.print("New Surname: ");
                    Updated[2] = scan.nextLine();

                    System.out.print("New Age: ");
                    Updated[3] = scan.nextLine();

                    System.out.print("New School Grade: ");
                    Updated[4] = scan.nextLine();

                    System.out.print("New Birth Day [MM/DD]: ");
                    Updated[5] = scan.nextLine();

                    System.out.print("New Gender [M/F]: ");
                    Updated[6] = scan.nextLine();

                    allRows.get(RowIndex)[0] = Updated[0];
                    allRows.get(RowIndex)[1] = Updated[1];
                    allRows.get(RowIndex)[2] = Updated[2];
                    allRows.get(RowIndex)[3] = Updated[3];
                    allRows.get(RowIndex)[4] = Updated[4];
                    allRows.get(RowIndex)[5] = Updated[5];
                    allRows.get(RowIndex)[6] = Updated[6];

                }
                //System.out.println(RowIndex);

                RowIndex = RowIndex + 1;

            }


            //Updated[0] = CurrentID;

            //allRows.set(EditID - 1, Updated);

            try(CSVWriter writer = new CSVWriter(new FileWriter(database));
            ) {
                writer.writeAll(allRows);
            }

        } catch( IOException e) {
            e.printStackTrace();
        } catch (CsvException e){
            e.printStackTrace();
        }


    }

    public static void DeleteData() throws CsvException{

        String database = "database.csv";

        Scanner scan = new Scanner(System.in);

        System.out.print("ID to delete: ");
        int DeleteID = scan.nextInt();



        try(CSVReader reader = new CSVReader(new FileReader(database));
        ){

            List<String[]> allRows = reader.readAll();

            int RowIndex = 0;

            for (String[] row : allRows){
                //System.out.println(row[0]);


                int IntRow = Integer.parseInt(row[0]);
                if(IntRow == DeleteID) {
                    //System.out.println(row[1]);

                    //System.out.println("yay");
                    allRows.remove(RowIndex);

                }
                //System.out.println(RowIndex);

                RowIndex = RowIndex + 1;

            }


            //System.out.println(allRows.get(8)[1]);
            //System.out.println(allRows.size());
            /*
            if ( LineNumber <= allRows.size()) {

                allRows.remove(LineNumber - 1);
            } else {
                System.out.println("Out of range");

            }
            */
            try (
                CSVWriter writer = new CSVWriter(new FileWriter(database));
            ){

                writer.writeAll(allRows);

                System.out.println("Entry Deleted");

            }

        }catch (IOException e){

            e.printStackTrace();

        }


    }

    public static String[] DataEntryWindow() {

        String filePath = "database.csv";

        // creating File object with the file location specified
        File file = new File(filePath);

        String LastID = "1";

        try {
            // creating FileReader object with file as parameter
            FileReader fileReader = new FileReader(file);

            // creating CSVReader object using FileReader object
            CSVReader csvReader = new CSVReader(fileReader);

            // creating the list (containing both header and data) using String array
            List<String[]> data = csvReader.readAll();

            //System.out.println(data.getLast()[0]);

            LastID = data.getLast()[0];


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(LastID);

        String[] DataArray = new String[7];

        Scanner scan = new Scanner(System.in);

        //int IntID = 1;

        int LastIDInt = Integer.parseInt(LastID);

        //System.out.println(LastIDInt+4);

        int NewIDInt = LastIDInt + 1;

        String NewID = String.valueOf(NewIDInt);

        DataArray[0] = NewID;

        System.out.print("First Name: ");
        DataArray[1] = scan.nextLine();

        System.out.print("Surname: ");
        DataArray[2] = scan.nextLine();

        System.out.print("Age: ");
        DataArray[3] = scan.nextLine();

        System.out.print("School Grade: ");
        DataArray[4] = scan.nextLine();

        System.out.print("Birth Day [MM/DD]: ");
        DataArray[5] = scan.nextLine();

        System.out.print("Gender [M/F]: ");
        DataArray[6] = scan.nextLine();

        return DataArray;
    }

    /*
    public static String[] DataEntryWindow() {


        String[] DataArray = new String[3];

        Scanner scan = new Scanner(System.in);

        int IntID = 1;

        String StringID = String.valueOf(IntID);

        DataArray[0] = StringID;

        System.out.print("Name: ");
        DataArray[1] = scan.nextLine();

        System.out.print("Age: ");
        DataArray[2] = scan.nextLine();

        return DataArray;
    } */



    public static int InterfaceMainMenu() {

        Scanner scan = new Scanner(System.in);

        System.out.println("\nThe Student Management System");
        System.out.println("------------------------------");
        System.out.println("1 - View Current Database");
        System.out.println("2 - Add New Entry");
        System.out.println("3 - Remove Entry");
        System.out.println("4 - Modify Entry");
        System.out.println("5 - Search by ID");
        System.out.println("0 - Abort");

        System.out.print("-->> ");

        int SelectionOption = scan.nextInt();

        //System.out.println(SelectionOption);

        return SelectionOption;
    }


    public static void ReadData() {
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


            AsciiTable at = new AsciiTable();

            /*
            at.addRule();
            at.addRow("1", "2");
            at.addRule();
            at.addRow("4", "5");
            at.addRule();

            String rend = at.render();
            System.out.println(rend);
            */

            // printing data
            at.addRule();
            at.addRow("ID", "FIRST NAME", "SURNAME", "AGE", "SCHOOL GRADE", "BIRTH DAY [MM/DD]", "GENDER [M/F]");
            for (String[] row : data) {

                at.addRule();
                at.addRow(row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
                //at.addRule();
                //System.out.println(row[0] + row[1]);


                //for (String TheList : row){
                //    System.out.println(TheList);
                //}
            }
            at.addRule();

            at.setTextAlignment(TextAlignment.CENTER);

            String rend = at.render();
            System.out.println(rend);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }




    public static void Writer(String ID, String FirstName, String Surname, String age, String SchoolGrade, String BirthDay, String Gender) {

        String csvFile = "database.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {

            String[] Details = {ID, FirstName, Surname, age, SchoolGrade, BirthDay, Gender};

            writer.writeNext(Details);

            System.out.println("Data saved\n");

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public static void Writer(String name, String age) {

        String csvFile = "database.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {

            String[] Details = {name, age};

            writer.writeNext(Details);

            System.out.println("Data saved\n");

        }catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public static void main(String[] args) throws CsvException, FileNotFoundException {

        while(true) {
            int SwitchNumber = InterfaceMainMenu();


            if (SwitchNumber == 0) {

                System.out.println("Program aborting");
                break;

            }
            switch (SwitchNumber) {
                case 1:
                    ReadData();
                    break;
                case 2:
                    String[] DataList = DataEntryWindow();
                    Writer(DataList[0], DataList[1], DataList[2], DataList[3], DataList[4], DataList[5], DataList[6]);
                    break;
                case 3:
                    DeleteData();
                    break;
                case 4:
                    ModifyData();
                    break;
                case 5:
                    SearchByID();
                    break;
                default:
                    System.out.println("--Input Error--");
            }

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