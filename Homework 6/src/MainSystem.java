/**
 * MAINSYSTEM class controls the other classes. It is necessary to create an object of it.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MainSystem {

    private String user_type;
    private String user_name;
    private int customerDefaultID = 20000000;
    private int traderDefaultID = 10000000;

    /**
     * CONSTRUCTOR
     */
    public MainSystem() {

    }

    /**
     * MainMenu function is for menu which is shown to the user at the opening
     */
    public void mainMenu() {
        try {
            while (true) {
                System.out.println("1. Login (Use MENU)");
                System.out.println("2. Register (Use MENU)");
                System.out.println("3. Run the drive function and exit");
                System.out.println("4. Exit");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        while (true) {
                            System.out.println("Enter the id: ");
                            String id = scanner.nextLine();
                            System.out.println("Enter the password: ");
                            String password = scanner.nextLine();
                            if (login(id, password)) {
                                System.out.println("Logged in! Welcome " + user_name + " :)");
                                if (user_type.equals("Trader")) {
                                    TraderMenu tradermenu = new TraderMenu(id, password);
                                    tradermenu.menu();
                                } else if (user_type.equals("Customer")) {
                                    CustomerMenu customermenu = new CustomerMenu(id, password);
                                    customermenu.menu();
                                }
                                break;
                            } else System.err.println("ID or PASSWORD are invalid!");
                        }
                        break;
                    case 2:
                        while (true) {
                            System.out.println("Enter the name: ");
                            String name = scanner.nextLine();
                            System.out.println("Enter the password: ");
                            String password = scanner.nextLine();
                            Integer id = customerDefaultID + countCustomer();
                            if (register(name, id.toString(), password)) {
                                System.out.println("Your ID is " + id + " NOTE: Don't forget this ID. You will login by using your ID");
                                break;
                            } else System.err.println("Email or PASSWORD are invalid!");
                        }
                        break;
                    case 3:
                        driver();
                        System.out.println("End of the driver");
                        System.exit(1);
                        break;
                    case 4:
                        System.out.println("Thank you for using our app!");
                        System.exit(1);

                }
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }

    /**
     * Login function
     */
    private boolean login(String id, String password) throws FileNotFoundException {
        File users = new File("users.txt");
        Scanner scanner = new Scanner(users);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> arr = Arrays.asList(line.split(","));
            if (arr.get(0).equals(id) && arr.get(1).equals(password)) {
                if (Integer.parseInt(arr.get(0)) < customerDefaultID) {
                    user_type = "Trader";
                } else user_type = "Customer";
                user_name = arr.get(2);
                return true;
            }
        }
        return false;
    }

    /**
     * Register function
     */
    private boolean register(String name, String id, String password) throws IOException {
        File users = new File("users.txt");
        FileWriter filewriter = new FileWriter(users, true); //append
        filewriter.write(id + "," + password + "," + name + "\n");
        filewriter.close();
        return true;
    }

    /***
     * Checks that if there is a customer with the same ID
     * @param id
     * @param password
     * @return
     * @throws FileNotFoundException
     */
    private boolean isThereACustomer(String id, String password) throws FileNotFoundException {
        File users = new File("users.txt");
        Scanner scanner = new Scanner(users);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> arr = Arrays.asList(line.split(","));
            if (arr.get(0).equals(id) && arr.get(1).equals(password)) {
                return true;
            }
        }
        return false;
    }

    /***
     * Driver function
     */
    private void driver() {
        try {
            if (login("10000000", "alisha")) {
                System.out.println("Logged in! Welcome " + user_name + " :)");
                if (user_type.equals("Trader")) {
                    Trader trader = new Trader("10000000");
                    if (contains("SRTEH2FVUHXXVX9X")) System.err.println("This product id is not available!");
                    else {
                        Hashtable product = new Hashtable<String, Product>();
                        product.put("SRTEH2FVUHXXVX9X", new Product("SRTEH2FVUHXXVX9X", "TEST_PRODUCT", "Clothing >> Women's Clothing", "1999", "489", "Test", trader.getName()));
                        System.out.println("Product add -> " + "SRTEH2FVUHXXVX9X is added");
                        add("SRTEH2FVUHXXVX9X", product, trader.getName());
                        System.out.println("Product remove -> " + "SRTEH2FVUHXXVX9X is removed");
                        remove("SRTEH2FVUHXXVX9X");
                        System.out.println("Product edit -> " + "SRTEH2F6HUZMQ6SJ is edited");
                        edit("SRTEH2F6HUZMQ6SJ", trader.getName(), 1, "EDITLENMIS TEXT");
                        System.out.println("Product edit -> " + "A123A123A123 is not in the products.txt: ");
                        edit("A123A123A123", trader.getName(), 1, "EDITLENMIS TEXT");
                        Queue<Order> temp = orders(trader.getName());
                        trader.setOrders(temp);
                        System.out.println("Finish order -> " + "trader.finishOrder(temp.peek(), 0)");
                        trader.finishOrder(temp.peek(), 0);
                        System.out.println("Cancel order -> " + "trader.cancelOrder(temp.peek(), 3)");
                        trader.cancelOrder(temp.peek(), 3);
                    }
                }
            }if (login("20000000", "arda")) {
                if (user_type.equals("Customer")) {
                    Customer customer = new Customer("20000000", "arda");
                    LinkedList<Product> products = search("Sofa");
                    System.out.println("************SEARCH SOFA************");
                    System.out.println("************SORT IN DECREASING BY THEIR NAME************");
                    products = sortName(products, true);
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************SORT IN INCREASING BY THEIR NAME************");
                    products = sortName(products, false);
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************SORT IN DECREASING BY THEIR PRICE************");
                    products = sortPrice(products, true);
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************SORT IN INCREASING BY THEIR PRICE************");
                    products = sortPrice(products, false);
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************SORT IN DECREASING BY THEIR DISCOUNT************");
                    products = sortDiscount(products, true);
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************SORT IN INCREASING BY THEIR DISCOUNT************");
                    products = sortDiscount(products, false);
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************FILTER PRICES BETWEEN 8000 - 70000************");
                    products = filterPrice(products, 8000, 70000);
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************FILTER CATEGORIES (Living Room)************");
                    products = filterCategories(products, "Living Room");
                    System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s", "", "ID", "Name", "Price", "Discount", "Trader"));
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s", i + 1, products.get(i).getId(), products.get(i).getName(), products.get(i).getDiscount_percentage(), products.get(i).productPercentage(), products.get(i).getTrader()));
                    }
                    System.out.println("************SEE PRODUCTS OF A TRADER (ALISHA)************");
                    seeProductsTrader("Alisha");
                    System.out.println("************SEE PRODUCTS OF A TRADER WHICH IS NOT AVAILABLE************");
                    seeProductsTrader("test");
                    System.out.println("************BUY A PRODUCT (0th VALUE IN PRODUCTS)************");
                    buy(new Order(products.get(0), customer.getId()));
                    System.out.println("************BUY A PRODUCT WHICH IS NOT AVAILABLE************");
                    buy(new Order(products.get(-1), customer.getId()));
                }
            } else System.err.println("ID or PASSWORD are invalid!");
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }

    /***
     * counts the number of customer
     * @return
     * @throws FileNotFoundException
     */
    private int countCustomer() throws FileNotFoundException {
        File users = new File("users.txt");
        if (!users.exists()) throw new FileNotFoundException();
        Scanner scanner = new Scanner(users);
        int count = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> arr = Arrays.asList(line.split(","));
            if (Integer.parseInt(arr.get(0)) >= customerDefaultID) count++;
        }
        return count;
    }

    /***
     * Checks that product is available or not
     * @param element
     * @return true when products contain the given element
     * @throws FileNotFoundException
     */
    protected boolean contains(String element) throws FileNotFoundException {
        File products = new File("products.txt");
        if (!products.exists()) throw new FileNotFoundException();
        Scanner scanner = new Scanner(products);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> arr = Arrays.asList(line.split(";"));
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).equals(element)) return true;
            }
        }
        return false;
    }

    /**
     * Returns the trader name of the given trader ID
     *
     * @param traderID
     * @return Returns the trader name of the given trader ID
     * @throws FileNotFoundException
     */
    protected String getTraderName(String traderID) throws FileNotFoundException {
        File fp = new File("users.txt");
        Scanner scanner = new Scanner(fp);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] data = line.split(",");
            if (data[0].equals(traderID)) return data[2];
        }
        return null;
    }

    /**
     * Updates the products.txt file
     *
     * @param productID
     * @param index
     * @param changedText
     * @throws IOException
     */
    protected void update(String productID, int index, String changedText) throws IOException {
        File products = new File("products.txt");
        File temp = new File("temp.txt");
        Scanner scanner = new Scanner(products);
        FileWriter fileWriter = new FileWriter(temp);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            if (data[0].equals(productID)) {
                data[index] = changedText;
                fileWriter.write(data[0] + ";" + data[1] + ";" + data[2] + ";" + data[3] + ";" + data[4] + ";" + data[5] + ";" + data[6] + "\n");
            } else {
                fileWriter.write(line + "\n");
            }
        }

        scanner.close();
        fileWriter.close();
        products.delete();
        temp.renameTo(products);

    }

    /**
     * Prints the categories starting from the given category
     *
     * @param category
     * @return list of subcategories of the category
     * @throws FileNotFoundException
     */
    protected LinkedList<String> printCategories(String category) throws FileNotFoundException {
        File file = new File("products.txt");
        Scanner scanner = new Scanner(file);
        LinkedList<String> list = new LinkedList<String>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            String[] categories = data[2].replaceAll("[\"\\[\\]]", "").split(">>");
            for (int i = 0; i < categories.length - 1; i++) {
                if (categories[i].replaceAll(" ", "").equals(category.replaceAll(" ", ""))) {
                    if (!contains(list, categories[i + 1])) {
                        list.add(categories[i + 1]);
                    }
                } else if (category.equals(" ")) {
                    if (!contains(list, categories[0]))
                        list.add(categories[0]);
                }
            }
        }
        return list;
    }

    /***
     * Checks that first parameter contains second parameter
     * @param list
     * @param category
     * @return true when category is in list
     */
    protected boolean contains(LinkedList<String> list, String category) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(category)) return true;
        }
        return false;
    }

    /***
     * Returns the orders of the given trader
     * @param name
     * @return orders of the given trader
     * @throws FileNotFoundException
     */
    public Queue<Order> orders(String name) throws FileNotFoundException {
        File file = new File("orders.txt");
        Scanner scanner = new Scanner(file);
        Queue<Order> temp = new LinkedList<Order>();
        System.out.println("\nOrders:\n");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> orders = Arrays.asList(line.split(";"));
            if (orders.get(0).equals(name)) {
                for (int i = 1; i < orders.size(); i++) {
                    System.out.println((i - 1) + ") " + orders.get(i));
                    temp.add(new Order(new Product(orders.get(i))));
                }
            }
        }
        scanner.close();
        return temp;
    }

    /***
     * Removes product from the products.txt
     * @param productID
     * @throws IOException
     */
    protected void remove(String productID) throws IOException {
        File fileOut = new File("products.txt");
        File temp = new File("temp.txt");
        FileWriter fileWriter = new FileWriter(temp);
        Scanner scanner = new Scanner(fileOut);
        boolean removed = false;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> arr = Arrays.asList(line.split(";"));
            if (!productID.equals(arr.get(0))) {
                fileWriter.write(line + "\n");
            }
            else{
                System.out.println("Product is removed!");
                removed = true;
            }
        }
        if(!removed) System.err.println("Invalid Product ID");
        scanner.close();
        fileWriter.close();
        fileOut.delete();
        temp.renameTo(fileOut);
    }

    /***
     * Adds new product to the products.txt
     * @param productID
     * @param item
     * @param traderName
     * @throws IOException
     */
    public void add(String productID, Hashtable<String, Product> item, String traderName) throws IOException {
        File file = new File("products.txt");
        FileWriter filewriter = new FileWriter(file, true);
        String id = item.get(productID).getId();
        String name = item.get(productID).getName();
        String category = item.get(productID).getCategory();
        String price = item.get(productID).getPrice();
        String description = item.get(productID).getDescription();
        //System.out.println(id + ";" + name + ";" + "\"[\"\"" + category +"\"\"]\"" +";" + price + ";" + "" + ";" + description + ";" + trader.getName());
        filewriter.write(id + ";" + name + ";" + "\"[\"\"" + category + "\"\"]\"" + ";" + price + ";" + item.get(productID).productPercentage() + ";" + description + ";" + traderName + "\n");
        filewriter.close();
        System.out.println("Product is added!");
    }

    /***
     * Trader can use this function to edit his/her products
     * @param productID
     * @param traderName
     * @throws IOException
     */
    public void edit(String productID, String traderName) throws IOException {
        File fp = new File("products.txt");
        Scanner scanner = new Scanner(fp);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            if (data[0].equals(productID) && data[6].equals(traderName)) {
                while (true) {
                    System.out.println("Select to edit: \n");
                    System.out.println("1-)Name\n2-)Price\n3-)Discounted Price\n4-)Description");
                    Scanner input = new Scanner(System.in);
                    int choice = input.nextInt();
                    input.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter a new name: ");
                            String name = input.nextLine();
                            scanner.close();
                            update(productID, 1, name);
                            System.out.println("Product is edited");
                            return;
                        case 2:
                            System.out.println("Enter a new price: ");
                            String price = input.nextLine();
                            scanner.close();
                            update(productID, 3, price);
                            System.out.println("Product is edited");
                            return;
                        case 3:
                            System.out.println("Enter a new discounted price: ");
                            String disc_price = input.nextLine();
                            scanner.close();
                            update(productID, 4, disc_price);
                            System.out.println("Product is edited");
                            return;
                        case 4:
                            System.out.println("Enter a new description: ");
                            String description = input.nextLine();
                            scanner.close();
                            update(productID, 5, description);
                            System.out.println("Product is edited");
                            return;
                        default:
                            System.err.println("Invalid input");
                    }
                }

            }

        }
        System.err.println("Product ID is invalid.");

    }

    /***
     * This function does not get any input from user. You can use while writing a driver.
     * @param productID
     * @param traderName
     * @param index
     * @param newValue
     * @throws IOException
     */
    public void edit(String productID, String traderName, int index, String newValue) throws IOException {
        File fp = new File("products.txt");
        Scanner scanner = new Scanner(fp);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            if (data[0].equals(productID) && data[6].equals(traderName)) {
                update(productID, index, newValue);
                System.out.println("Product is edited");
                return;
            }
        }
        System.err.println("Product ID is invalid.");
    }

    /***
     * Customer can filter the prices by using this function
     * @param products
     * @param min
     * @param max
     * @return
     */
    public LinkedList<Product> filterPrice(LinkedList<Product> products, double min, double max) {
        LinkedList<Product> list = new LinkedList<Product>();

        for (int i = 0; i < products.size(); i++) {
            if (Double.parseDouble(products.get(i).getDiscount_percentage()) < max && Double.parseDouble(products.get(i).getDiscount_percentage()) > min) {
                list.add(products.get(i));
            }
        }

        return list;
    }

    /**
     * Customer can filter the categories by using this function
     *
     * @param products
     * @param category
     * @return
     */
    public LinkedList<Product> filterCategories(LinkedList<Product> products, String category) {
        LinkedList<Product> list = new LinkedList<Product>();

        for (int i = 0; i < products.size(); i++) {
            String[] categories = products.get(i).getCategory().split(">>");
            boolean flag = false;
            for (int k = 0; k < categories.length; k++) {
                if (categories[k].replaceAll(" ", "").equals(category.replaceAll(" ", ""))) {
                    flag = true;
                }
            }
            if (flag) {
                list.add(products.get(i));
            }
        }
        return list;
    }

    /***
     * Customer see all products of the trader
     * @param traderName
     * @throws FileNotFoundException
     */
    public void seeProductsTrader(String traderName) throws FileNotFoundException {
        File file = new File("products.txt");
        Scanner scanner = new Scanner(file);
        System.out.println(String.format("%-10s %-10s %-100s%-6s%15s", "", "ID", "Name", "Price", "Trader"));
        int i = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            if (data[6].equals(traderName)) {
                i++;
                System.out.println(String.format("%d) %-10s %-100s %-10s %-30s", i, data[0], data[1], data[4], data[6]));
            }
        }
    }

    /***
     * Customer can sort the products by their name
     * @param products
     * @param isDecreasing
     * @return
     */
    public LinkedList<Product> sortName(LinkedList<Product> products, boolean isDecreasing) {
        if (!isDecreasing) {
            for (int i = products.size() - 1; i > 0; i--) {
                for (int k = 0; k < i; k++) {
                    if (products.get(k).getName().compareTo(products.get(k + 1).getName()) > 0) {
                        Product temp = products.get(k);
                        products.set(k, products.get(k + 1));
                        products.set(k + 1, temp);
                    }
                }
            }
        } else if (isDecreasing) {
            for (int i = products.size() - 1; i > 0; i--) {
                for (int k = 0; k < i; k++) {
                    if (products.get(k).getName().compareTo(products.get(k + 1).getName()) < 0) {
                        Product temp = products.get(k);
                        products.set(k, products.get(k + 1));
                        products.set(k + 1, temp);
                    }
                }
            }
        }
        return products;
    }

    /***
     * Customer can sort the products by their price
     * @param products
     * @param isDecreasing
     * @return
     */
    public LinkedList<Product> sortPrice(LinkedList<Product> products, boolean isDecreasing) {
        if (!isDecreasing) {
            for (int i = 1; i < products.size(); i++) {
                Product current = products.get(i);
                int k = i;
                for (; k > 0 && Double.parseDouble(products.get(k - 1).getDiscount_percentage()) > Double.parseDouble(current.getDiscount_percentage()); k--) {
                    products.set(k, products.get(k - 1));
                }
                products.set(k, current);
            }
        } else if (isDecreasing) {
            for (int i = 1; i < products.size(); i++) {
                Product current = products.get(i);
                int k = i;
                for (; k > 0 && Double.parseDouble(products.get(k - 1).getDiscount_percentage()) < Double.parseDouble(current.getDiscount_percentage()); k--) {
                    products.set(k, products.get(k - 1));
                }
                products.set(k, current);
            }
        }
        return products;
    }

    /***
     * Customer can sort the products by their discount percentage
     * @param products
     * @param isDecreasing
     * @return
     */
    public LinkedList<Product> sortDiscount(LinkedList<Product> products, boolean isDecreasing) {
        if (!isDecreasing) {
            for (int i = products.size() - 1; i > 0; i--) {
                int max = 0;
                for (int k = 1; k <= i; k++) {
                    if (Double.parseDouble(products.get(max).productPercentage()) < Double.parseDouble(products.get(k).productPercentage()))
                        max = k;
                }
                Product temp = products.get(max);
                products.set(max, products.get(i));
                products.set(i, temp);
            }
        } else if (isDecreasing) {
            Heap heap = new Heap();
            for (int i = 0; i < products.size(); i++) {
                heap.insert(products.get(i));
            }
            heap = heap.sort(heap);
            LinkedList<Product> temp = new LinkedList<Product>();
            while (heap.size() > 0) {
                temp.add(heap.getMax());
                heap.removeMax();

            }
            return temp;
        }
        return products;
    }

    /***
     * Customer can buy a product by using this function
     * @param product
     * @throws IOException
     */
    public void buy(Order product) throws IOException {
        File order = new File("orders.txt");
        File temp = new File("temp.txt");
        Scanner scanner = new Scanner(order);
        FileWriter writer = new FileWriter(temp);
        boolean found = false;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] list = line.split(";");
            if (list[0].equals(product.getProduct().getTrader())) {
                writer.write(line + ";" + product.getProduct().getId() + "\n");
                found = true;
                System.out.println("You bought an item!");
            } else
                writer.write(line + "\n");
        }
        if (!found) {
            writer.write(product.getProduct().getTrader() + ";" + product.getProduct().getId() + "\n");
        }

        scanner.close();
        writer.close();
        order.delete();
        temp.renameTo(order);

    }

    /***
     * Gets the product list of the given name. Searches in name and description
     * @param name
     * @return
     * @throws FileNotFoundException
     */
    public LinkedList<Product> search(String name) throws FileNotFoundException {
        File file = new File("products.txt");
        Scanner scanner = new Scanner(file);
        LinkedList<Product> items = new LinkedList<Product>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> list = Arrays.asList(line.split(";"));
            if (list.get(1).equals(name)) {
                items.add(new Product(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6)));
            } else {
                String[] description = list.get(5).split(" ");
                for (int i = 0; i < description.length; i++) {
                    if (description[i].equals(name)) {
                        items.add(new Product(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6)));
                    }
                }
            }
        }
        return items;
    }


}
