/**
 *Coffee Application
 *This is a program that allows users to view the inventory of coffee,create multiple coffee orders,update the inventory,and creates a log of the receipts.
 *CS 160L
 *@author Pranav Nambiar

 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.io.*;
import java.util.Date;
public class Main {
    static int inventory[]=new int[7];
    static Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner userOrders = new Scanner(System.in);
        Scanner CafeApplication = new Scanner(System.in);
        System.out.println("Cafe Application Running...");
       int input=0;
        while (input ==0)
        {
            System.out.println("Press 1 : Read Inventory");
            System.out.println("Press 2 : Create Coffee Order");
            System.out.println("Press 3: Update Inventory");
            System.out.println("Press 4: Update log file");
            System.out.println("Press 5: Manually update Inventory");
            System.out.println("Press 6: Exit the application");
            switch (CafeApplication.nextLine()) {
                case "1":
                    inventorReader();
                    break;
                case "2":
                    ArrayList<String> Item = new ArrayList<>();
                    ArrayList<Double> price = new ArrayList<>();
                    ArrayList<String> Temp2;
                    if (inventory[0] == 0) {
                        System.out.println("Out of coffee, visit us later.");

                    }
                    else {
                        System.out.println("Coffee order created. Select toppings for the first coffee:");
                        String line = "yes";
                        double d;
                        do {
                            if (inventory[0] == 0) {
                                System.out.println("Order completed. No more coffees.");
                                break;
                            }
                            Temp2 = CreateOrder();
                            d = Double.parseDouble(Temp2.get(1));
                            Item.add(0, Temp2.get(0));
                            price.add(0, d);

                            System.out.println("Do you want to add another coffee to this order? - yes or no");
                        }
                        while (!(line = userOrders.nextLine()).equals("no"));
                        {

                            stack.push(PrintOrder(Item, price));

                        }
                    }
                    break;
                case "3":
                    inventoryWriter(inventory);
                    break;
                case "4":
                    logWriter(stack);
                    break;
                case "5":
                    inventoryUpdater();
                    break;
                case "6":
                    inventoryWriter(inventory);
                    logWriter(stack);
                    input = 1;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again");

            }
        }
    }
    /**
     * This method takes the Item and price array and creates a string that is the receipt.
     * @param Item- the type of coffee/snacks that is ordered
     * @param price- the total price of each coffee
     * @return the receipt which displays the total cost of the order along with a break-down of the order
     */

    public static String PrintOrder(ArrayList<String> Item, ArrayList<Double> price) {
        StringBuilder str = new StringBuilder();
        double total = 0;
        str.append("Receipt: ");
        str.append("\n");
        for (int i = 1; i <= Item.size(); i++) {
            str.append("Item " + i + ": " + Item.get(i - 1) + " | cost:" + price.get(i - 1));
            str.append("\n");
            total = total + price.get(i - 1);
        }
        str.append("Total cost of order: " + total);
        return str.toString();
    }
    /**
     * This method uses wrapper class to create the order and store it in an array
     * @return an array containing the order and price
     */

    public static ArrayList<String> CreateOrder() {
        Scanner userFeedback = new Scanner(System.in);
        ArrayList<String> coffeeOrder = new ArrayList<String>();
        Coffee basicCoffee = new BasicCoffee();
        int in = 0;
        while (in != 1) {
            System.out.println("Enter the number for toppings/snacks: 0.Croissant  1. Milk 2. Sugar 3. WhippedCream 4. Espresso 5. Hotwater 6. Finish order");
            int topping = userFeedback.nextInt();
            switch (topping) {
                case 0:
                    if(inventory[6]!=0) {
                        basicCoffee = new Croissant(basicCoffee);
                        inventory[6]=inventory[6]-1;
                    }
                    else{
                        System.out.println("Out of Croissants.Come back later");
                    }
                    break;
                case 1:
                    if(inventory[1]!=0){
                        basicCoffee = new Milk(basicCoffee);
                        inventory[1]=inventory[1]-1;
                    }
                    else{
                        System.out.println("Out of Milk.Try a different topping");
                    }

                    break;
                case 2:
                    if(inventory[4]!=0){
                    basicCoffee = new Sugar(basicCoffee);
                    inventory[4]=inventory[4]-1;
                    }
                    else{
                        System.out.println("Out of Sugar.Try a different topping");
                }
                    break;
                case 3:
                    if(inventory[5]!=0) {
                        basicCoffee = new WhippedCream(basicCoffee);
                        inventory[5]=inventory[5]-1;
                    }
                    else{
                        System.out.println("Out of WhippedCream.Try a different topping");
                    }
                    break;
                case 4:
                    if(inventory[3]!=0) {
                        basicCoffee = new Espresso(basicCoffee);
                        inventory[3]=inventory[3]-1;
                    }
                    else{
                        System.out.println("Out of Espresso.Try a different topping");
                    }
                    break;
                case 5:
                    if(inventory[2]!=0) {
                        basicCoffee = new Hotwater(basicCoffee);
                        inventory[2]=inventory[2]-1;
                    }
                    else{
                        System.out.println("Out of hot water.Try a different topping");
                    }
                    break;
                case 6:
                    inventory[0]=inventory[0]-1;
                    in = 1;
                    break;

            }
        }
        coffeeOrder.add(basicCoffee.printCoffee());
        String t = String.valueOf(basicCoffee.Cost());
        coffeeOrder.add(t);
        return coffeeOrder;
    }
    /**
     * This reads the inventory and displays it to the user and creates array with the amount of each coffee.
     * @return inventory array that contains the amount of each coffee type.
     */
    public static int[] inventorReader() {
        char a[]=new char[7];
        int i=0;
    try {
    File file = new File("C:\\Users\\pnamb\\IdeaProjects\\CS160Labs\\src\\Inventory.txt");
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    String st;
    System.out.println("Current items in the inventory: ");
    while ((st = br.readLine()) != null) {
        System.out.println(st);
        a[i] = st.charAt(st.length()-1);
        i++;
    }
    fr.close();
    }
    catch(IOException e){
    e.printStackTrace();
    }
        for(int j=0;j<7;j++){
            inventory[j]=a[j]-'0';
        }

     return inventory;
    }
    /**
     * This method updates the inventory after a coffee is created.
     * @param inventory- the array containing the amount of coffees.
     */
    public static void inventoryWriter(int inventory []){
        try {
            File file = new File("C:\\Users\\pnamb\\IdeaProjects\\CS160Labs\\src\\Inventory.txt");
            FileWriter fw = new FileWriter(file,false);
            fw.write("Black Coffee = "+inventory[0]);
            fw.write("\n");
            fw.write("Milk = "+inventory[1]);
            fw.write("\n");
            fw.write("HotWater = "+inventory[2]);
            fw.write("\n");
            fw.write("Espresso = "+inventory[3]);
            fw.write("\n");
            fw.write("Sugar = "+inventory[4]);
            fw.write("\n");
            fw.write("WhippedCream = "+inventory[5]);
            fw.write("\n");
            fw.write("Croissant = "+inventory[6]);
            fw.close();
            System.out.println("Successfully updated inventory");


        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * This method takes the stack containing the receipt of the order and writes it onto the logfile.
     * @param stack- a stack containing the receipt of the order
     */
    public static void logWriter(Stack<String> stack) {
        try {
            File file = new File("C:\\Users\\pnamb\\IdeaProjects\\CS160Labs\\src\\LogFile2.txt");
            FileWriter output = new FileWriter(file, true);
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            if (stack.empty()) {
                System.out.println("Nothing to log.");
            }
            else {
                output.write("\n\nWriting orders from stack " + formatter.format(date));
                while (!stack.empty()) {
                    output.write(stack.pop());
                    System.out.println("Successfully updated the log file");
                }
                output.flush();
                output.close();


            } }
        catch(Exception e){
                e.printStackTrace();
            }


    }
    /**
     * This method allows the user to manually add coffee to the inventory.
     */
    public static void inventoryUpdater(){
        Scanner sc1=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
        int j;
        int c=0;
        while(c==0) {
            System.out.println("Choose which inventory item to change: 0 for Black Coffee. 1 for Milk. 2 for HotWater. 3 for Espresso. 4 for Sugar. 5 for Whipped Cream. 6 for Croissant. 7 to finish updating.");
            int i=sc1.nextInt();
            switch (i) {
                case 0:
                    System.out.println("Enter the amount of Black Coffee to add to inventory");
                    j = sc2.nextInt();
                    inventory[0] = inventory[0] + j;
                    break;
                case 1:
                    System.out.println("Enter the amount of Milk to add to inventory");
                    j = sc2.nextInt();
                    inventory[1] = inventory[1] + j;
                    break;
                case 2:
                    System.out.println("Enter the amount of HotWater to add to inventory");
                    j = sc2.nextInt();
                    inventory[2] = inventory[2] + j;
                    break;
                case 3:
                    System.out.println("Enter the amount of Espresso to add to inventory");
                    j = sc2.nextInt();
                    inventory[3] = inventory[3] + j;
                    break;
                case 4:
                    System.out.println("Enter the amount of Sugar to add to inventory");
                    j = sc2.nextInt();
                    inventory[4] = inventory[4] + j;
                    break;
                case 5:
                    System.out.println("Enter the amount of Whipped Cream to add to inventory");
                    j = sc2.nextInt();
                    inventory[5] = inventory[5] + j;
                    break;
                case 6:
                    System.out.println("Enter the amount of Croissant to add to inventory");
                    j = sc2.nextInt();
                    inventory[6] = inventory[6] + j;
                    break;
                case 7:
                    System.out.println("Exiting inventory updater");
                    c=1;
                    break;

            }
        }
        inventoryWriter(inventory);

    }

}