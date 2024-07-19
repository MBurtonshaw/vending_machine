package com.techelevator;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class Application {



	public static void main(String[] args) {
		//initializing a new Vending Machine object, vm,  and using run(vm)
		VendingMachine VM = setUpVM();
		System.out.println(ColorCode.CYAN + Utility.welcomeCuteCo + ColorCode.RESET);
		run(VM);
	}

	public static void run(VendingMachine vm) {
		//takes in user input and loops through the first menu
		try (Scanner userInput = new Scanner(System.in)) {
			while (!isFirstMenuOk(userInput, vm));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean isFirstMenuOk(Scanner userInput, VendingMachine vm){
		//printing the initial menu options
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.println("(3) Exit");
		String decision = userInput.nextLine();
		//if user selects '1', show the list of products
		if (decision.equals("1")) {
			System.out.println(vm.showProducts());
			//asks user to press 'enter', which takes them back to the initial menu
			System.out.print("Press enter to continue:");
			userInput.nextLine();
			System.out.println();
			//recaching; essentially saying that if the condition is satisfied,
			//pressing enter,
			// return to beginning of menu
			isFirstMenuOk(userInput, vm);
			return true;
			//if the user's decision is 2, then the user is directed to the second menu
		} else if (decision.equals("2")) {
			while (!isSecondMenuOk(userInput, vm));
			return true;
			//if the decision is 3, then vm.finishTransaction() is executed
		} else if (decision.equals("3")) {
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> vm.finishTransaction());
			try {
				String s = future.get();
				System.out.println(s);
			}catch (InterruptedException | ExecutionException e){
				System.out.println(e.getMessage());
			}
			return true;
			//if the decision is 4, hiddenMethod is executed
		} else if (decision.equals("4")) {
			hiddenMethod(vm,userInput,1);
			return false;
		} else {
			System.out.println("Please enter a valid number");
			return false;
		}
	}
	public static boolean isSecondMenuOk(Scanner userInput, VendingMachine vm) {
		//the second menu is printed for the user:
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		String decision = userInput.nextLine();
		//if the user decision is 1,run isStillFeedMoney until the conditions are satisfied within that method
		if (decision.equals("1")) {
			while (!isStillFeedMoney(userInput, vm));
			return true;
			//if the user decision is 2, run purchaseItem until conditions are satisfied within that method
		} else if (decision.equals("2")) {
			purchaseItem(userInput, vm);
			return true;
			//if decision is 3, print vm.finishTransaction()
		} else if (decision.equals("3")) {
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> vm.finishTransaction());
			try {
				String s = future.get();
				System.out.println(s);
			}catch (InterruptedException | ExecutionException e){
				System.out.println(e.getMessage());
			}
            return true;
		}
		//return false inherently, until a user selects an option
		return false;
	}

	public static void purchaseItem(Scanner userInput, VendingMachine vm) {
		//print product list for customer
		System.out.println(vm.showProducts());
		String input = userInput.nextLine();
		String result = vm.purchase(input);
		//if user input results in the following line, print that line
		if(result.equals("!!! ---Please enter a valid number--- !!!")){
			System.out.println(result);
			//then restart this method
			purchaseItem(userInput, vm);
		}else{
			//otherwise, just print the result of the purchase
			System.out.println(result);
		}
		//print the second menu to the customer
		while (!isSecondMenuOk(userInput, vm));
	}
	public static boolean isStillFeedMoney(Scanner userInput, VendingMachine vm){
		//method to handle user input while depositing money
		//calls the actual Customer.feedMoney() method if conditions are met
		System.out.println("Your current amount is " + vm.showCustomerBalance());
		System.out.println("Please enter amount you want to top-up or enter (m) to go back to menu: ");
		String input = userInput.nextLine();
		//conditional to check if the input is "m"
		if(input.equalsIgnoreCase("m")){
			//if it is, then the first menu is printed for the customer
			while (!isFirstMenuOk(userInput, vm));
			return true;
		}
		//if the input is not "m", then
		try{
			//amount = user input as an integer
			int amount = Integer.parseInt(input);
			//checking for negative values
			if(amount<=0){
				System.out.println("Please enter positive value.");
				//otherwise the machine accepts the amount of money entered
			}else{
				vm.feedMoney(amount);
				Utility.playSound("cash.wav");
				System.out.println("successfully top-up the amount.");
			}
		}catch (Exception e){
			System.out.println("!!!Invalid user input!!!");
		}
		return false;
	}
	public static VendingMachine setUpVM(){
		//a method to quickly instantiate a new VendingMachine object with its own Bank
		Bank bank = new Bank();
		bank.deposit(100,100,100,100,100, 100,100,100);
		//		Map<String, Item> items = new HashMap<>();
		//		Item duck = new Duck(1,"Yellow Duck", 305, 4);
		//		Item penguin1 = new Penguin(1,"Emperor Penguin", 180, 5);
		//		Item penguin2 = new Penguin(2,"Macaroni Penguin", 150, 5);
		//		Item cat = new Cat(1,"Black Cat", 125, 3);
		//		Item pony = new Pony(1, "Unicorn", 555, 5);
		//
		//		items.put(duck.getId(), duck);
		//		items.put(penguin1.getId(),penguin1);
		//		items.put(penguin2.getId(), penguin2);
		//		items.put(cat.getId(),cat);
		//		items.put(pony.getId(),pony);

		VendingMachine vendingMachine = new VendingMachine(bank);

		return vendingMachine;
	}

	public static void hiddenMethod(VendingMachine vm, Scanner scanner, int attempt
	){
		//if a user enters "4" during the first menu, this method is executed
		System.out.println("Please enter password:");
		String str = scanner.nextLine();
		if(str.equals("techelevator1")){
			//if user enters the correct password, techelevator1:
			try {
				//the salesReport method is triggered
				vm.saveSalesReport();
				System.out.println("Burying the hatchet, the task is six feet under.");
			}catch (IOException e){
				System.out.println("London Bridge Is Falling Down");
			}
		} else if (attempt<3) {
			//if the password attempt is incorrect, but less than 3
			System.out.println("You shall not pass!");
			if(attempt==2){
				System.out.println("C'mon everybody at Tech Elevator know the password");
			}

			hiddenMethod(vm, scanner, attempt+1);
		}else {


			System.out.println(ColorCode.RED + "Failed to enter password, self-destruction initiated...");
			try {
				Utility.playSound("alert.wav");
				//self destruct countdown
				for(int i = 19; i>=0; i--){
					if(i==0){
						Utility.playSound("kaboom.wav");
						System.out.println(Utility.KABOOM);
						System.out.print( ColorCode.RESET + "Press enter:");
						scanner.nextLine();
					}else{
						System.out.println("Self-destruct in: " + i);
					}

					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				// Handle the exception if the sleep is interrupted
				System.err.println("self-destruction was interrupted: " + e.getMessage());
			}
			//just kidding, text is reset
			System.out.println("Just kidding! Let's go back to the main menu. Shall we :)");
			//and user is taken back to the first menu
			while (!isFirstMenuOk(scanner, vm));

		}
	}


}
