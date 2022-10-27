/**
 * The <code>ProductLoad</code> class constructs and contains all the methods
 * required to manipulate <code>ProductLoad</code> type objects. It represents a product load
 * which has a name, a weight in tons, a value in dollars and can be dangerous or safe.
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 */
public class ProductLoad {
    private String name;
    private double weight;
    private double value;
    private boolean isDangerous;

    /**
     * This is a default constructor of the Product Load class
     * which creates a new Product Load object with default values
     */
    public ProductLoad(){
        name = "Empty";
        weight = 0;
        value = 0;
        isDangerous = false;
    }

    /**
     * This is a constructor used to create a new Product Load object
     * @param name
     * The name used to keep track of the load
     * @param weight
     * The amount the load weighs in tons
     * @param value
     * The monetary value of the load contained in dollars
     * @param isDangerous
     * The indicator of whether the load is dangerous
     */
    public ProductLoad(String name, double weight, double value, boolean isDangerous) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.isDangerous = isDangerous;
    }

    /**
     * Returns the load's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set's the load's name
     * @param name
     *  The name of the load
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the loads weight
     * @return weight
     *
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set's the load's weight
     * @param weight
     *  The weight of the load
     */
    public void setWeight(double weight) {
        if (weight <= 0)
            throw new IllegalArgumentException("\nInvalid input, weight cannot be negative or equal to 0, please enter a correct value to set load");
        this.weight = weight;
    }

    /**
     * Return's the value of the load
     * @return load
     */
    public double getValue() {
        return value;
    }

    /**
     * Set's the load's value
     * @param value
     *  The value of the load
     */
    public void setValue(double value) {
        if (value < 0)
            throw new IllegalArgumentException("\nInvalid input, value is negative, please enter a correct value to set load");
        this.value = value;
    }

    /**
     * Returns a boolean value of whether the load is dangerous
     * @return isDangerous
     */
    public boolean isDangerous() {
        return isDangerous;
    }

    /**
     * Set's the load's danger value
     * @param dangerous
     *  The danger value of the load
     */
    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }
}
