public class BasicCoffee implements Coffee{
    /**
     * This method adds a topping to the coffee
     * @param coffee object which has the order
     */
    @Override
    public void addTopping(Coffee coffee){

    }
    /**
     * This method prints the chosen coffee.
     * @return a string that is the coffee/topping chosen
     */

    @Override
    public String printCoffee() {

        return "A black coffee";
    }
    /**
     * This method returns the price of this topping/coffee
     * @return the cost of the chosen coffee
     */
    public double Cost(){
        return 0.85;
    }


}
