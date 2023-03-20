/* Moses Munroe BulkBarn / " Candy Store Assigment
March 2023, ICS4U. Assigment 1" */

import java.util.Scanner;
public class CandyStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Define the candy options and their prices
        String[] candies = {"Skittles", "Lollipops", "Jubjubes", "Smarties", "Reese's Pieces"};
        double[] prices = {1.75, 0.5, 0.5, 1.5, 2.5};
        String[] priceUnits = {"per kg", "each", "per kg", "per kg", "per 0.5 kg"};

        // Define the special deal for lollipops
        double lollipopDealPrice = 2.0;
        int lollipopDealQuantity = 5;
        
        // Initialize variables to keep track of the customer's candy selections and quantities
        int[] candyQuantities = new int[candies.length];
        double subtotal = 0.0;
        
        // Print the candy options
        System.out.println("Please select a candy:");
        for (int i = 0; i < candies.length; i++) {
            System.out.printf("%s: %s at $%.2f %s\n", (char)('a'+i), candies[i], prices[i], priceUnits[i]);
        }
        System.out.println("Type 'quit' to check out.");
        
        // Keep asking for candy selections and quantities until the customer checks out
        while (true) {
            // Get the user's candy selection
            String newline = System.getProperty("line.separator");
        System.out.println("-------------------------------" + newline + "Enter a candy selection or type (quit) to finish");
            String candySelection = scanner.next();
            if (candySelection.equals("quit")) {
                break;
            }
            
            // Get the user's desired quantity
            System.out.print("Enter the quantity desired: ");
            int candyQuantity = scanner.nextInt();

            // Calculate the subtotal for this candy selection
            int candyIndex = candySelection.charAt(0) - 'a';
            double candyPrice = prices[candyIndex];
            double candySubtotal = 0.0;
            if (candySelection.equals("b")) { // Special case for lollipops
                int dealCount = candyQuantity / lollipopDealQuantity;
                int regularCount = candyQuantity % lollipopDealQuantity;
                candySubtotal += dealCount * lollipopDealPrice;
                candySubtotal += regularCount * candyPrice;
            } else {
                candySubtotal += candyPrice * candyQuantity;
            }
            
            // Add the candy selection and quantity to the running total
            candyQuantities[candyIndex] += candyQuantity;
            subtotal += candySubtotal;

            // Print the subtotal for this candy selection
            System.out.printf("%d of %s at $%.2f per %s costs a subtotal of $%.2f\n", candyQuantity, candies[candyIndex], candyPrice, priceUnits[candyIndex], candySubtotal);
        }
        
        // Calculate and print the total cost with tax
        double tax = 0.13 * subtotal;
        double total = subtotal + tax;
        System.out.printf("Subtotal: $%.2f\n", subtotal);
        System.out.printf("Tax: $%.2f\n", tax);
        System.out.printf("Total: $%.2f\n", total);
        
        scanner.close();
    }
}
