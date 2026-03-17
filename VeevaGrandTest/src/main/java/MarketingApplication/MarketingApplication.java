package MarketingApplication;

import java.time.LocalDate;
import java.util.*;

class Item {
    String name;
    double price;
    int quantityInHand;
    int reorderLevel;

    Item(String name, double price, int quantityInHand, int reorderLevel) {
        this.name = name;
        this.price = price;
        this.quantityInHand = quantityInHand;
        this.reorderLevel = reorderLevel;
    }
}

class Customer {
    String name;
    String address;
    String phone;
    String email;

    Customer(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}

class OrderItem {
    Item item;
    int quantity;

    OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

class Order {
    static int counter = 1;
    int orderId;
    Customer customer;
    List<OrderItem> items;
    LocalDate date;
    double totalAmount;

    Order(Customer customer, List<OrderItem> items) {
        this.orderId = counter++;
        this.customer = customer;
        this.items = items;
        this.date = LocalDate.now();
        this.totalAmount = calculateTotal();
    }

    double calculateTotal() {
        double sum = 0;
        for (OrderItem oi : items) {
            sum += oi.item.price * oi.quantity;
        }
        return sum;
    }
}

class OrderProcessingSystem {
    Map<String, Item> itemMap = new HashMap<>();
    Map<String, Customer> customerMap = new HashMap<>();
    Map<Integer, Order> orderMap = new HashMap<>();

    void addItem(Item item) {
        itemMap.put(item.name, item);
    }

    void addCustomer(Customer customer) {
        customerMap.put(customer.name, customer);
    }

    void updateStock(String itemName, int quantity) {
        Item item = itemMap.get(itemName);
        if (item != null) {
            item.quantityInHand += quantity;
            System.out.println("Stock updated successfully");
        } else {
            System.out.println("Item not found");
        }
    }

    void placeOrder(String customerName, Map<String, Integer> orderItems) {
        Customer customer = customerMap.get(customerName);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }

        List<OrderItem> items = new ArrayList<>();

        for (String itemName : orderItems.keySet()) {
            Item item = itemMap.get(itemName);
            int qty = orderItems.get(itemName);

            if (item == null) {
                System.out.println("Item not found: " + itemName);
                return;
            }

            if (item.quantityInHand - qty <= item.reorderLevel) {
                System.out.println("Cannot place order. Item " + itemName + " reached reorder level.");
                return;
            }

            items.add(new OrderItem(item, qty));
        }

        for (OrderItem oi : items) {
            oi.item.quantityInHand -= oi.quantity;
        }

        Order order = new Order(customer, items);
        orderMap.put(order.orderId, order);

        System.out.println("Order placed successfully. Order ID: " + order.orderId);
    }

    void getItemByName(String name) {
        Item item = itemMap.get(name);
        if (item != null) {
            System.out.println("Name: " + item.name + " Price: " + item.price + " Qty: " + item.quantityInHand);
        } else {
            System.out.println("Item not found");
        }
    }

    void getItemsByPrice(double price) {
        boolean found = false;
        for (Item item : itemMap.values()) {
            if (item.price == price) {
                System.out.println(item.name);
                found = true;
            }
        }
        if (!found) System.out.println("No items found");
    }

    void getOrderById(int id) {
        Order order = orderMap.get(id);
        if (order != null) {
            System.out.println("Order ID: " + order.orderId + " Total: " + order.totalAmount);
        } else {
            System.out.println("Order not found");
        }
    }

    void getOrdersByCustomer(String name) {
        boolean found = false;
        for (Order order : orderMap.values()) {
            if (order.customer.name.equals(name)) {
                System.out.println("Order ID: " + order.orderId);
                found = true;
            }
        }
        if (!found) System.out.println("No orders found");
    }

    void getHighestAndLowestOrder() {
        if (orderMap.isEmpty()) return;

        Order max = Collections.max(orderMap.values(), Comparator.comparingDouble(o -> o.totalAmount));
        Order min = Collections.min(orderMap.values(), Comparator.comparingDouble(o -> o.totalAmount));

        System.out.println("Highest Order: " + max.orderId + " Amount: " + max.totalAmount);
        System.out.println("Lowest Order: " + min.orderId + " Amount: " + min.totalAmount);
    }

    void getOrdersLastWeek() {
        LocalDate now = LocalDate.now();
        for (Order order : orderMap.values()) {
            if (order.date.isAfter(now.minusDays(7))) {
                System.out.println("Order ID: " + order.orderId);
            }
        }
    }

    void getOrdersLastMonth() {
        LocalDate now = LocalDate.now();
        for (Order order : orderMap.values()) {
            if (order.date.isAfter(now.minusMonths(1))) {
                System.out.println("Order ID: " + order.orderId);
            }
        }
    }
}

public class MarketingApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderProcessingSystem ops = new OrderProcessingSystem();

        while (true) {
            System.out.println("\n1.Add Item \n2.Add Customer \n3.Place Order \n4.Update Stock \n5.Search Item \n6.Items by Price \n7.Order by ID \n8.Orders by Customer \n9.High/Low Order \n10.Last Week \n11.Last Month \n0.Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name price qty reorderLevel: ");
                    String name = sc.next();
                    double price = sc.nextDouble();
                    int qty = sc.nextInt();
                    int rl = sc.nextInt();
                    ops.addItem(new Item(name, price, qty, rl));
                    break;

                case 2:
                    System.out.print("Enter name address phone email: ");
                    String cname = sc.next();
                    String addr = sc.next();
                    String phone = sc.next();
                    String email = sc.next();
                    ops.addCustomer(new Customer(cname, addr, phone, email));
                    break;

                case 3:
                    System.out.print("Enter customer name: ");
                    String cust = sc.next();
                    Map<String, Integer> map = new HashMap<>();
                    System.out.print("Enter number of items: ");
                    int n = sc.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Item name and qty: ");
                        String iname = sc.next();
                        int iq = sc.nextInt();
                        map.put(iname, iq);
                    }
                    ops.placeOrder(cust, map);
                    break;

                case 4:
                    System.out.print("Enter item name and quantity: ");
                    ops.updateStock(sc.next(), sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter item name: ");
                    ops.getItemByName(sc.next());
                    break;

                case 6:
                    System.out.print("Enter price: ");
                    ops.getItemsByPrice(sc.nextDouble());
                    break;

                case 7:
                    System.out.print("Enter order ID: ");
                    ops.getOrderById(sc.nextInt());
                    break;

                case 8:
                    System.out.print("Enter customer name: ");
                    ops.getOrdersByCustomer(sc.next());
                    break;

                case 9:
                    ops.getHighestAndLowestOrder();
                    break;

                case 10:
                    ops.getOrdersLastWeek();
                    break;

                case 11:
                    ops.getOrdersLastMonth();
                    break;

                case 0:
                    System.exit(0);
            }
        }
    }
}