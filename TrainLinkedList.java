
/**
 * The <code>TrainLinkedList</code> class constructs and contains all the methods
 * required to manipulate <code>TrainLinkedList</code> type objects. It implements a Double-Linked List ADT. The class
 * contains references to the head and tail of the list, as well as a cursor variable (all TrainCarNode), and provides
 * methods to perform insertion, deletion, search, and various other operations.
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 */

public class TrainLinkedList {

    private TrainCarNode head;
    private TrainCarNode tail;
    private TrainCarNode cursor;

    private int numOfCars = 0; //total number of cars
    private double totLength = 0; //total length
    private double totWeight = 0; //total weight, empty car weight + load inside car's weight
    private double totValue = 0; //total money value
    private int dangerousCars = 0; //total number of dangerous cars

    /**
     * Creates an empty doubly linked list
     */
    public TrainLinkedList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     *
     * @return The data of the node the cursor is currently pointing to
     */
    public TrainCar getCursorData(){
        return cursor.getCar();
    }

    /**
     * Sets the data (in our case a train car) of the cursor node to the specified data
     * @param car
     *  The car reference which is being set
     */
    public void setCursorData (TrainCar car){
        cursor.setCar(car);
    }

    /**
     * Moves the cursor forward to the next node of the list, a.k.a. train
     */
    public void cursorForward() {
        if (cursor == null)
            System.out.println("Could not move cursor train doesn't have any cars");
        else if (cursor.getNext() != null){
            cursor = cursor.getNext();
            System.out.println("Cursor moved forward");
        } else
            System.out.println("No next car, cannot move cursor forward");
    }

    /**
     * Moves the cursor backwards to the previous node of the list, a.k.a. train
     */
    public void cursorBackward(){
        if (cursor == null){
            System.out.println("Could not move cursor as train doesn't have any cars");
        } else if (cursor.getPrev() != null) {
            cursor = cursor.getPrev();
            System.out.println("Cursor moved backward");
        } else
            System.out.println("No previous car, cannot move cursor backward");
    }

    /**
     * Inserts a new after the car the cursor is pointing to in the train
     * @param newCar
     *  The car to be added
     * @throws IllegalArgumentException
     *  When the new car to be added is null
     */
    public void insertAfterCursor(TrainCar newCar){

        TrainCarNode newNode = new TrainCarNode(newCar);

        if (newCar == null)
            throw new IllegalArgumentException("the newCar is null");
        if(cursor == null){
            head = newNode;
            tail = newNode;
            cursor = newNode;
        } else if (cursor == tail) {
            newNode.setPrev(cursor);
            cursor.setNext(newNode);
            cursor = newNode;
            tail = newNode;
        } else{
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor);
            cursor.setNext(newNode);
            newNode.getNext().setPrev(newNode);
            cursor = newNode;
            if(cursor.getNext() == null)
                tail = cursor;
        }

        numOfCars++;
        totLength += newCar.getCarLength();
        totWeight += newCar.getCarWeight();
    }

    /**
     * Removes the TrainCarNode referenced by the cursor.
     * @return
     *  The TrainCar contained within the node.
     */
    public TrainCar removeCursor(){
        TrainCarNode nodePtr = cursor;

        if(nodePtr.getNext() != null)
            cursor = nodePtr.getNext();
        else if (nodePtr.getPrev() != null)
            cursor = nodePtr.getPrev();
        else
            cursor = null;

        if (head != null && head == tail){
            head = null;
            tail = null;
        } else {
            nodeRemover(nodePtr);
        }

        numOfCars--;
        totLength -= nodePtr.getCar().getCarLength();
        totWeight -= nodePtr.getCar().getCarWeight() + nodePtr.getCar().getLoad().getWeight();
        totValue -= nodePtr.getCar().getLoad().getValue();
        if (nodePtr.getCar().getLoad().isDangerous() && dangerousCars > 0)
            dangerousCars--;
        return nodePtr.getCar();
    }

    /**
     * Removes the node passed to it
     * @param nodePtr
     *  The node to be removed
     */
    public void nodeRemover(TrainCarNode nodePtr) {
        if (nodePtr == head){
            head = head.getNext();
            head.setPrev(null);
        } else if (nodePtr == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            (nodePtr.getPrev()).setNext(nodePtr.getNext());
            (nodePtr.getNext()).setPrev(nodePtr.getPrev());
        }
    }

    /**
     * Removes all dangerous cars from the train, maintaining the order of the cars in the train.
     */
    public void removeDangerousCars(){
        TrainCarNode nodePtr = head;
        if (nodePtr == null)
            System.out.println("The train is empty hence there are no dangerous cars to be removed");
        else if (numOfCars == dangerousCars) {
            head = null;
            tail = null;
            cursor = null;
            numOfCars = 0;
            totWeight = 0;
            totValue = 0;
            totLength = 0;
            dangerousCars = 0;
            System.out.println("All cars were dangerous, hence all cars were removed, add new cars to train to proceed");
        } else if (dangerousCars != 0){
            while(dangerousCars != 0){
                if (nodePtr.getCar().getLoad().isDangerous()){
                    if(nodePtr == cursor){
                        if(nodePtr.getNext() != null && !nodePtr.getNext().getCar().getLoad().isDangerous())
                            cursor = nodePtr.getNext();
                        else if (nodePtr.getPrev() != null)
                            cursor = nodePtr.getPrev();
                        else{
                            TrainCarNode nodePtr2 = head;
                            while(true){
                                if (nodePtr2.getNext().getCar().getLoad().isDangerous()) {
                                    nodePtr2 = nodePtr2.getNext();
                                    continue;
                                }
                                cursor = nodePtr2.getNext();
                                break;
                            }
                        }
                    }

                    nodeRemover(nodePtr);
                    numOfCars--;
                    totLength -= nodePtr.getCar().getCarLength();
                    totWeight -= nodePtr.getCar().getCarWeight() + nodePtr.getCar().getLoad().getWeight();
                    totValue -= nodePtr.getCar().getLoad().getValue();
                    dangerousCars--;
                }
                nodePtr = nodePtr.getNext();
            }
            System.out.println("All dangerous cars were removed successfully");
        } else
            System.out.println("There are no dangerous cars in the train that need to be removed");
    }

    /**
     * Determines the number of TrainCar objects currently on the train.
     * @return
     *  The number of TrainCar objects on this train.
     */
    public int size(){
        return numOfCars;
    }

    /**
     * Returns the total length of the train in meters.
     * @return
     *  The sum of the lengths of each TrainCar in the train.
     */
    public double getLength(){
        return totLength;
    }

    /**
     * Returns the total weight in tons of the train.
     * @return
     *  The sum of the weight of each TrainCar plus the sum of the ProductLoad carried by that car.
     */
    public double getWeight(){
        return totWeight;
    }

    /**
     * Returns the total value of product carried by the train.
     * @return
     *  The sum of the values of each TrainCar in the train.
     */
    public double getValue(){
        return totValue;
    }

    /**
     * Whether there is a dangerous product on one of the TrainCar objects on the train.
     * @return
     *  Returns true if the train contains at least one TrainCar carrying a dangerous ProductLoad, false otherwise.
     */
    public boolean isDangerous(){
        return (dangerousCars != 0);
    }

    /**
     * Searches the list for all ProductLoad objects with the indicated name and sums together their weight and value
     * (Also keeps track of whether the product is dangerous or not), then prints a single ProductLoad record to the console.
     * @param name
     *  the name of the ProductLoad to find on the train.
     */
    public void findProduct(String name){
        TrainCarNode nodePtr = head;
        double totProductWeight = 0;
        double totProductValue = 0;
        double prodsFound = 0;
        String isD = "NO";
        boolean isFound = false;
        for (int i = 0; i < numOfCars; i++) {
            if ((nodePtr.getCar().getLoad().getName()).equals(name)){
                totProductWeight += nodePtr.getCar().getLoad().getWeight();
                totProductValue += nodePtr.getCar().getLoad().getValue();
                isFound = true;
                prodsFound++;
                if (nodePtr.getCar().getLoad().isDangerous())
                    isD = "YES";
            }
            nodePtr = nodePtr.getNext();
        }

        if (isFound){
            System.out.println("The following products were found on " + prodsFound + " cars:");
            System.out.println();
//                                                name   weigh val   danger
            System.out.println(String.format("   %-15s %-15s %-15s %-9s", "Name", "Weight (t)", "Value ($)", "Dangerous"));
            System.out.println("===============================================================");
            System.out.println(String.format("   %-15s %-15f %-15f %-9s", name, totProductWeight, totProductValue, isD));

        } else {
            System.out.println("No record of " + name + " onboard.");
        }
    }

    /**
     * Prints a neatly formatted table of the car number, car length, car weight, load name, load weight, load value,
     * and load dangerousness for all the car on the train.
     */
    public void printManifest(){
       if (numOfCars != 0){
           System.out.println(String.format("    %-43s%-5s", "CAR","LOAD:"));
           System.out.println(String.format("      %-5s %-15s %-15s   |   %-15s %-15s %-15s %-9s", "Num", "Length (m)", "Weight (t)", "Name", "Weight (t)", "Value ($)", "Dangerous"));
           System.out.println("    ==========================================+===============================================================");

           TrainCarNode nodePtr = head;

           for (int i = 0; i < numOfCars; i++) {
               String cursorOnNode = "      ";
               if (nodePtr == cursor){
                   cursorOnNode = "  ->  ";
               }
               System.out.println(String.format("%-6s%-5d %-15s %-15s   |   %-15s %-15s %-15s %-9s",cursorOnNode, i + 1, nodePtr.getCar().getCarLength(), nodePtr.getCar().getCarWeight()
               , nodePtr.getCar().getLoad().getName(), nodePtr.getCar().getLoad().getWeight(), nodePtr.getCar().getLoad().getValue(), ((nodePtr.getCar().getLoad().isDangerous())? "YES":"NO") ));

               nodePtr = nodePtr.getNext();
           }
       } else {
           System.out.println(" There are no cars in the train, add cars to show a manifest.");
       }
    }

    /**
     * Sets the load at the car currently referenced by cursor
     * @param newProductName
     *  Name of the load
     * @param newProductWeight
     *  Weight of the load in tons
     * @param newProductValue
     *  Value of the load in dollars
     * @param newIsD
     *  Indication of whether the product is dangerous
     */
    public void setLoadAtCursor (String newProductName, double newProductWeight, double newProductValue, boolean newIsD){
        ProductLoad newProduct = new ProductLoad();

        try {
            newProduct.setName(newProductName);
            newProduct.setWeight(newProductWeight);
            newProduct.setValue(newProductValue);
            newProduct.setDangerous(newIsD);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }

        TrainManager.getTrain().getCursor().getCar().setLoad(newProduct);

        System.out.println();
        System.out.println(newProductWeight + " tons of " + newProductName + " added to the current car.");
        totWeight += newProductWeight;
        totValue += newProductValue;
        if (newIsD)
            dangerousCars++;
    }

    /**
     * Returns a neatly formatted String representation of the train.
     * @return
     *  A neatly formatted string containing information about the train, including its size (number of cars), length in
     *  meters, weight in tons, value in dollars, and whether it is dangerous or not.
     */
    @Override
    public String toString() {

        return "Train: " + numOfCars + " cars, " + totLength + " meters, " + totWeight + " tons, " +
                "value = $" + totValue + ", " + (dangerousCars != 0? "IS":"NOT") + " dangerous";
    }

    /**
     * @return
     *  the cursor of the list
     */
    public TrainCarNode getCursor() {
        return cursor;
    }
}
