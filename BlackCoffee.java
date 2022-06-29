public class BlackCoffee extends CoffeeDecorator {
    public BlackCoffee(Coffee coffee) {
        super(coffee);
    }
    /**
     * This method adds a topping to the coffee
     * @param coffee object which has the order
     */
    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }
    /**
     * This method prints the chosen coffee.
     * @return a string that is the coffee/topping chosen
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee();
    }
    /**
     * This method returns the price of this topping/coffee
     * @return the cost of the chosen coffee
     */
    @Override
    public double Cost() {
        return this.coffee.Cost();
    }

}
