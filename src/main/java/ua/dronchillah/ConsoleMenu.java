package ua.dronchillah;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleMenu {
    final static Scanner scan = new Scanner(System.in);
    public static void start() {
        String menu = "\nChoose one of next options: " +
                "\n 1.View all entries" +
                "\n 2.Create entry" +
                "\n 3.Update entry" +
                "\n 4.Delete entry" +
                "\n 5.Stop application";
        String startMenu = "Welcome to Dronchillah's CRUD application " + menu;
        System.out.println(startMenu);
            String input = scan.nextLine();
            bigLoopa:
            while (!input.equalsIgnoreCase("q")) {
                switch (input) {
                    case "1":
                        viewAllEntries();
                        break;
                    case "2":
                        create();
                        break;
                    case "3":
                        update();
                        break;
                    case "4":
                        delete();
                        break;
                    case "5":
                        System.out.println("bye");
                        break bigLoopa;
                    default:
                        System.out.println("Choose correct number of option:");
                }
                System.out.println(menu);
                input = scan.nextLine();
            }
    }

    private static void create() {
        System.out.println("Input username:");
        String username = scan.nextLine();
        System.out.println("Input password:");
        String password = scan.nextLine();
        System.out.println("Input email:");
        String email = checkEmail(scan.nextLine());
        Actions.create(username, password, email);
    }

    private static void update(){
        viewAllEntries();
        System.out.println("Please, choose number of record to edit:");
        int id = checkId(scan.nextLine());
        System.out.println("Chosen record:");
        Actions.viewCurrentEntry(id);
        System.out.println("Choose option to edit: " +
                "\n1.Edit username" +
                "\n2.Edit password" +
                "\n3.Edit email" +
                "\n4.Quit");
        String input = scan.nextLine();
        bigLoopa:
        while (!input.equalsIgnoreCase("q")) {
            switch (input) {
                case "1":
                    System.out.println("Please, input new username:");
                    String username = scan.nextLine();
                    Actions.update(id, "username", username);
                    break bigLoopa;
                case "2":
                    System.out.println("Please, input new password:");
                    String password = scan.nextLine();
                    Actions.update(id, "password", password);
                    break bigLoopa;
                case "3":
                    System.out.println("Please, input new email:");
                    String email = checkEmail(scan.nextLine());
                    Actions.update(id, "email", email);
                    break bigLoopa;
                case "4":
                    System.out.println("bye");
                    break bigLoopa;
                default:
                    System.out.println("Choose correct number of option:");
            }
            input = scan.nextLine();
        }

    }

    private static void delete() {
        viewAllEntries();
        System.out.println("Input id of user you want to delete:");
        Actions.delete(checkId(scan.nextLine()));

    }

    private static void viewAllEntries() {
        Actions.viewAllEntries();
    }

    private static String checkEmail(String str) {
        String temp;
        try (Scanner scan = new Scanner(System.in)) {
            if (str.matches("[a-zA-Z\\d]+@[a-z]+\\.[a-z]+")) {
                return str;
            } else {
                while (true) {
                    System.out.println("Please, input the correct email:");
                    temp = scan.nextLine();
                    if (temp.matches("[a-zA-Z\\d]+@[a-z]+\\.[a-z]+")) {
                        return temp;
                    }
                }
            }
        }
    }

    private static int checkId(String id){
        while(true){
            try{
                int intId = Integer.parseInt(id);
                if(!Actions.isRecordAvailable(intId)){
                    System.out.println("Please, input correct id:");
                } else {
                    return intId;
                }
            }catch (NumberFormatException e){
                System.out.println("Please, input correct id:");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            id = scan.nextLine();
        }
    }
}
