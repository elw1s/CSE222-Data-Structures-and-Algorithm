import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/***
 * This class holds data of the ORDER
 */

public class Order{
    String customerID;
    Product product;

    public Order(Product product,String customerID){
        this.product = product;
        this.customerID = customerID;
    }

    public Order(Product product){
        this.product = product;
    }

    public Order(String productID) throws FileNotFoundException {
        this.product = getProduct(productID);
    }

    /***
     * Returns the product of the given product ID
     * @param productID
     * @return
     * @throws FileNotFoundException
     */
    private Product getProduct(String productID) throws FileNotFoundException {
        File file = new File("products.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            List<String> list = Arrays.asList(line.split(";"));
            if(list.get(0).equals(productID)){
                return new Product(list.get(0), list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6));
            }
        }
        return null;
    }

    public Product getProduct() {
        return product;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
