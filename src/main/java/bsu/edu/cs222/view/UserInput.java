package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;

import java.io.IOException;

import java.util.*;

public class UserInput {

    public String getCountry() throws IOException {
        Controller controller = new Controller();
        String userInput;
        while (true) {
            Scanner requestScanner = new Scanner(System.in);
            System.out.println("Enter your desired country: ");
            userInput = requestScanner.nextLine();
            List<String> checkIfInDatabase = new ArrayList<>();
            checkIfInDatabase.add(controller.checkMisspelling(userInput).get(0));
            if (controller.checkNoInput(userInput).equals(false)) {
                System.out.println("Please enter something!");
            } else if (checkIfInDatabase.get(0).equals("noVal")) {
                System.out.println("This country is not in our database!");
            } else {
                break;
            }

        }
        return userInput.toLowerCase(Locale.ROOT);

    }

}
