// -------------------------------------------------------
		// Assignment 4
		// Written by: Robert Mounsef 40279248
		// For COMP 248 Section H A – Fall 2023
		// --------------------------------------------------------


/**
The PoSDemo Java class defines a Point of Sale (PoS) application for managing sales and prepaid 
cards in a catering service. Users can perform various operations, including viewing the content 
of all PoS instances, displaying content for a specific PoS, listing PoS instances with the same total 
sales or the same sales categories, adding or removing prepaid cards, updating prepaid card expiry dates, 
and adding sales to a PoS. The code utilizes a set of methods such as displayAllPoS, displayOnePoS, SameTotalSales, SameSalesCategories, 
PoSsWithSameTotalSalesAndPrepaidCards, addPrePaidCardToPoS, removePrePaidCard, updatePrePaidCardExpiry, and addSalesToPoS to implement 
these functionalities. The main method initializes a set of predefined PoS instances, displays a welcome message, and presents a menu-driven 
interface for users to interact with the application until choosing to exit. Overall, the code provides an interactive system for managing
 sales and prepaid cards in a catering business.
*/

import java.util.Scanner;

// Define the PoSDemo class
public class PoSDemo {
    // Declare a static array to store PoS instances
    private static PoS[] posArray;

    // Display content of all PoS instances
    private static void displayAllPoS() {
        System.out.println("\nContent of each PoS:\n------------------------");
        // Iterate through each PoS instance
        for (int i = 0; i < posArray.length; i++) {
            System.out.println("PoS #" + i + ":");
            System.out.println(posArray[i].salesBreakdown()); // Display sales breakdown
            // Check and display prepaid cards if available
            if (posArray[i].getNumPrePaidCards() > 0) {
                System.out.println("Pre-paid Cards:");
                for (PrePaidCard card : posArray[i].getPrePaidCards()) {
                    System.out.println(card);
                }
            } else {
                System.out.println("No pre-paid cards");
            }
            System.out.println();
        }
    }

    // Display content of a specific PoS instance
    private static void displayOnePoS() {
        Scanner scanner = new Scanner(System.in);

        int posNumber;

        do {
            // Prompt user for the PoS number to display
            System.out.print("Which PoS do you want to see the content of? (Enter number 0 to " + (posArray.length - 1) + "): ");
            posNumber = scanner.nextInt();

            // Validate the input PoS number
            if (posNumber < 0 || posNumber >= posArray.length) {
                System.out.println("Sorry, but there is no PoS number " + posNumber);
                System.out.println("--> Try again:(enter number 0 to 4)");
            }
        } while (posNumber < 0 || posNumber >= posArray.length);

        // Display content of the selected PoS
        System.out.println("\nContent of PoS #" + posNumber + ":\n------------------------");
        System.out.println(posArray[posNumber].salesBreakdown());

        // Display prepaid cards associated with the selected PoS
        PrePaidCard[] prePaidCards = posArray[posNumber].getPrePaidCards();
        if (prePaidCards == null || prePaidCards.length == 0) {
            System.out.println("No PrePaidCards");
        } else {
            System.out.println("Pre-paid Cards:");
            for (PrePaidCard card : prePaidCards) {
                System.out.println(card.toString());
            }
        }
        System.out.println("");
    }

    // List PoS instances with the same total sales
    private static void SameTotalSales() {
        System.out.println("\nList of PoSs with the same total $ Sales:\n");

        boolean foundMatches = false;

        // Compare total sales of different PoS instances
        for (int i = 0; i < posArray.length - 1; i++) {
            for (int j = i + 1; j < posArray.length; j++) {
                if (posArray[i].getTotalSales() == posArray[j].getTotalSales()) {
                    System.out.println("PoSs " + i + " and " + j + " both have $" + posArray[i].getTotalSales());
                    foundMatches = true;
                }
            }
        }

        // Display message if no matches are found
        if (!foundMatches) {
            System.out.println("No PoSs with the same total $ Sales found.");
        }
        System.out.println("");
    }

    // List PoS instances with the same sales categories breakdown
    private static void SameSalesCategories() {
        System.out.println("\nList of PoSs with the same Sales categories:\n");

        boolean foundMatches = false;

        // Compare sales categories of different PoS instances
        for (int i = 0; i < posArray.length - 1; i++) {
            for (int j = i + 1; j < posArray.length; j++) {
                if (posArray[i].isEqualSalesCategories(posArray[j])) {
                    System.out.println("PoSs " + i + " and " + j + " both have " + posArray[i].salesBreakdown());
                    foundMatches = true;
                }
            }
        }

        // Display message if no matches are found
        if (!foundMatches) {
            System.out.println("No PoSs with the same Sales categories found.");
        }

        System.out.println("");
    }

    // List PoS instances with the same total sales and the same number of prepaid cards
    private static void PoSsWithSameTotalSalesAndPrepaidCards() {
        System.out.println("\nList of PoSs with the same $ amount of sales and same number of PrePaiCards:");
        System.out.println("");

        boolean foundMatches = false;

        // Compare total sales and number of prepaid cards of different PoS instances
        for (int i = 0; i < posArray.length - 1; i++) {
            for (int j = i + 1; j < posArray.length; j++) {
                if (posArray[i].getTotalSales() == posArray[j].getTotalSales() &&
                        posArray[i].getNumPrePaidCards() == posArray[j].getNumPrePaidCards()) {

                    System.out.println("PoSs " + i + " and " + j);
                    foundMatches = true;
                }
            }
        }

        // Display message if no matches are found
        if (!foundMatches) {
            System.out.println("No PoSs with the same $ amount of sales and same number of PrePaiCards found.");
        }
        System.out.println("");
    }

    // Add a prepaid card to an existing PoS instance
    private static void addPrePaidCardToPoS(Scanner scanner) {
        System.out.print("Which PoS do you want to add a PrePaidCard to? (Enter number 0 to 4): ");
        int posNumber = scanner.nextInt();

        // Validate the input PoS number
        if (posNumber < 0 || posNumber >= posArray.length) {
            System.out.println("Invalid PoS number. Please try again.");
            return;
        }

        System.out.println("Please enter the following information so that we may complete the PrePaidCard-");

        System.out.println("--> Type of PrePaidCard (Carnivore, Halal, Kosher, Pescatarian, Vegetarian, Vigan): ");
        String cardType = scanner.next();

        System.out.print("--> Id of the prepaid card owner: ");
        String cardHolderID = scanner.next();

        System.out.print("--> Expiry day number and month (separate by a space): ");
        int dueDay = scanner.nextInt();
        int dueMonth = scanner.nextInt();

        // Create a new PrePaidCard instance and add it to the selected PoS
        PrePaidCard newCard = new PrePaidCard(cardType, cardHolderID, dueDay, dueMonth);
        int newCardCount = posArray[posNumber].addPrePaidCard(newCard);

        System.out.println("You now have " + newCardCount + " PrePaidCard(s)");
    }

    // Remove a prepaid card from an existing PoS instance
    private static void removePrePaidCard() {
        Scanner scanner = new Scanner(System.in);

        int posNumber;

        do {
            // Prompt user for the PoS number from which to remove a prepaid card
            System.out.print("From which PoS do you want to remove a PrePaiCard? (Enter number 0 to " + (posArray.length - 1) + "): ");
            posNumber = scanner.nextInt();

            // Validate the input PoS number
            if (posNumber < 0 || posNumber >= posArray.length) {
                System.out.println("Sorry, but there is no PoS number " + posNumber);
                System.out.println("--> Try again:");
            }
        } while (posNumber < 0 || posNumber >= posArray.length);

        // Retrieve prepaid cards associated with the selected PoS
        PrePaidCard[] prePaidCards = posArray[posNumber].getPrePaidCards();

        // Check if the PoS has no prepaid cards
        if (prePaidCards == null || prePaidCards.length == 0) {
            System.out.println("Sorry, that PoS has no PrePaiCards.");
        } else {
            // Display prepaid cards in the selected PoS
            System.out.println("Pre-paid Cards in PoS #" + posNumber + ":");
            for (int i = 0; i < prePaidCards.length; i++) {
                System.out.println(i + ": " + prePaidCards[i].toString());
            }

            int cardIndex;
            do {
                // Prompt user for the index of the prepaid card to remove
                System.out.print("Which PrePaiCard do you want to remove? (Enter number 0 to " + (prePaidCards.length - 1) + "): ");
                cardIndex = scanner.nextInt();

                // Validate the input card index
                if (cardIndex < 0 || cardIndex >= prePaidCards.length) {
                    System.out.println("Invalid card index. --> Try again:");
                }
            } while (cardIndex < 0 || cardIndex >= prePaidCards.length);

            // Attempt to remove the selected prepaid card
            boolean removed = posArray[posNumber].removePrePaidCard();

            if (removed) {
                System.out.println("PrePaiCard was removed successfully.");
            }
        }
        System.out.println("");
    }

    // Update the expiry date of a prepaid card in an existing PoS instance
    private static void updatePrePaidCardExpiry() {
        Scanner scanner = new Scanner(System.in);

        int posNumber;

        do {
            // Prompt user for the PoS number to update the expiry date of a prepaid card
            System.out.print("Which PoS do you want to update the expiry date of a PrePaiCard? (Enter number 0 to " + (posArray.length - 1) + "): ");
            posNumber = scanner.nextInt();

            // Validate the input PoS number
            if (posNumber < 0 || posNumber >= posArray.length) {
                System.out.println("Sorry, but there is no PoS number " + posNumber);
                System.out.println("--> Try again:");
            }
        } while (posNumber < 0 || posNumber >= posArray.length);

        // Retrieve prepaid cards associated with the selected PoS
        PrePaidCard[] prePaidCards = posArray[posNumber].getPrePaidCards();

        // Check if the PoS has no prepaid cards
        if (prePaidCards == null || prePaidCards.length == 0) {
            System.out.println("Sorry, that PoS has no PrePaiCards.");
        } else {
            // Prompt user for the index of the prepaid card to update
            System.out.println("Which PrePaiCard do you want to update? (Enter number 0 to " + (prePaidCards.length - 1) + "): ");
            int cardIndex = scanner.nextInt();

            // Validate the input card index
            if (cardIndex < 0 || cardIndex >= prePaidCards.length) {
                System.out.println("Invalid card index.");
            } else {
                // Prompt user for the new expiry date
                System.out.print("--> Enter new expiry date day number and month (separated by a space): ");
                int newDueDay = scanner.nextInt();
                int newDueMonth = scanner.nextInt();

                // Update the expiry date of the selected prepaid card
                posArray[posNumber].updateCardExpiry(cardIndex, newDueDay, newDueMonth);

                System.out.println("Expiry Date updated.");
            }
        }
        System.out.println("");
    }

 // Method to add sales to a PoS based on different meal categories
    private static void addSalesToPoS() {
        Scanner scanner = new Scanner(System.in); // Initialize Scanner for user input

        int posNumber;

        do {
            // Prompt user to enter PoS number within valid range
            System.out.print("Which PoS do you want to add Sales to? (Enter number 0 to " + (posArray.length - 1) + "): ");
            posNumber = scanner.nextInt();

            if (posNumber < 0 || posNumber >= posArray.length) {
                // Display error if the entered PoS number is out of range
                System.out.println("Sorry, but there is no PoS number " + posNumber);
                System.out.println("--> Try again:");
            }
        } while (posNumber < 0 || posNumber >= posArray.length);

        // Prompt user to enter sales for different meal categories
        System.out.println("How many junior, teen, medium, big, and family meal menu do you want to add?");
        System.out.print("Enter 5 numbers separated by a space): ");
        int junior = scanner.nextInt();
        int teen = scanner.nextInt();
        int medium = scanner.nextInt();
        int big = scanner.nextInt();
        int family = scanner.nextInt();

        // Update total sales for the specified PoS
        int totalSales = posArray[posNumber].addMealSales(junior, teen, medium, big, family);

        // Display the updated total sales
        System.out.println("You now have $" + totalSales + ".0");
        System.out.println("");
    }

    // Method to initialize an array of PoS instances with predefined values
    private static void initializePoSArray() {
        // Initialize PoS instances as per the prompt
        // For simplicity, we are hardcoding some values here
        posArray = new PoS[5];

        // Create the first PoS instance with sales and prepaid cards
        Sales sales1 = new Sales(2, 1, 0, 4, 1);
        PrePaidCard[] cards1 = {
            new PrePaidCard("Vegetarian", "40825164", 25, 12),
            new PrePaidCard("Carnivore", "21703195", 03, 12)};
        posArray[0] = new PoS(sales1, cards1);

        // Create the second PoS instance with sales and prepaid cards
        Sales sales2 = new Sales(2, 1, 0, 4, 1);
        PrePaidCard[] cards2 = {
            new PrePaidCard("Vigan", "408251642", 07, 12),
            new PrePaidCard("Vegetarian", "21596387", 24, 07)};
        posArray[1] = new PoS(sales2, cards2);

        // Create the third PoS instance with sales and prepaid cards
        Sales sales3 = new Sales(0, 1, 5, 2, 0);
        PrePaidCard[] cards3 = {
            new PrePaidCard("Pescatarian", "95432806", 01, 06),
            new PrePaidCard("Halal", "42087913", 18, 12),
            new PrePaidCard("Kosher","40735421",05,04)};
        posArray[2] = new PoS(sales3, cards3);

        // Create the fourth and fifth PoS instances with the same breakdown of sales but no prepaid cards
        posArray[3] = new PoS(new Sales(3, 2, 4, 1, 2), null);
        posArray[4] = new PoS(new Sales(3, 2, 4, 1, 2), null);
    }

    // Main method to run the application
    public static void main(String[] args) {
        initializePoSArray(); // Initialize the array of PoS instances
        Scanner scanner = new Scanner(System.in);
        int choice = 1;

        // Display welcome message
        System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n"
                + "| Welcome to Concordia CostLessBites Catering Sales Counter Application         |\r\n"
                + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         \r\n");

        do {
            // Display menu options
            System.out.print( "| What would you like to do?                                                    |\r\n"
                    + "| 1 >> See the content of all PoSs                                              |\r\n"
                    + "| 2 >> See the content of one PoS                                               |\r\n"
                    + "| 3 >> List PoSs with the same $ amount of sales                                |\r\n"
                    + "| 4 >> List PoSs with the same number of Sales categories                       |\r\n"
                    + "| 5 >> List PoSs with the same $ amount of Sales and same number of prepaid cards|\r\n"
                    + "| 6 >> Add a PrePaiCard to an existing PoS                                      |\r\n"
                    + "| 7 >> Remove an existing prepaid card from a PoS                               |\r\n"
                    + "| 8 >> Update the expiry date of an existing Prepaid card                       |\r\n"
                    + "| 9 >> Add Sales to a PoS                                                       |\r\n"
                    + "| 0 >> To quit                                                                  |\r\n"
                    + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n"
                    + "Please enter your choice and press <Enter>:");
            choice = scanner.nextInt();

            // Perform actions based on user's choice
            switch (choice) {
                case 0:
                    break;
                case 1:
                    displayAllPoS(); // Display content of all PoS instances
                    break;
                case 2:
                    displayOnePoS(); // Display content of a specific PoS
                    break;
                case 3:
                    SameTotalSales(); // List PoS instances with the same $ amount of sales
                    break;
                case 4:
                    SameSalesCategories(); // List PoS instances with the same sales categories
                    break;
                case 5:
                    PoSsWithSameTotalSalesAndPrepaidCards(); // List PoS instances with the same $ amount of sales and prepaid cards
                    break;
                case 6:
                    addPrePaidCardToPoS(scanner); // Add a PrePaidCard to an existing PoS
                    break;
                case 7:
                    removePrePaidCard(); // Remove a prepaid card from a PoS
                    break;
                case 8:
                    updatePrePaidCardExpiry(); // Update the expiry date of a PrePaidCard in a PoS
                    break;
                case 9:
                    addSalesToPoS(); // Add sales to a PoS
                    break;
            }
        } while (choice != 0);

        // Display closing message
        System.out.println("Thank you for using Concordia CostLesssBites Catering Sales Counter Application!");
    }
    }
