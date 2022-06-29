public class Sugar extends CoffeeDecorator{
    public Sugar(Coffee coffee){
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
     * @return a string that adds the coffee/topping chosen to the order
     */

    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " and sugar";
    }
    /**
     * This method returns the price of this topping/coffee
     * @return the cost of the chosen coffee
     */
    @Override
    public double Cost() {
        return this.coffee.Cost()+0.05;
    }
}
