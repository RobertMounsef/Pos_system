// -------------------------------------------------------
		// Assignment 4
		// Written by: Robert Mounsef 40279248
		// For COMP 248 Section H A – Fall 2023
		// --------------------------------------------------------


/**
 * The PrePaidCard class represents a prepaid card with information such as card type,
 * cardholder ID, and due date. It includes methods to construct, access, and modify
 * card details, as well as comparison methods for equality. The due day and due month
 * are validated to ensure they fall within appropriate ranges. The toString method
 * generates a formatted string representation of the card's details.
 */
public class PrePaidCard {
    // Instance variables
    private String cardType;        // Type of the prepaid card
    private String cardHolderID;    // ID of the cardholder
    private int dueDay;             // Due day of the card
    private int dueMonth;           // Due month of the card

    // Default constructor
    public PrePaidCard() {
        // Initialize a prepaid card with default values
        this("", "", 0, 0);
    }

    // Constructor with 4 parameters
    public PrePaidCard(String cardType, String cardHolderID, int dueDay, int dueMonth) {
        // Initialize a prepaid card with specified values, validating and setting the due day and month
        this.cardType = cardType;
        this.cardHolderID = cardHolderID;
        setDueDay(dueDay);
        setDueMonth(dueMonth);
    }

    // Copy constructor
    public PrePaidCard(PrePaidCard otherCard) {
        // Copy the attributes from another prepaid card
        this.cardType = otherCard.cardType;
        this.cardHolderID = otherCard.cardHolderID;
        this.dueDay = otherCard.dueDay;
        this.dueMonth = otherCard.dueMonth;
    }

    // Accessor methods
    public String getCardType() {
        // Get the card type
        return cardType;
    }

    public String getCardHolderID() {
        // Get the cardholder ID
        return cardHolderID;
    }

    public int getDueDay() {
        // Get the due day
        return dueDay;
    }

    public int getDueMonth() {
        // Get the due month
        return dueMonth;
    }

    // Mutator methods
    // Sets the due day, ensuring it falls within the valid range (1 to 31)
    public void setDueDay(int dueDay) {
        // Validate and set the due day
        if (dueDay >= 1 && dueDay <= 31) {
            this.dueDay = dueDay;
        } else {
            this.dueDay = 0; // Set to 0 if the provided value is invalid
        }
    }

    // Sets the due month, ensuring it falls within the valid range (1 to 12)
    public void setDueMonth(int dueMonth) {
        // Validate and set the due month
        if (dueMonth >= 1 && dueMonth <= 12) {
            this.dueMonth = dueMonth;
        } else {
            this.dueMonth = 0; // Set to 0 if the provided value is invalid
        }
    }

    // toString method
    // Generates a formatted string representation of the card's details
    @Override
    public String toString() {
        // Format the due day and month with leading zeros
        String formattedDueDay = String.format("%02d", dueDay);
        String formattedDueMonth = String.format("%02d", dueMonth);
        // Construct and return the formatted string
        return cardType + " - " + cardHolderID + " - " + formattedDueDay + "/" + formattedDueMonth + ".";
    }

    // equals method
    // Compares two PrePaidCard objects for equality based on their attributes
    public boolean equals(PrePaidCard otherCard) {
        // Check if the attributes of two prepaid cards are equal
        return this.cardType.equals(otherCard.cardType) &&
               this.cardHolderID.equals(otherCard.cardHolderID) &&
               this.dueDay == otherCard.dueDay &&
               this.dueMonth == otherCard.dueMonth;
    }
}