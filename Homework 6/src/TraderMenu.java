import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/***
 * TraderMenu is a menu for trader
 */

public class TraderMenu extends MainSystem {

    Trader trader;

    /***
     * Constructor
     * @throws FileNotFoundException
     */
    public TraderMenu() throws FileNotFoundException {
        super();
        trader = new Trader();
    }
    /***
     * Constructor
     * @throws FileNotFoundException
     */
    public TraderMenu(String id, String password) throws FileNotFoundException {
        super();
        trader = new Trader(id);
    }

    /**
     * Menu for trader
     * @throws IOException
     */
    public void menu() throws IOException {
        while (true) {
            System.out.println(">1. Add a product");
            System.out.println(">2. Remove a product");
            System.out.println(">3. Edit a product");
            System.out.println(">4. Orders");
            System.out.println(">5. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter a product ID: ");
                    String productID = scanner.nextLine();
                    System.out.println("Enter a product name: ");
                    String productName = scanner.nextLine();
                    String productCategory = "";
                    String category_input = " ";
                    while(true){
                        productCategory += category_input;
                        LinkedList<String> list = printCategories( category_input);
                        for(int i = 0; i < list.size(); i++){
                            System.out.println(list.get(i));
                        }
                        if(list.size() == 0) break;
                        System.out.println("Enter the category: ");
                        category_input = scanner.nextLine();
                        productCategory += " >> ";
                    }
                    productCategory = productCategory.substring(5);
                    System.out.println("Category is " + productCategory);
                    System.out.println("Enter the price: ");
                    String productPrice = scanner.nextLine();
                    System.out.println("Enter the discount: ");
                    String productDiscount = scanner.nextLine();
                    double discountPrice = Double.parseDouble(productPrice) * Double.parseDouble(productDiscount) / 100;
                    System.out.println("Enter the description: ");
                    String productDescription = scanner.nextLine();
                    if (contains(productID)) System.err.println("This product id is not available!");
                    else {
                        Hashtable product = new Hashtable<String, Product>();
                        product.put(productID, new Product(productID, productName, productCategory, productPrice,String.valueOf(discountPrice),productDescription,trader.getId()));
                        add(productID, product , trader.getName());
                    }
                }
                break;
                case 2: {
                    System.out.println("Enter a product ID to remove: ");
                    String productID = scanner.nextLine();
                    remove(productID);
                }

                break;
                case 3:
                    System.out.println("Enter a product ID to edit: ");
                    String productID = scanner.nextLine();
                    edit(productID , trader.getName());
                    break;
                case 4:
                    Queue<Order> temp = orders(trader.getName());
                    trader.setOrders(temp);

                    while (true) {
                        System.out.println("Finish an order (fin index Ex: fin 5)");
                        System.out.println("Cancel an order (can index Ex: can 5)");
                        String x = scanner.nextLine();
                        String[] z = x.split(" ");
                        if (z[0].equals("fin")) {

                            for (int i = 0; temp.size() > 0; i++) {
                                if (i == Integer.parseInt(z[1])) break;
                                temp.remove();
                            }
                            trader.finishOrder(temp.peek(), Integer.parseInt(z[1]));
                            break;
                        } else if (z[0].equals("can")) {
                            for (int i = 0; temp.size() > 0; i++) {
                                if (i == Integer.parseInt(z[1])) break;
                                temp.remove();
                            }
                            trader.cancelOrder(temp.peek(), Integer.parseInt(z[1]));
                            break;
                        } else {
                            System.err.println("WRONG INPUT!");
                        }
                    }
                    break;
                case 5:
                    System.exit(1);
                    break;
                default:
                    System.err.println("Please enter a number between 1-5");
            }
        }
    }




}
