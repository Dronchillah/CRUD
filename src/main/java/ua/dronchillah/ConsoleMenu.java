package ua.dronchillah;

import java.util.Scanner;

public class ConsoleMenu {
    static Scanner scan = new Scanner(System.in);
    public static void start() {
        String menu = "Welcome to Dronchillah's CRUD application " +
                "\nChoose one of next options: " +
                "\n 1.View all entries" +
                "\n 2.Create entry" +
                "\n 3.Update entry" +
                "\n 4.Delete entry" +
                "\n 5.Stop application";
        System.out.println(menu);
        try {
            bigLoopa:
            while (true) {
                String str = scan.nextLine();
                switch (str) {
                    case "1":
                        System.out.println("case 1");
                        Actions.viewAllEntries();
                        break;
                    case "2":
                        System.out.println("case 2");
                        create();
                        break;
                    case "3":
                        System.out.println("case 3");
                        break;
                    case "4":
                        System.out.println("case 4");
                        break;
                    case "5":
                        System.out.println("bye");
                        break bigLoopa;
                    default:
                        System.out.println("Choose correct number of option:");
                }
            }
        } finally {
            scan.close();
        }
    }
    private static void viewAllEntries(){

    }
    private static void create(){
        //Scanner scanner = new Scanner(System.in);
            System.out.println("Input username:");
            String username = scan.nextLine();
            System.out.println("Input password:");
            String password = scan.nextLine();
            System.out.println("Input email:");
            String email = checkEmail(scan.nextLine());
            Actions.create(username, password, email);

    }
    private static void update(){

    }
    private static void delete(){

    }

    private static String checkEmail(String str){
        String temp;
        try(Scanner scan = new Scanner(System.in)){
            if(str.matches("[a-zA-Z\\d]+@[a-z]+\\.[a-z]+")){
                return str;
            } else {
                while (true) {
                    System.out.println("Please, input the correct email:");
                    temp = scan.nextLine();
                    if(temp.matches("[a-zA-Z\\d]+@[a-z]+\\.[a-z]+")){
                        return temp;
                    }
                }
            }
        }
    }
}
