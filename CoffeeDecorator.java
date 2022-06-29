public abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;
    public CoffeeDecorator(Coffee coffee){
    this.coffee=coffee;

    }
    /**
     * This method adds a topping to the coffee
     * @param coffee object which has the order
     */
    public void addTopping(Coffee coffee) {
        this.coffee.addTopping(coffee);
    }


    /**
     * This method prints the chosen coffee.
     * @return a string that adds the coffee/topping chosen to the order
     */
    public String printCoffee() {
       return this.coffee.printCoffee();

    }
    /**
     * This method returns the price of this topping/coffee
     * @return the cost of the chosen coffee
     */
    public  abstract double Cost();



}
