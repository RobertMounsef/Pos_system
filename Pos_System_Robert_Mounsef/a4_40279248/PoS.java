// -------------------------------------------------------
		// Assignment 4
		// Written by: Robert Mounsef 40279248
		// For COMP 248 Section H A – Fall 2023
		// --------------------------------------------------------


/**
 * The PoS (Point of Sale) class represents a system that tracks meal sales and
 * prepaid cards for a fast-food restaurant. It includes methods to check and
 * calculate sales totals, manage prepaid cards, and provide a breakdown of sales.
 * The class supports equality comparison based on sales and the number of prepaid cards.
 */
public class PoS {
    private Sales mealSales;          // Sales object to track meal sales
    private PrePaidCard[] prePaidCards;   // Array to store prepaid cards

    // Default constructor
    public PoS() {
        // Initialize the PoS with default meal sales and no prepaid cards
        this.mealSales = new Sales();
        this.prePaidCards = null;
    }

    // Constructor with 2 parameters
    public PoS(Sales mealSales, PrePaidCard[] prePaidCards) {
        // Initialize the PoS with specified meal sales and prepaid cards
        this.mealSales = new Sales(mealSales);
        if (prePaidCards != null) {
            // Copy each prepaid card using the copy constructor
            this.prePaidCards = new PrePaidCard[prePaidCards.length];
            for (int i = 0; i < prePaidCards.length; i++) {
                this.prePaidCards[i] = new PrePaidCard(prePaidCards[i]);
            }
        } else {
            this.prePaidCards = null;
        }
    }

    // Method to check if total $ value of sales are equal
    public boolean isEqualTotalSales(PoS otherPoS) {
        // Check if the total sales of this PoS instance is equal to another PoS instance
        return this.mealSales.salesTotal() == otherPoS.mealSales.salesTotal();
    }

    // Method to check if the number of each sales category are equal
    public boolean isEqualSalesCategories(PoS otherPoS) {
        // Check if the sales categories (quantities) are equal between two PoS instances
        return this.mealSales.equals(otherPoS.mealSales);
    }

    // Method to return the total $ sales
    public int getTotalSales() {
        // Get and return the total sales value from the meal sales object
        return mealSales.salesTotal();
    }

    // Method to return the number of prepaid cards
    public int getNumPrePaidCards() {
        // Return the number of prepaid cards, or 0 if there are none
        return (prePaidCards != null) ? prePaidCards.length : 0;
    }

    // Method to add a new PrePaidCard
    public int addPrePaidCard(PrePaidCard newCard) {
        // Add a new prepaid card to the array (expand the array if needed)
        if (prePaidCards == null) {
            prePaidCards = new PrePaidCard[]{newCard};
        } else {
            PrePaidCard[] newArray = new PrePaidCard[prePaidCards.length + 1];
            System.arraycopy(prePaidCards, 0, newArray, 0, prePaidCards.length);
            newArray[prePaidCards.length] = newCard;
            prePaidCards = newArray;
        }
        return prePaidCards.length; // Return the new number of prepaid cards
    }

    // Method to remove a pre-paid card
    public boolean removePrePaidCard() {
        // Remove a prepaid card from the array (shrink the array if needed)
        if (prePaidCards != null && prePaidCards.length > 0) {
            PrePaidCard[] newArray = new PrePaidCard[prePaidCards.length - 1];
            System.arraycopy(prePaidCards, 0, newArray, 0, newArray.length);
            prePaidCards = newArray;
            return true; // Return true if a card was successfully removed
        }
        return false; // Return false if no card was removed
    }

    // Method to update the expiry day and month of a prepaid card
    public void updateCardExpiry(int cardIndex, int newDueDay, int newDueMonth) {
        // Update the expiry day and month of a prepaid card at the specified index
        if (prePaidCards != null && cardIndex >= 0 && cardIndex < prePaidCards.length) {
            prePaidCards[cardIndex].setDueDay(newDueDay);
            prePaidCards[cardIndex].setDueMonth(newDueMonth);
        }
    }

    // Method to add meal sales
    public int addMealSales(int junior, int teen, int medium, int big, int family) {
        // Add meal sales quantities and return the new total sales value
        mealSales.addSales(junior, teen, medium, big, family);
        return mealSales.salesTotal();
    }

    // equals() method
    public boolean equals(PoS otherPoS) {
        // Check if the total sales and number of prepaid cards are equal
        return this.getTotalSales() == otherPoS.getTotalSales() &&
                this.getNumPrePaidCards() == otherPoS.getNumPrePaidCards();
    }

    // toString() method
    @Override
    public String toString() {
        // Generate a string representation with meal sales breakdown and prepaid cards
        StringBuilder result = new StringBuilder("Meal Sales Breakdown:\n");
        result.append(mealSales.toString()).append("\n");

        if (prePaidCards != null && prePaidCards.length > 0) {
            result.append("Pre-paid Cards:\n");
            for (PrePaidCard card : prePaidCards) {
                result.append(card.toString()).append("\n");
            }
        } else {
            result.append("No pre-paid cards\n");
        }

        return result.toString(); // Return the final string representation
    }

    // Method to return a string with just the breakdown of the sales
    public String salesBreakdown() {
        // Return a formatted string with the breakdown of meal sales quantities and prices
        return String.format("%d x $%d + %d x $%d + %d x $%d + %d x $%d + %d x $%d",
                mealSales.getJunior(), Sales.JUNIOR_PRICE,
                mealSales.getTeen(), Sales.TEEN_PRICE,
                mealSales.getMedium(), Sales.MEDIUM_PRICE,
                mealSales.getBig(), Sales.BIG_PRICE,
                mealSales.getFamily(), Sales.FAMILY_PRICE);
    }

    // Getter method to retrieve the array of prepaid cards
    public PrePaidCard[] getPrePaidCards() {
        return prePaidCards;
    }
}