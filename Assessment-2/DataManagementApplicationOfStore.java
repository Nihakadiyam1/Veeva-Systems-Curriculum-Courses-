import java.util.*;
import java.util.HashMap;

public class DataManagementApplicationOfStore {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, HashMap<String, Integer>> storeDetails = new HashMap<String, HashMap<String, Integer>>();

    public static void main(String[] args) {

        int choice = 0;

        while (choice != 5) {

            System.out.println("\n**** STORE MENU ****");
            System.out.println("1. Add Store and Products");
            System.out.println("2. Display Products of a Store");
            System.out.println("3. Highest Priced Product in Store");
            System.out.println("4. Find Product in Stores");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addStore();
            } else if (choice == 2) {
                displayStore();
            } else if (choice == 3) {
                highestPrice();
            } else if (choice == 4) {
                searchProduct();
            } else if (choice == 5) {
                System.out.println("Thank You, see you again!");
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    static void addStore() {
        System.out.print("Enter Store ID: ");
        String storeId = sc.nextLine();

        HashMap<String, Integer> productDetails = new HashMap<String, Integer>();

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Product name: ");
            String productName = sc.nextLine();

            System.out.print("Price: ");
            int productPrice = sc.nextInt();
            sc.nextLine();

            productDetails.put(productName, productPrice);
        }

        storeDetails.put(storeId, productDetails);
        System.out.println("Store added successfully");
    }

    static void displayStore() {
        System.out.print("Enter Store ID: ");
        String storeId = sc.nextLine();

        if (storeDetails.containsKey(storeId)) {
            HashMap<String, Integer> products = storeDetails.get(storeId);

            for (Map.Entry<String, Integer> item : products.entrySet()) {
                System.out.println(item.getKey() + " : " + item.getValue());
            }
        } else {
            System.out.println("Store not found");
        }
    }

    static void highestPrice() {
        System.out.print("Enter Store ID: ");
        String storeId = sc.nextLine();

        if (storeDetails.containsKey(storeId)) {

            HashMap<String, Integer> products = storeDetails.get(storeId);
            int max = 0;
            String product = "";

            for (Map.Entry<String, Integer> item : products.entrySet()) {
                if (item.getValue() > max) {
                    max = item.getValue();
                    product = item.getKey();
                }
            }

            System.out.println("Highest priced product:");
            System.out.println(product + " = " + max);
        } else {
            System.out.println("Store not found");
        }
    }

    static void searchProduct() {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Map.Entry<String, HashMap<String, Integer>> store : storeDetails.entrySet()) {
            if (store.getValue().containsKey(name)) {
                System.out.println("Available in store: " + store.getKey());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Product not found in any store");
        }
    }
}