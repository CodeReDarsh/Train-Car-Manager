/**
 * The <code>TrainCarNode</code> class constructs and contains all the methods required to manipulate <code>TrainCarNode</code>
 * type objects. It acts as a node wrapper around a TrainCar object.
 *
 * The class contains two TrainCarNode references, one for the next node in the chain, one for the previous node in the chain
 * and one TrainCar reference variable containing the data.
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 */
public class TrainCarNode {
    private TrainCarNode prev;
    private TrainCarNode next;
    private TrainCar car;

    /**
     * This is the default constructor of the <code>TrainCarNode</code> class.
     * Creates an empty train car node containing default values
     */
    public TrainCarNode (){
        prev = null;
        next = null;
        car = null;
    }

    /**
     * Creates a train car node containing a train car as a data
     * @param car
     *  The train car to be stored
     */
    public TrainCarNode (TrainCar car){
        prev = null;
        next = null;
        this.car = car;
    }

    /**
     * @return a reference to the previous node in the linked list
     */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * Sets the link containing a reference to the previous node to a new reference
     * @param prev
     *  The new reference to set the link to
     */
    public void setPrev(TrainCarNode prev) {
        this.prev = prev;
    }

    /**
     *
     * @return A reference to the next node in the linked list
     */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * Sets the link containing a reference to the next node to a new reference
     * @param next
     *  The new reference to set the link to
     */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     *
     * @return The reference to the data stored in the node which here, is the data about the train car
     */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Sets the data of the train car node as the specified car
     * @param car
     *  The car to be set as the data of the node
     */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     *
     * @return A string representation of the node
     */
    @Override
    public String toString() {
        return "TrainCarNode{" +
                "car=" + car +
                '}';
    }
}