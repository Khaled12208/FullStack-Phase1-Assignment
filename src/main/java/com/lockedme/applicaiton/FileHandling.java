package com.lockedme.applicaiton;

import javafx.beans.binding.When;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandling {

    private final String dtaBaseURL = System.getProperty("user.dir")+"\\src\\main\\resources\\com\\lockedme\\application\\dataBase\\";
    private final File folder = new File(dtaBaseURL);

    public void getListOfFilesNames() {
        List<String> filesNames = new LinkedList<>();
        // get List of all files and excludes directories
        File[] listFiles = listFilesForFolder(folder);
        if (listFiles.length==0)
        {
            System.out.println("User Directory is empty .. No files is available in the data base");
        }else {
            // Sort files by name
            Arrays.sort(listFiles, new Comparator() {
                @Override
                public int compare(Object f1, Object f2) {
                    return ((File) f1).getName().compareTo(((File) f2).getName());
                }
            });
            for (File file : listFiles) {
                filesNames.add(file.getName());
            }
            filesNames.forEach((fileName) -> System.out.println(fileName));
        }

    }

    public void addFile() {
        System.out.println("\nPlease Enter File Name\t");
        String fileName=getFileName();
        File file = new File(dtaBaseURL + fileName);
        if (searchForFile(fileName)) {
            System.out.println("File is already exist in our data base with Name : " + fileName);
        } else {
            try {
                file.createNewFile();
                System.out.println("File Created !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteFile() {
        System.out.println("\nPlease Enter File Name\t");
        String fileName=getFileName();
        File file = new File(dtaBaseURL + fileName);
        if (searchForFile(fileName)) {
            file.delete();
            System.out.println("File deleted successfully ");

        } else {

            System.out.println("File is not exist in the database");

        }
    }

    public void findFile() {
        System.out.println("\nPlease Enter your File Name\t");
        String fileName=getFileName();
        if (searchForFile(fileName)) {
            System.out.println("File " + fileName + " is exist in our data base");
        } else {
            System.out.println("File " + fileName + " is NOT exist in our data base");

        }

    }

    public void renameFile() {
        System.out.println("\nPlease enter the file current name\t");
        String fileName=getFileName();
        if (!searchForFile(fileName)) {
            System.out.println("File " + fileName + " is Not exist in our data base");
        }else {

            File oldFile = new File(dtaBaseURL + fileName);
            System.out.println("\nPlease enter your file new name\t");
            String fileNewName= getFileName();
            File newFile = new File(dtaBaseURL + fileNewName);
            if (oldFile.renameTo(newFile))
            {
                System.out.println("File Name has changed from: " + fileName + " to: "+fileNewName + " Successfully" );
            }
        }

    }

    public void getNumberOfExistFiles() {
        File[] listFiles = listFilesForFolder(folder);
        if (listFiles.length == 0) {
            System.out.println("User Directory is empty .. {0} files are available in the data base");
        }else
        {
            System.out.println("{"+listFiles.length+"} files are available in the database");

        }
    }

    public void deleteAllFiles() {

        System.out.println("Are you sure you want to delete all files press [y] to continue or [n]");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        if (fileName.trim().equalsIgnoreCase("y")) {
            for (File file : folder.listFiles())
                if (!file.isDirectory())
                    file.delete();
                System.out.println(" All files was deleted .. ");
        }else
        {
            System.out.println(" Operation Aborted ");

        }
    }

    private boolean searchForFile(String fileName) {
        File file = new File(dtaBaseURL + fileName);
        File[] listFiles = listFilesForFolder(folder);
        Boolean existFile = false;
        for (File fTemp : listFiles) {
            if (fTemp.getName().equals(file.getName())) {
                existFile = true;
            }
        }

        return existFile;
    }
    private File[] listFilesForFolder(final File folder) {
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.isDirectory();
            }
        };
        File[] listOfFiles = folder.listFiles(fileFilter);

        return listOfFiles;

    }
    private boolean checkFileExtension(String fileName) {
        Pattern p = Pattern.compile("^.*\\.[a-zA-Z]+$");
        Matcher m = p.matcher(fileName);
        return m.matches();
    }
    private String getFileName() {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        Boolean fileCheck= checkFileExtension(fileName);
        while (!fileCheck) {
            System.out.println("Invalid file name: " + fileName + " \n we cannot create or modify directories \n please enter a valid file extension *.[a-zA-Z]");
            System.out.println("\nPlease Enter your File Name\t");
            fileName = sc.nextLine();
            fileCheck = checkFileExtension(fileName);
        }
        return fileName;
    }

}
