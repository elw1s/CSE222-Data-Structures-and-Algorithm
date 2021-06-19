import java.io.IOException;
import java.util.*;

/***
 * Menu for customer
 */

public class CustomerMenu extends MainSystem{

    Customer customer;

    public CustomerMenu(Customer customer){
        this.customer = customer;
    }

    public CustomerMenu(String id , String password){
        this.customer = new Customer(id,password);
    }

    /***
     * Customer menu
     * @throws IOException
     */
    public void menu() throws IOException {
        while (true) {
            System.out.println(">1. Search Product");
            System.out.println(">2. See Product of a Seller");
            System.out.println(">3. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the product: ");
                    String product_name = scanner.nextLine();
                    LinkedList<Product> products = search(product_name);
                    products = sortName(products,true);
                    System.out.println();
                    while(true) {
                        System.out.println(String.format("%-10s %-10s %-100s%-6s%10s%15s","","ID","Name","Price","Discount","Trader"));
                        for (int i = 0; i < products.size(); i++) {
                            System.out.println(String.format("%d) %-10s %-100s %-10s %%%-10s %-30s",i+1,products.get(i).getId(),products.get(i).getName(),products.get(i).getDiscount_percentage(),products.get(i).productPercentage(),products.get(i).getTrader()));
                        }
                        System.out.println("\nSort products in decreasing order by their names (Enter: snd)");
                        System.out.println("Sort products in increasing order by their names (Enter: sni)");
                        System.out.println("Sort products in decreasing order by their prices (Enter: spd)");
                        System.out.println("Sort products in increasing order by their prices (Enter: spi)");
                        System.out.println("Sort products in decreasing order by their discount (Enter: sdd)");
                        System.out.println("Sort products in increasing order by their discount (Enter: sdi)");
                        System.out.println("Filter products by their prices (Enter: filp min max)");
                        System.out.println("Filter products by their categories (Enter: filc category)");
                        System.out.println("Buy a product (Enter: buy product_number  [Ex: buy 3])");
                        System.out.print("Enter: ");
                        String order = scanner.nextLine();
                        System.out.println();
                        String[] input = order.split(" ");
                        if(input[0].equals("buy")){
                            buy(new Order(products.get(Integer.parseInt(input[1])), customer.getId()));
                            break;
                        }
                        else if(input[0].equals("snd")){
                            products = sortName(products , true);
                        }
                        else if(input[0].equals("sni")){
                            products = sortName(products , false);
                        }
                        else if(input[0].equals("spd")){
                            products = sortPrice(products , true);
                        }
                        else if(input[0].equals("spi")){
                            products = sortPrice(products , false);
                        }
                        else if(input[0].equals("sdd")){
                            products = sortDiscount(products , true);
                        }
                        else if(input[0].equals("sdi")){
                            products = sortDiscount(products , false);
                        }
                        else if(input[0].equals("filp")){
                            products = filterPrice(products, Double.parseDouble(input[1]) , Double.parseDouble(input[2]));
                        }
                        else if(input[0].equals("filc")){
                            String category = "";
                            for(int i = 1; i < input.length; i++){
                                category += input[i];
                            }
                            products = filterCategories(products, category);
                        }
                        else{
                            System.err.println("Invalid Input!");
                        }
                    }
                break;
                case 2:
                    System.out.println("Enter a trader name: ");
                    String traderName = scanner.nextLine();
                    seeProductsTrader(traderName);
                break;
                case 3:
                    System.exit(1);
                    break;
                default:
                    System.err.println("Please enter a number between 1-5");
            }
        }
    }



}
