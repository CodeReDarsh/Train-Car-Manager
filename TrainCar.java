/**
 * The <code>TrainCar</code> class constructs and contains all the methods
 * required to manipulate <code>TrainCar</code> type objects. It represents a train's car
 * which has a length in meters and a weight in tons.
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 */
public class TrainCar {
    private final double carLength;
    private final double carWeight;
    private ProductLoad load;


    /**
     * Creates a new empty train car with some specified length and weight
     * @param carLength
     *  The length of the train car in meters
     * @param carWeight
     *  The weight of the train car in tons
     */
    public TrainCar(double carLength, double carWeight){
        load = new ProductLoad();
        if (carLength <= 0)
            throw new IllegalArgumentException("invalid car length, car length cannot be less than or equal to 0");
        if (carWeight <= 0)
            throw new IllegalArgumentException("invalid car weight, car weight cannot be less than or equal to 0");
        this.carLength = carLength;
        this.carWeight = carWeight;
    }

    /**
     * Return the length of the car
     * @return
     *  the length of the car in meters
     */
    public double getCarLength() {
        return carLength;
    }

    /**
     * Returns the weight of the car
     * @return
     *  The weight of the car in tons
     */
    public double getCarWeight() {
        return carWeight;
    }

    /**
     * Returns a ProductLoad object representing the load of the car
     * @return
     *  an object representing the load of the car
     */
    public ProductLoad getLoad() {
        return load;
    }

    /**
     * Sets the load of the train car
     * @param load
     *  The load of the car
     */
    public void setLoad(ProductLoad load) {
        this.load = load;
    }

    /**
     * Checks if the train car is empty
     * @return true is car is empty else false
     */
    public boolean isEmpty(){
        if (load != null) return true;
        return false;
    }

}
