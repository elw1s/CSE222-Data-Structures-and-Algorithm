/**
 *Company class is the main class of the system.
 *
 * */
public class Company implements CONSTANTS{
    protected static KWLinkedList<Branch> branches;
    protected static KWArrayList<Customer> customers;
    protected static KWArrayList<Employee> employees;
    private Employee loggedEmployee;
    private Customer loggedCustomer;
    private boolean loggedIn = false;

    public void fillFields(){
        branches = new KWLinkedList<Branch>();
        customers = new KWArrayList<Customer>(4);
        employees = new KWArrayList<Employee>(2);

        branches.add(new Branch());
        branches.add(new Branch());
        branches.add(new Branch());
        fillBranches();
        fillCustomers();
        fillEmployees();
        fillProductList();
    }

    private void fillProductList(){
        branches.get(0).getProducts().get(0).getProductList().add(new SingleProduct(new Product("office cabinets",12,1,200,449.99) ,1,1));
        branches.get(0).getProducts().get(0).getProductList().add(new SingleProduct(new Product("office cabinets",12,1,200,449.99) ,10,1));
        branches.get(0).getProducts().get(1).getProductList().add(new SingleProduct(new Product("meeting tables",10,4,100,1099.99),1,4 ));
        branches.get(0).getProducts().get(1).getProductList().add(new SingleProduct(new Product("meeting tables",10,4,100,1099.99), 7, 1));
        branches.get(0).getProducts().get(2).getProductList().add(new SingleProduct(new Product("office desks",5,4,200,89.99) ,2,3));
        branches.get(0).getProducts().get(2).getProductList().add(new SingleProduct(new Product("office desks",5,4,200,89.99) ,3,4));
        branches.get(0).getProducts().get(3).getProductList().add(new SingleProduct(new Product("chair",7,5,200,59.99) ,5,2));
        branches.get(0).getProducts().get(3).getProductList().add(new SingleProduct(new Product("chair",7,5,200,59.99) ,6,5));
        branches.get(1).getProducts().get(0).getProductList().add(new SingleProduct(new Product("meeting tables",10,4,100,1099.99) ,1,3));
        branches.get(1).getProducts().get(0).getProductList().add(new SingleProduct(new Product("meeting tables",10,4,100,1099.99) ,5,1));
        branches.get(1).getProducts().get(1).getProductList().add(new SingleProduct(new Product("office desks",5,4,200,89.99) ,1,3));
        branches.get(1).getProducts().get(1).getProductList().add(new SingleProduct(new Product("office desks",5,4,200,89.99) ,3,3));
        branches.get(1).getProducts().get(2).getProductList().add(new SingleProduct(new Product("bookcases",12,1,200,649.99) ,1,1));
        branches.get(1).getProducts().get(2).getProductList().add(new SingleProduct(new Product("bookcases",12,1,200,649.99) ,6,1));
        branches.get(1).getProducts().get(3).getProductList().add(new SingleProduct(new Product("chair",7,5,200,59.99) ,4,2));
        branches.get(1).getProducts().get(3).getProductList().add(new SingleProduct(new Product("chair",7,5,200,59.99) ,6,1));

    }

    private void fillEmployees(){
        employees.add(new Employee("Mahmut","Yağcı","mahmut@yagci.com","mahmut",1));
        employees.add(new Employee("Selim","Güleryüz","guleryuz@selim.com","gulselimgul",0));
    }

    private void fillCustomers(){
        customers.add(new Customer("Jane","Doe","janedoe@gmail.com","123",customers.size() + 1));
        customers.add(new Customer("Jack","Richard","jackrichard@gmail.com","987654321",customers.size() + 1));
        customers.add(new Customer("Kelly","Guy","kellyguy@gmail.com","123456789",customers.size() + 1));
        customers.add(new Customer("George","Hamilton","georgehamilton@gmail.com","8596",customers.size() + 1));
    }

    private void fillBranches(){
        branches.get(0).addProduct(new Product("office cabinets",12,1,200,449.99));
        branches.get(0).addProduct(new Product("meeting tables",10,4,100,1099.99));
        branches.get(0).addProduct(new Product("office desks",5,4,200,89.99));
        branches.get(0).addProduct(new Product("chair",7,5,200,59.99));

        branches.get(1).addProduct(new Product("meeting tables",10,4,100,1099.99));
        branches.get(1).addProduct(new Product("office desks",5,4,200,89.99));
        branches.get(1).addProduct(new Product("bookcases",12,1,200,649.99));
        branches.get(1).addProduct(new Product("chair",7,5,200,59.99));

        branches.get(2).addProduct(new Product("office desks",5,4,200,89.99));
        branches.get(2).addProduct(new Product("bookcases",12,1,200,649.99));
        branches.get(2).addProduct(new Product("office cabinets",12,1,200,449.99));
        branches.get(2).addProduct(new Product("chair",7,5,200,59.99));
    }

    /**
     *
     * @return true when logged in successfully
     * login function for admin
     */
    public boolean loginAdmin(String email , String password){
        if(ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)){
            System.out.println("Logged IN!");
            loggedIn = true;

            return true;
        }
        System.out.println("Whether email or password is wrong!");
        return false;
    }
    /**
     * @return true when logged in successfully
     * login function for customer
     */
    public boolean loginCustomer(String email , String password){
        for(int i = 0; i < customers.size(); i++){
            if(email.equals(customers.get(i).getEmail()) && password.equals(customers.get(i).getPassword())){
                System.out.println("Logged IN!");
                loggedIn = true;
                loggedCustomer = customers.get(i);
                loggedCustomer.welcomeMessage();
                return true;
            }
        }
        System.out.println("Whether email or password is wrong!");
        return false;
    }
    /**
     * @return true when logged in successfully
     * login function for employee
     */
    public boolean loginEmployee(String email , String password){
        for(int i = 0; i < employees.size(); i++){
            if(email.equals(employees.get(i).getEmail()) && password.equals(employees.get(i).getPassword())){
                System.out.println("Logged IN!");
                loggedIn = true;
                loggedEmployee = employees.get(i);
                loggedEmployee.welcomeMessage();
                return true;
            }
        }
        System.out.println("Whether email or password is wrong!");
        return false;
    }
    /**
     * @return The value of loggedIn field.
     *
     */
    private boolean isLoggedIn(){
        return loggedIn;
    }
    /**
     * @return true when registered in successfully
     * Creates a new customer
     */
    public boolean registerAccount(String email , String password, String name , String surname){
        for(int i = 0; i < customers.size(); i++){
            if(email.equals(customers.get(i).getEmail()) && password.equals(customers.get(i).getPassword())){
                return false;
            }
        }
        customers.add(new Customer(name, surname , email, password , customers.size() + 1));
        System.out.println("Registration is completed! Your customer number is " + customers.size());
        return true;
    }

    /**
     * @return Returns the loggedCustomer object
     * */
    public Customer getLoggedCustomer(){
        return loggedCustomer;
    }
    /**
     * @return Returns the loggedEmployee object
     * */
    public Employee getLoggedEmployee() {
        return loggedEmployee;
    }
}
