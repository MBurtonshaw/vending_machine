package com.techelevator;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VendingMachine {
    private Bank bank;
    private Customer customer;

    private Map<String, Item> items = new HashMap<>();
    private final List<String> KEYS = Utility.KEYS;


    //constructor to add a map of items to the vending machine
    public VendingMachine(Bank bank, Map items) {
        this.bank = bank;
        this.items = items;
        customer = new Customer();
    }
    //constructor to load in the map of items for the vending machine from an external file
    public  VendingMachine(Bank bank){
        this.bank = bank;
        customer = new Customer();
        //calling method below to load data from a file
        loadProducts();
    }

    public String purchase(String id) {
        //creating an empty purchaseItem
        Item purchaseItem;
        //checking the items list for an item that matches the id entered by the user
        if(items.containsKey(id)){
            //if items list contains that key, that item is assigned to purchaseItem
            purchaseItem = items.get(id);
            //if the item is sold out:
            if (purchaseItem.getIsSoldOut()) {
                return  "Sorry, " + purchaseItem.getName() + " is sold out";
                //if the item's price is higher than the customer's balance
            } else if (purchaseItem.getPrice()> customer.getBalance()) {
                return ("Sorry, you don't have enough money, your current balance is "+showCustomerBalance()+"\nPlease top-up more.");
            } else {
                //otherwise, the price of the purchaseItem is deducted from the customer's balance
                customer.deductMoney(purchaseItem.getPrice());
                //1 of the item is subtracted from that item's quantity
                int newQty = purchaseItem.getQuantity()-1;
                purchaseItem.setQuantity(newQty);
                //crafting success message to show customer:
                String success = "";
                String sound = purchaseItem.getSound().split(",")[0] + ".wav";
                Utility.playSound(sound);
                success += ("Dispensing... " + purchaseItem.getName() + ": " + showItemPrice(purchaseItem) + ", your remaining balance is $" + showCustomerBalance() + "\n");
                success += (purchaseItem.getSound());

                //crafting line to print to csv file and log transaction
                String description = purchaseItem.getName() + " " + id + " " + showItemPrice(purchaseItem) + " " + showCustomerBalance();
                Utility.logToFile(description);
                return success;
            }
        }else {
            return("!!! ---Please enter a valid number--- !!!");
        }
    }


    public String showProducts() {
        String result = "";
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            //looping through each item in the entry map to get their values and set them
            //to the result string
            result += (entry.getKey() + ") " + entry.getValue().getName() + ", " + showItemPrice(entry.getValue()));
            result += "\n";
        }
        return result;
    }
    public String finishTransaction() {
        int amount = customer.getBalance();
        String result = "Thank you for using CuteCo.\nYour current amount is {$" + amount/100 + " and " + amount%100 + " cents}.\n";

        //creating a map to hold the customer's change to be returned
        Map<String,Integer> map = new HashMap<>();

        //while there is still money to be dispensed to the customer:
        while (amount>0){
            //if that amount is >= 2000 (cents) and bank.dispenseTwentyDollar(1) is true
            if(amount>=2000 && bank.dispenseTwentyDollar(1)){
                //key is $20, and that "$20 bill" is added to the map created above
                String key = KEYS.get(0);
                putNote(map,key);
                //amount left to be dispensed is reduced by $20, or 2000 cents
                amount -= 2000;
                /* The remainder of this while loop uses the same logic for the $20,
                *   but for $10s, $5s, and $1s, as well as quarters, dimes, nickels, and pennies
                *   until the amount to be dispensed == 0 */
            } else if (amount>=1000 && bank.dispenseTenDollar(1)) {
                String key = KEYS.get(1);
                putNote(map,key);
                amount -= 1000;
            } else if (amount>= 500 && bank.dispenseFiveDollar(1)) {
                String key = KEYS.get(2);
                putNote(map,key);
                amount -= 500;
            } else if (amount>=100 && bank.dispenseOneDollar(1)) {
                String key = KEYS.get(3);
                putNote(map,key);
                amount -= 100;
            } else if (amount>=25 && bank.dispenseQuarterCent(1)) {
                String key = KEYS.get(4);
                putNote(map,key);
                amount -= 25;
            }else if (amount>=10 && bank.dispenseTenCent(1)) {
                String key = KEYS.get(5);
                putNote(map,key);
                amount -= 10;
            }else if (amount>=5 && bank.dispenseFiveCent(1)) {
                String key = KEYS.get(6);
                putNote(map,key);
                amount -= 5;
            }else if(amount>=1 && bank.dispenseOneCent(1)){
                String key = KEYS.get(7);
                putNote(map,key);
                amount -= 1;
            }else{
                System.out.println("Sorry, not enough money in Bank, please call customer service at (123)-456-7890\nor please enter your phone number: so we can get back to you ASAP." );
                Scanner scanner = new Scanner(System.in);
                customer.setInfo(scanner.nextLine());
                Utility.logToFile("Not enough of money to return to the customer. Customer: " + customer.getInfo());
            }
        }
        //resetting the customer to a new customer, thereby also resetting their bank values
        customer = new Customer();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> writeBill(map));

        try {
            String bill = future.get(); // Wait for the CompletableFuture to complete and get the result
            result += bill;
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error calling write bill: " + e.getMessage());
        }
        return result;
    }

    public boolean feedMoney(int amountInDollar){
        //this is a method to essentially be called any time customer.feedMoney() happens
       boolean result = customer.feedMoney(amountInDollar, this.bank);
       //this string is created and then written to the .csv file with the Utility class
       String description = "FEED MONEY: $" + String.format("%.2f", amountInDollar/100.0) + " " + showCustomerBalance();
       Utility.logToFile(description);
       return result;
    }

    public String showCustomerBalance(){
        //converting balance to "dollars" from "cents"
        double amount = customer.getBalance() / 100.0;
        //formatting the string for the user
        return "$" + String.format("%.2f", amount);
    }

    public String showItemPrice(Item item){
        double amount = item.getPrice() / 100.0;
        return "$"+ String.format("%.2f", amount);
    }

    protected String writeBill(Map<String,Integer> map){

        String bill = "Dispensing:\n";
        //manually created a list of keys corresponding to possible money denominations

        //looping through the map provided to this method to check for corresponding keys
        //to return the denomination, and amount of change returned
        for(String key: KEYS){
            if (map.containsKey(key)) {
                bill += key + "\t: " + map.get(key) +"\n";
                int qty = map.get(key);
                for(int i = 0; i<qty; i++) {
                    if (key.equals("Quarter") || key.equals("Dime") || key.equals("Nickel") || key.equals("Penny")) {
                        Utility.playSound("coin.wav");
                    } else {
                        Utility.playSound("cashOut.wav");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return bill;
    }

    private void putNote(Map<String,Integer> map, String key){
        if(map.containsKey(key)){
            map.put(key,map.get(key)+1);
        }else{
            map.put(key,1);
        }
    }

    private void loadProducts() {
        //creating a new File and setting it to vendingmachine.csv
        File file = new File("vendingmachine.csv");
        if(file.exists() && file.isFile()){
            //checking in the file exists & is a file
            try(Scanner scanner = new Scanner(file)){
                //while that file still has lines to read:
                while (scanner.hasNextLine()){
                    String lineOfData = scanner.nextLine();
                    //split that line into individual words
                    String[] data = lineOfData.split(",");
                    //setting price to the value at the third position in a line
                    int price = (int)(Double.parseDouble(data[2]) * 100);
                    //setting quantity to the value at the fifth position in a line
                    int qty = Integer.parseInt(data[4]);
                    Item item;

                    //a conditional process that checks the fourth position in a line
                    //for what type of item it is. Example: if it's a "Cat", then a new
                    //"Cat" will be created with data to complete the class being pulled
                    //from the same line, i.e. data[0], data[1].
                    //We had to differentiate which type was being created because we are using different
                    //interfaces to further define/override our Items objects.
                    switch (data[3]){
                        case "Cat":
                            item = new Cat(data[0],data[1],price,qty);
                            break;
                        case "Duck":
                            item = new Duck(data[0],data[1],price,qty);
                            break;
                        case "Penguin":
                            item = new Penguin(data[0],data[1],price,qty);
                            break;
                        case "Pony":
                            item = new Pony(data[0],data[1],price,qty);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + data[3]);
                    }
                    //adding that item to the items list
                    items.put(item.getId(),item);
                }
            }catch (FileNotFoundException e){
                System.out.println("Fail to load initial CSV file.");
            }catch (NumberFormatException e){
                System.out.println("There are some data type error in CSV file.");
            }catch (IllegalStateException e){
                System.out.println("There is un-excepted product type in the CSV file. ");
            }
        }

    }

    public void saveSalesReport() throws IOException {
        //creating a new File, saleReport.csv
        File file = new File("saleReport.csv");
        double totalSales = 0.0;
        //checking if the file exists already. If not, it's created
        if(!file.exists()){
            file.createNewFile();
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            //looping through the keys in items
            for (String id : items.keySet()) {
                //creating a salesQty variable and setting it to the amount of the item that sold
                int salesQty = items.get(id).getInitialQuantity() - items.get(id).getQuantity();
                //creating a productSales variable and setting it to the amount of products sold * their price
                int productSales = salesQty * items.get(id).getPrice();
                //setting the value back to "dollars" from "cents"
                totalSales += productSales/100.0;

                //writing the transactions to the file
                writer.println(items.get(id).getName() + "," + salesQty);
            }
            writer.println(); // Blank line
            //writing total sales to the file
            writer.printf("TOTAL SALES $%.2f%n", totalSales);
        }
    }
}
