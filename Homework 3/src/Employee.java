/**
 *Employee class
 *
 * */

public class Employee extends Account{
    private String name;
    private String surname;
    private String email;
    private String password;
    private int branch;

    public Employee(){
        /* INTENTIONALLY EMPTY */
    }

    public Employee(String name, String surname , String email, String password , int branch){
        if(branch < 0 || branch > branches.size()){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.branch = branch;
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

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public void welcomeMessage(){
        System.out.println(getName() + ", Welcome! Work well!");
    }
    /**
     * @throws IllegalArgumentException
     * Adds new product to the previous order list of given customer by Employee
     * */
    public void addPreviousOrders(int customerNumber , SingleProduct product) throws IllegalArgumentException{
        if(customerNumber < 0 && customerNumber > customers.size() + 1) throw new IllegalArgumentException();

        customers.get(customerNumber - 1).getPreviousOrders().add(product);
    }
    /**
     * @throws IllegalArgumentException
     * Adds new item that is out of stock to the list of products which are out of stock. Use this function to inform ADMIN
     *
     * */
    public void outOfStock(SingleProduct product ,int branch,int requestedAmount) throws IllegalArgumentException{
        if(requestedAmount < 0 ||branch < 0 || branch > branches.size()) throw new IllegalArgumentException();

        branches.get(branch).getOutOfStock().add(product);
    }
    /**
     * Prints the items in out_of_stock list
     *
     * */
    public void queryStock(){
        System.out.println("PRODUCT NAME\tMODEL\tCOLOR");
        for(int i = 0; i < branches.size(); i++){
            for(int k = 0; k < branches.get(i).getOutOfStock().size(); k++){
                SingleProduct current = branches.get(i).getOutOfStock().get(k);
                System.out.println(current.getName() + "\t" + current.getModel() + "\t" + current.getColor());
            }
        }
        System.out.println("------------------------------------------------");
    }
    /**
     * Adds new product to the given branch
     *
     * */
    public void addProduct(Product product , int branch){
        branches.get(getBranch()).getProducts().add(product);
    }
    /**
     * @param product
     * Removes the given product from Employee's branch
     * */
    public void removeProduct(Product product){
        branches.get(getBranch()).getProducts().remove(product);
    }
    /**
     * @throws IllegalArgumentException
     * Employee will be able to make sale using this function
     *
     * */
    public void makeSale(Product product,double percentage) throws IllegalArgumentException{
        if(percentage < 0 || percentage > 100 ||branch < 0 || branch > branches.size()) throw new IllegalArgumentException();

        for(int i = 0; i < branches.get(getBranch()).getProducts().size(); i++){
            if(branches.get(getBranch()).getProducts().get(i).getName() == product.getName()){
                double price = branches.get(getBranch()).getProducts().get(i).getPrice();
                branches.get(getBranch()).getProducts().get(i).setPrice(price - (price * (percentage / 100)));
            }
        }
    }



}
