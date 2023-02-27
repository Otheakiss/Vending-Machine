package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {
    // Main Menu Options
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

    // Purchase Menu Options
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    //Class variables
    private Menu menu;
    private List<Item> listOfItems = new ArrayList<>();
    private Inventory inventory;
    private BigDecimal money = BigDecimal.ZERO;
    private Log log = new Log();

    //Scanner for user input
    Scanner userInput = new Scanner(System.in);

    //Constructor for main menu
    public VendingMachineCLI(Menu menu) throws FileNotFoundException {
        this.menu = menu;
    }

    //Read inventory from file location (provided)
    private void generateInventory() throws FileNotFoundException {
        File itemListFile = new File("C:\\Users\\Student\\workspace\\Mod1Capstone\\module-1-capstone-team-5\\capstone\\vendingmachine.csv");

        //Creating items and adding them to list of items available
        try (Scanner fileScanner = new Scanner(itemListFile)) {
            while (fileScanner.hasNext()) {
                String lineFromFile = fileScanner.nextLine();
                Item item = new Item(lineFromFile);
                listOfItems.add(item);
            }
        }
    }

    //Runs core program, has main and purchase menu options
    public void run() throws IOException {
        generateInventory();
        inventory = new Inventory(listOfItems);
        String choice = "";
        while (!choice.equals(MAIN_MENU_OPTION_EXIT)) {
             choice = (String) menu.getChoiceFromStrings(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                displayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                purchaseMenu();
            } else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                SalesReport salesReport = new SalesReport();
                salesReport.reportOnInventory(inventory);
            }
        }
    }

    private void displayItems() {
        // display vending machine items
        System.out.println(inventory.generateInventoryList());
    }

    private void purchaseMenu() throws IOException {
        String purchaseChoice = "";
        while (!purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
            System.out.println("Current Money Provided : " + CurrencyHelper.makeItMoney(money));
            purchaseChoice = (String) menu.getChoiceFromStrings(PURCHASE_MENU_OPTIONS);
            if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                moneyAddedToAccount();
            } else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                handlePurchase();
            }
        }
        dispenseChange(money);
        money = BigDecimal.ZERO;
    }

    private void handlePurchase() throws IOException {
        displayItems();
        System.out.println("Current Money Provided : " + CurrencyHelper.makeItMoney(money));
        System.out.println("Please enter Slot location Number for item desired.");
        String itemLocationFromUser = userInput.nextLine();
        if (inventory.hasItem(itemLocationFromUser)){
            Item wantToPurchase = inventory.getItemByLocation(itemLocationFromUser);
            if(wantToPurchase.inStock()) {
                if (inventory.canPurchase(wantToPurchase, money)){
                    // Dispense item
                    System.out.println(inventory.dispense(wantToPurchase));
                    // Update Money
                    money = money.subtract(wantToPurchase.getPrice());
                    log.logItemBought(wantToPurchase, money);
                }else{
                    System.out.println("Not enough money, FEED ME MONEY NOW!\n");
                }
            } else {
                System.out.println("SOLD OUT. Try reading the stock next time!\n");
            }
        } else {
            System.out.println("You must have fat fingered your choice, please try again.");
        }
    }

    private void dispenseChange(BigDecimal money) throws IOException {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while(money.compareTo(BigDecimal.valueOf(0.25f)) >= 0) {
            money = money.subtract(BigDecimal.valueOf(0.25f));
            quarters++;
        }

        while(money.compareTo(BigDecimal.valueOf(0.1f)) >= 0) {
            money = money.subtract(BigDecimal.valueOf(0.1f));
            dimes++;
        }

        while(money.compareTo(BigDecimal.valueOf(0.05f)) >= 0) {
            money = money.subtract(BigDecimal.valueOf(0.05f));
            nickels++;
        }

        printChange(quarters, dimes, nickels);

    }

    private void printChange(int quarters, int dimes, int nickels) throws IOException {
        System.out.println("Don't forget your change!\nQuarters: "
                + quarters + "\nNickels: "
                + nickels + "\nDimes: "
                + dimes);

        log.logGiveChange(money, BigDecimal.ZERO);
    }

    private void moneyAddedToAccount() {
        System.out.println("How much money do you want to add? Please pick a whole number.");

        try {
            String moneyTaken = userInput.nextLine();
            BigDecimal value = BigDecimal.valueOf(Long.valueOf(moneyTaken));
            money = money.add(value);
            log.logMoneyAdded(value, money);

        } catch (Exception e) {
            System.out.println("Please read the directions and select a whole number \n");
        }

    }

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

}
