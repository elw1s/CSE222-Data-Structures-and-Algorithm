import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/***
 * Trader class for trader
 */

public class Trader extends Account{
    private String DEFAULT_ID = "10000000"; //Default ID for trader ID
    Queue<Order> orders = new LinkedList<Order>(); //Queue of orders

    /***
     * Constructor
     * @throws FileNotFoundException
     */
    public Trader() throws FileNotFoundException {
        setId(DEFAULT_ID);
        setName();
    }
    /***
     * Constructor
     * @throws FileNotFoundException
     */
    public Trader(String id) throws FileNotFoundException {
        setId(id);
        setName();
    }

    /***
     * Setter for orders
     * @param orders
     */
    public void setOrders(Queue<Order> orders) {
        this.orders = orders;
    }

    /***
     * Getter for orders
     * @return
     */
    public Queue<Order> getOrders() {
        return orders;
    }

    /**
     * Trader can cancel the order by using this function
     * @param order
     * @param index
     * @throws IOException
     */
    public void cancelOrder(Order order ,int index) throws IOException {
        Queue<Order> temp = new LinkedList<Order>();
        for(int i = 0; orders.size() > 0; i++){
            if(!orders.peek().getProduct().getId().equals(order.getProduct().getId()) && index != i){
                temp.add(orders.peek());
            }
            orders.remove();
        }
        orders = temp;
        updateOrder();
    }

    /***
     * Trader can finish the order by using this function
     * @param order
     * @param index
     * @throws IOException
     */
    public void finishOrder(Order order,int index) throws IOException {
        Queue<Order> temp = new LinkedList<Order>();
        for(int i = 0; orders.size() > 0; i++){
            if(!orders.peek().getProduct().getId().equals(order.getProduct().getId()) && index != i){
                temp.add(orders.peek());
            }
            orders.remove();
        }
        orders = temp;
        updateOrder();
    }

    /***
     * This function updates the orders.txt file
     * @throws IOException
     */
    public void updateOrder() throws IOException {
        File ordersFile = new File("orders.txt");
        File tempFile = new File("temp.txt");
        Scanner scanner = new Scanner(ordersFile);
        FileWriter fileWriter = new FileWriter(tempFile);
        Queue<Order> temp = orders;

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] separated = line.split(";");
            if(separated[0].equals(getName())){
                fileWriter.write(getName());
                for(int i = 0; temp.size() > 0; i++){
                    fileWriter.write(";"+ temp.peek().getProduct().getId());
                    temp.remove();
                }
                fileWriter.write("\n");
            }
            else{
                fileWriter.write(line + "\n");
            }
        }

        scanner.close();
        fileWriter.close();
        ordersFile.delete();
        tempFile.renameTo(ordersFile);

    }





}
