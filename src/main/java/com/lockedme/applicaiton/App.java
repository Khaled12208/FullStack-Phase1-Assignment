package com.lockedme.applicaiton;

import java.util.*;

public class App {


    public static void main(String[] arg) {

        initApp();
        executeProgram();


    }

    private static void businessOptions() {

        System.out.println("=========Business Options Menu========");

        String[] arr = {"\n1. Add new user file ",
                "2. Delete user file",
                "3. Search for user file",
                "4. Rename a file",
                "5. Delete all files",
                "6. Back to main menu",
                "7. Close the application"
        };
        int[] arr1 = {1, 2, 3, 4, 5,6,7};
        int slen = arr1.length;
        for (int i = 0; i < slen; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("\nEnter your choice:\t");
        FileHandling fHandler = new FileHandling();
        Integer options = null;
        try {
            Scanner sc = new Scanner(System.in);
            options = sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.println(" Please Enter Valid Option with numeric values from [1-6]");
            businessOptions();
        }
            if (options<=arr1.length && options !=0) {
                switch (options) {
                    case 1:
                        fHandler.addFile();
                        businessOptions();
                        break;
                    case 2:
                        fHandler.deleteFile();
                        businessOptions();
                        break;
                    case 3:
                        fHandler.findFile();
                        businessOptions();
                        break;
                    case 4:
                        fHandler.renameFile();
                        businessOptions();
                        break;
                    case 5:
                        fHandler.deleteAllFiles();
                        businessOptions();
                        break;
                    case 6:
                        System.out.println("=========Back To Main========");
                        executeProgram();
                        break;
                    case 7:
                        closeApp();
                        break;
                    default:
                        System.out.println("You have made an invalid choice!");
                        break;
                }
            } else {
                System.out.println("You have made an invalid choice!");
                businessOptions();
            }
        }

    private static void executeProgram() {
        System.out.println("======== Main Options Menu=======");

        String[] arr = {"\n1. Lists all User Files ",
                "2. list number of files",
                "3. Business Operations",
                "4. Close App"
        };
        int[] arr1 = {1, 2, 3, 4};
        int slen = arr1.length;
        for (int i = 0; i < slen; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("\nEnter your choice:\t");
        FileHandling fHandler = new FileHandling();
        Integer option = null;
        try {
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.println(" Please Enter Valid Option with numeric values from [1-3]");
            businessOptions();
        }
        if (option<=arr1.length && option !=0) {
            switch (option) {
                case 1:
                    System.out.println("========All Database Files=======");
                    fHandler.getListOfFilesNames();
                    System.out.println("=================================");
                    executeProgram();
                    break;
                case 2:
                    fHandler.getNumberOfExistFiles();
                    executeProgram();
                    break;
                case 3:
                    businessOptions();
                    break;
                case 4:
                    closeApp();
                    break;
                default:
                    System.out.println("You have made an invalid choice!");
                    executeProgram();
                    break;
            }
        } else {
            System.out.println("You have made an invalid choice!");
            executeProgram();
        }
    }

    private static void closeApp() {
        System.out.println("Closing your application... \nThank you!");
    }

    private static void initApp() {
        String[] arr = {"******* Welcome *******",
                "LockedMe.Com application",
                "Developed By: Khaled M. Farh",
        };
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
