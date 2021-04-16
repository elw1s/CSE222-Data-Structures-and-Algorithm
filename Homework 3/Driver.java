public class Driver {

    public static void main(String[] args) {
        try {
            Product chair = new Product("chair", 7, 5, 200, 59.99);
            Company company = new Company();
            Admin admin = new Admin();
            Customer customer;
            Employee employee;
            /*Fill the fields such as branches, products etc. to manipulate the data*/
            company.fillFields();
            /*Register function*/
            company.registerAccount("ardakilicbm@gmail.com", "arda111", "Arda", "Kılıç");
            /*Check the loginCustomer function with invalid parameters*/
            System.out.println("LoginCustomer with invalid parameters => " + company.loginCustomer("test", "test"));
            /*Login with Customer account with valid parameters*/
            System.out.println("\n\t\t|||||||||||CUSTOMER ACCOUNT|||||||||||\n");
            if (company.loginCustomer("janedoe@gmail.com", "123")) {
                customer = company.getLoggedCustomer();
                /*Print the information about customer*/
                System.out.println("\n~~~~~~~~ACCOUNT INFORMATION~~~~~~~~\n");
                System.out.println("Name = " + customer.getName());
                System.out.println("Surname = " + customer.getSurname());
                System.out.println("Email = " + customer.getEmail());
                System.out.println("Password = " + customer.getPassword());
                System.out.println("Customer Number = " + customer.getCustomer_code());
                /*Buy Product using customer object*/
                System.out.println("\n~~~~~~~~BUY PRODUCT~~~~~~~~\n");
                customer.buyProduct(new SingleProduct(chair, 4, 2), 3, "My Address", "My Number");
                /*Check what function will do when ordered amount is less than stock*/
                System.out.println("\n~~~~~~~~BUY PRODUCT MORE THAN AVAILABLE STOCK~~~~~~~~\n");
                customer.buyProduct(new SingleProduct(chair, 4, 2), 2000, "My Address", "My Number");
                /*Search product using customer account*/
                System.out.println("\n~~~~~~~~SEARCH PRODUCT (MEETING TABLE)~~~~~~~~\n");
                customer.searchProduct("meeting tables");
                /*View the previous orders of customer*/
                System.out.println("\n~~~~~~~~SEE PREVIOUS ORDERS~~~~~~~~\n");
                customer.seePreviousOrders(customer.getCustomer_code());
                /*View the list of products*/
                System.out.println("\n~~~~~~~~SEE THE LIST OF PRODUCTS~~~~~~~~\n");
                customer.seeProducts();
            }
            /*Login to the system using employee account*/
            System.out.println("\n\t\t|||||||||||EMPLOYEE ACCOUNT|||||||||||\n");
            if (company.loginEmployee("mahmut@yagci.com", "mahmut")) {
                employee = company.getLoggedEmployee();
                /*Print the information about employee*/
                System.out.println("\n~~~~~~~~ACCOUNT INFORMATION~~~~~~~~\n");
                System.out.println("Name = " + employee.getName());
                System.out.println("Surname = " + employee.getSurname());
                System.out.println("Email = " + employee.getEmail());
                System.out.println("Password = " + employee.getPassword());
                System.out.println("Branch = " + employee.getBranch());
                /*Get the information of customer with specified customer number*/
                System.out.println("\n~~~~~~~~SEE PREVIOUS ORDERS OF CUSTOMER 2~~~~~~~~\n");
                employee.seePreviousOrders(2);
                /*Adds new product*/
                System.out.println("\n~~~~~~~~ADD PRODUCT~~~~~~~~\n");
                employee.addProduct(new Product("office set", 1, 5, 20, 1299.99), 1);
                /*Search Product*/
                System.out.println("\n~~~~~~~~SEARCH PRODUCT~~~~~~~~\n");
                employee.searchProduct("office set");
                /*Removes product*/
                System.out.println("\n~~~~~~~~REMOVE PRODUCT~~~~~~~~\n");
                employee.removeProduct(new Product("office set", 1, 5, 20, 1299.99));
                /*Makes sale*/
                System.out.println("\n~~~~~~~~MAKES SALE~~~~~~~~\n");
                employee.makeSale(chair, 25);
                /*Adds product to the out of stock array*/
                System.out.println("\n~~~~~~~~ADD A PRODUCT TO OUT OF STOCK TO INFORM MANAGER~~~~~~~~\n");
                employee.outOfStock(new SingleProduct(chair, 1, 1), 0, 5);
                /*Adds product to the previous order of a customer*/
                System.out.println("\n~~~~~~~~ADD A PRODUCT TO THE PREVIOUS ORDER OF THE CUSTOMER~~~~~~~~\n");
                employee.addPreviousOrders(2, new SingleProduct(chair, 6, 5));
                /*Creates new customer*/
                System.out.println("\n~~~~~~~~CREATE NEW CUSTOMER~~~~~~~~\n");
                employee.registerAccount("ahmetcaglar@gmail.com", "ahmetcaglar", "Ahmet", "Çağlar");
            }
            /*Login to the system using admin account*/
            System.out.println("\n\t\t|||||||||||ADMIN ACCOUNT|||||||||||\n");
            if (company.loginAdmin("admin", "admin")) {
                /*Queries the stock*/
                System.out.println("\n~~~~~~~~QUERY STOCK~~~~~~~~\n");
                admin.queryStock();
                /*Updates stock by supplying product*/
                System.out.println("\n~~~~~~~~UPDATE STOCK~~~~~~~~\n");
                admin.updateStock(1, new SingleProduct(chair, 5, 3), 5, 39.99);
                /*Removes a branch*/
                System.out.println("\n~~~~~~~~REMOVE BRANCH~~~~~~~~\n");
                admin.removeBranch(2);
                /*Adds a branch*/
                System.out.println("\n~~~~~~~~ADD BRANCH~~~~~~~~\n");
                admin.addBranch();
                /*Adds an employee*/
                System.out.println("\n~~~~~~~~ADD EMPLOYEE~~~~~~~~\n");
                admin.addEmployee(new Employee("Mustafa", "Bağlarbaşı", "mustafa", "baglar", 1));
                /*Removes an employee*/
                System.out.println("\n~~~~~~~~REMOVE EMPLOYEE~~~~~~~~\n");
                admin.removeEmployee(new Employee("Mustafa", "Bağlarbaşı", "mustafa", "baglar", 1));
                /*Removes an employee*/
                System.out.println("\n~~~~~~~~REMOVE EMPLOYEE~~~~~~~~\n");
                admin.removeEmployee(new Employee("Mustafa", "Bağlarbaşı", "mustafa", "baglar", 1));
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}

