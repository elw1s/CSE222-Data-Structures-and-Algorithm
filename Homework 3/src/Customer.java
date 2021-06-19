/**
 *Customer class
 *
 * */

public class Customer extends Account{
    private String name;
    private String surname;
    private String email;
    private String password;
    private String number;
    private String address;
    private int customer_code;
    private HybridList<SingleProduct> previousOrders;

    public Customer(String name, String surname , String email, String password, int customer_code){
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.customer_code = customer_code;
        previousOrders = new HybridList<SingleProduct>();
    }

    public Customer(String name, String surname , String email, String password, int customer_code, String number, String address){
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.customer_code = customer_code;
        this.number = number;
        this.address = address;
        previousOrders = new HybridList<SingleProduct>();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public int getCustomer_code() {
        return customer_code;
    }

    public void welcomeMessage(){
        System.out.println(getName() + ", Welcome! You are our best customer!");
    }

    /**
     * Customer buys a product by using this function.
     * */
    public void buyProduct(SingleProduct product , int piece , String address , String number){
        for(int i = 0; i < branches.size(); i++){
            for(int k = 0; k < branches.get(i).getProducts().size(); k++){
                for(int j = 0; j < branches.get(i).getProducts().get(k).getProductList().size(); j++){
                    SingleProduct curr = branches.get(i).getProducts().get(k).getProductList().get(j);
                    if(curr.getName().equals(product.getName()) && curr.getModel() == product.getModel() && curr.getColor() == product.getColor()){
                        Product temp = branches.get(i).getProducts().get(k);
                        if(temp.getStock() < piece){
                            branches.get(i).getOutOfStock().add(product);
                            System.err.println("You cannot buy more than stock!");
                            return;
                        }
                        else{
                            temp.setStock(temp.getStock() - piece);
                            if(temp.getStock() == 0){
                                branches.get(i).getOutOfStock().add(product);
                            }
                            setAddress(address);
                            setNumber(number);
                            previousOrders.add(product);
                            System.out.println("Thanks for buying!");
                        }
                        return;
                    }
                }
            }
        }
    }

    /**
     * @throws IllegalStateException
     * Customer buys a product by using this function. There should be stored address and telephone number of customer. Otherwise, it will throw exception.
     *
     * */
    public void buyProduct(SingleProduct product , int piece) throws IllegalStateException{
        if(getAddress() == null || getNumber() == null) throw new IllegalStateException();
        buyProduct(product,piece,getAddress(),getNumber());
    }

    public HybridList<SingleProduct> getPreviousOrders(){
        return previousOrders;
    }


}
