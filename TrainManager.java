/**
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 */

import java.util.Scanner;

/**
 * The <code>TrainManager</code> class contains the main driver method of the program along with some helper methods
 */
public class TrainManager {

    // The variable in control of terminating the program
    private static boolean runCondition = true;
    private static TrainLinkedList train = new TrainLinkedList();

    /**
     * The driver method of the program
     * @param args
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        while(runCondition){
            System.out.println("""
                    
                    (F) Cursor Forward
                    (B) Cursor Backward
                    (I) Insert Car After Cursor
                    (R) Remove Car At Cursor
                    (L) Set Product Load
                    (S) Search For Product
                    (T) Display Train
                    (M) Display Manifest
                    (D) Remove Dangerous Cars
                    (Q) Quit
                    """);

            String input = getString(stdin, "Enter a selection: ");
            System.out.println();
            TrainManager.iSwitch(input, stdin);
        }

        System.out.println("Program terminating successfully...");
        stdin.close();
    }

    /**
     * Helper method for keeping switch statements in
     * @param input
     *  The user selected operation to be performed
     * @param stdin
     *  The Scanner variable to take input
     */
    public static void iSwitch(String input, Scanner stdin){
        switch (input.toLowerCase()) {
            case "f" -> train.cursorForward();
            case "b" -> train.cursorBackward();
            case "i" -> TrainManager.insertAfterCursor(stdin);
            case "r" -> TrainManager.removeCursor(stdin);
            case "l" -> TrainManager.setLoadAtCursor(stdin);
            case "s" -> {
                String prodToFetch = getString(stdin, "Enter product name: ");
                train.findProduct(prodToFetch);
            }
            case "t" -> System.out.println(train);
            case "m" -> train.printManifest();
            case "d" -> train.removeDangerousCars();
            case "q" -> runCondition = false;
            default ->
                    System.out.println("Invalid selection!! Please enter letters that correspond to operations in the menu...");
        }
    }

    /**
     * Sets the load in the car the cursor is pointing to by accepting input from the user
     * @param stdin
     *  The Scanner variable used to collect input
     */
    public static void setLoadAtCursor(Scanner stdin){
        if(train.getCursor() != null){
            while(true){
                try {
                    String newProductName = getString(stdin, "Enter product name: ");
                    double newProductWeight = getDouble(stdin, "Enter product weight in tons: ", "Invalid product weight!!! Please add a decimal value for product weight");
                    double newProductValue = getDouble(stdin, "Enter product value in dollars: ", "Invalid product value!!! Please add a decimal value for product value");
                    String newIsDString = getString(stdin, "Enter is product dangerous? (y/n)");
                    if (!(newIsDString.equals("yes") || newIsDString.equals("no") || newIsDString.equals("y") || newIsDString.equals("n"))){
                        System.out.println("Invalid answer to yes or no question, please enter 'y' for yes and 'n' for no");
                        continue;
                    }
                    boolean newIsD = (newIsDString.equals("yes") || newIsDString.equals("y"));
                    train.setLoadAtCursor(newProductName,newProductWeight,newProductValue,newIsD);
                    break;

                } catch (IllegalArgumentException ex){
                    ex.getMessage();
                }
            }
        } else
            System.out.println("Cannot set load at cursor since train has no cars!! add cars first to set load..");
    }

    /**
     * Removes the car the cursor is currently pointing to
     * @param stdin
     *  The Scanner variable used to collect input
     */
    public static void removeCursor(Scanner stdin){
        if(train.getCursor() != null){
            TrainCar removedCar = train.removeCursor();
            System.out.println("Car successfully unlinked. Following load has been removed from the train:");
            System.out.println();
            System.out.println(String.format("   %-15s %-15s %-15s %-9s", "Name", "Weight (t)", "Value ($)", "Dangerous"));
            System.out.println("===============================================================");
            System.out.println(String.format("   %-15s %-15f %-15f %-9s", removedCar.getLoad().getName(), removedCar.getLoad().getWeight(), removedCar.getLoad().getValue(),
                    ((removedCar.getLoad().isDangerous())?"YES":"NO")));
        } else {
            System.out.println("Cannot remove car at cursor as train has no cars, add cars first.");
        }
    }

    /**
     * Creates a new car and attaches it to the train after the car pointed at by the cursor
     * @param stdin
     *  The Scanner variable used to take input
     */
    public static void insertAfterCursor(Scanner stdin){
        while (true){
            try{
                double carLength = getDouble(stdin, "Enter car length in meters: ", "Invalid car length!!! Please add a decimal value for car length");
                double carWeight = getDouble(stdin, "Enter car weight in tons: ", "Invalid car weight!!! Please add a decimal value for car weight");
                TrainCar car = new TrainCar(carLength, carWeight);
                train.insertAfterCursor(car);
                System.out.println();
                System.out.println("New train car " + carLength + " meters " + carWeight + " tons inserted into train.");
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * This method is used to collect integer type input from the terminal/console
     * @param stdin
     * The Scanner variable used to collect input
     * @param instruction
     * The message to be displayed
     * @param invalidMessage
     * The message to be displayed when input is invalid
     * @return
     * The inputted double
     */
    private static double getDouble(Scanner stdin, String instruction, String invalidMessage) {
        while (true) {
            System.out.print(instruction);
            String line = stdin.nextLine();

            try {
                double value = Double.parseDouble(line);
                return value;
            } catch (NumberFormatException ex) {
                System.out.println(invalidMessage);
            }
        }
    }

    /**
     * This method is used to collect String type input from the terminal/console
     * @param stdin
     * The Scanner variable used to collect input
     * @param instruction
     * The message to be displayed
     * @return
     * The inputted String
     */
    private static String getString(Scanner stdin, String instruction) {
        System.out.print(instruction);
        String line = "";

        while (line.isBlank() || line.isEmpty()) {
            line = stdin.nextLine();
        }

        return line;
    }

    /**
     * Returns a reference to the train
     */
    public static TrainLinkedList getTrain() {
        return train;
    }
}
