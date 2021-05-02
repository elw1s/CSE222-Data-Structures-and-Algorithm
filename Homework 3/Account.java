/**
 *Abstract class to use common methods among Customer,Employee and Admin
 *
 * */

public abstract class Account extends Company{

    private String name;
    private String surname;
    private String email;
    private String password;


    /**
     * Searchs the given product in branches and prints it
    * */
    public void searchProduct(String name){
        System.out.println("Product Name\tNo of Models\t No of Colors\tStock\tPrice\tAvailable Stores\n");
        for(int i = 0; i < branches.size(); i++){
            for(int k = 0; k < branches.get(i).getProducts().size(); k++){
                if(branches.get(i).getProducts().get(k).getName().equals(name)){
                    Product temp = branches.get(i).getProducts().get(k);
                    System.out.format("%12s%12s%12s%12s%12s%12s\n",temp.getName(),temp.getModelNumber(),temp.getColorNumber(),temp.getStock(),temp.getPrice(),i);
                }
            }
        }
    }
    /**
     * An abstract method to print welcoming message
     *
     * */
    public abstract void welcomeMessage();
    /**
     * @throws IllegalArgumentException
     * Prints previous orders of given customer
     * */
    public void seePreviousOrders(int customerNumber) throws IllegalArgumentException{
        if(customerNumber < 0 && customerNumber > customers.size()) throw new IllegalArgumentException();
        System.out.println("Product Name\tModel\tColor\tPrice");
        HybridList<SingleProduct> arr = customers.get(customerNumber - 1).getPreviousOrders();
        for(int i = 0; i < arr.size(); i++){
            //System.out.format("%12s%6s%6s%6s\n",arr.get(i).getName(),arr.get(i).getModel(),arr.get(i).getColor(),arr.get(i).getPrice());
            System.out.println(arr.get(i).getName() + "\t\t\t" + arr.get(i).getModel() + "\t\t" + arr.get(i).getColor() +"\t\t"+arr.get(i).getPrice());
        }
    }
    /**
     * Prints list of all products
     * */
    public void seeProducts(){
        System.out.println("Product Name\tNo of Models\t No of Colors\tStock\tPrice\tAvailable Stores\n");
        for(int i = 0; i < branches.size(); i++){
            for(int k = 0; k < branches.get(i).getProducts().size(); k++){
                Product temp = branches.get(i).getProducts().get(k);
                System.out.format("%12s%12s%12s%12s%12s%12s\n",temp.getName(),temp.getModelNumber(),temp.getColorNumber(),temp.getStock(),temp.getPrice(),i);
            }
        }
    }

}
