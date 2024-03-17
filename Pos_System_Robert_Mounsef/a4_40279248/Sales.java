// -------------------------------------------------------
		// Assignment 4
		// Written by: Robert Mounsef 40279248
		// For COMP 248 Section H A – Fall 2023
		// --------------------------------------------------------


/**
 * The Sales class represents a sales tracking system for a fast-food restaurant.
 * It provides functionality to record and manage the sales quantities of different
 * meal types, calculate total sales revenue, and compare sales data between instances.
 * The class includes constants for meal prices, methods for updating sales quantities,
 * calculating total sales, and implementing basic object comparison. This code is
 * designed to be part of a larger system that monitors and analyzes sales performance.
 */

public class Sales {
    // Constants for meal prices
    public static final int JUNIOR_PRICE = 5;
    public static final int TEEN_PRICE = 10;
    public static final int MEDIUM_PRICE = 12;
    public static final int BIG_PRICE = 15;
    public static final int FAMILY_PRICE = 20;

    // Instance variables to store the quantity of each meal
    private int junior;   // Quantity of junior meals sold
    private int teen;     // Quantity of teen meals sold
    private int medium;   // Quantity of medium meals sold
    private int big;      // Quantity of big meals sold
    private int family;   // Quantity of family meals sold

    // Default constructor
    // Initializes Sales object with zero quantities for each meal type
    public Sales() {
        this(0, 0, 0, 0, 0);
    }

    // Constructor to set the number of each meal in the till
    // Initializes Sales object with specified quantities for each meal type
    public Sales(int junior, int teen, int medium, int big, int family) {
        this.junior = junior;
        this.teen = teen;
        this.medium = medium;
        this.big = big;
        this.family = family;
    }

    // Copy constructor
    // Creates a new Sales object by copying values from another Sales object
    public Sales(Sales otherSales) {
        this.junior = otherSales.junior;
        this.teen = otherSales.teen;
        this.medium = otherSales.medium;
        this.big = otherSales.big;
        this.family = otherSales.family;
    }

    // Getter and setter methods for each meal type
    // These methods provide access to and modification of individual meal quantities
    public int getJunior() {
        return junior;
    }

    public void setJunior(int junior) {
        this.junior = junior;
    }

    public int getTeen() {
        return teen;
    }

    public void setTeen(int teen) {
        this.teen = teen;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public int getBig() {
        return big;
    }

    public void setBig(int big) {
        this.big = big;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    // Method to add sales
    // Updates the sales quantities based on the given parameters
    public void addSales(int junior, int teen, int medium, int big, int family) {
        this.junior += junior;
        this.teen += teen;
        this.medium += medium;
        this.big += big;
        this.family += family;
    }

    // Method to calculate total sales
    // Computes the total revenue by multiplying each meal quantity with its respective price
    public int salesTotal() {
        return (junior * JUNIOR_PRICE + teen * TEEN_PRICE + medium * MEDIUM_PRICE +
                big * BIG_PRICE + family * FAMILY_PRICE);
    }

    // toString method: Converts Sales object to a formatted string representation
    // Returns a string containing the quantities of each meal type
    @Override
    public String toString() {
        return String.format("Junior: %d, Teen: %d, Medium: %d, Big: %d, Family: %d",
                junior, teen, medium, big, family);
    }

    // equals method: Compares two Sales objects for equality
    // Returns true if all corresponding meal quantities are equal, false otherwise
    public boolean equals(Sales otherSales) {
        return this.junior == otherSales.junior &&
               this.teen == otherSales.teen &&
               this.medium == otherSales.medium &&
               this.big == otherSales.big &&
               this.family == otherSales.family;
    }
}