/**
 *Admin class extends Employee. There are some special methods for admin such as adding and removing Employee.
 *
 * */

public class Admin extends Employee{

    public Admin(){
        super();
    }

    public Admin(String name, String surname , String email, String password){
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPassword(password);
    }

    /**
     * @throws ArrayIndexOutOfBoundsException
     * Removes the given branch
     * */
    public void removeBranch(int index) throws ArrayIndexOutOfBoundsException{
        if(index < 0 || index > branches.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        branches.remove(index);
    }
    /**
     * Adds new branch
     * */
    public void addBranch(){
        branches.add(new Branch());
    }
    /**
     * Adds new Employee by given object
     *
     * */
    public void addEmployee(Employee employee){
            branches.get(employee.getBranch()).addEmployee(employee);
            employees.add(employee);
    }
    /**
     * @param employee
     * Removes given employee from branches
     * */
    public void removeEmployee(Employee employee){
        branches.get(employee.getBranch()).removeEmployee(employee);
        employees.remove(employee);
    }
    /**
     * @return True
     * Updates stock of given product in given branch by using stock and price variables
     * */
    public boolean updateStock(int branch , SingleProduct product , int stock , double price){
        SingleProduct newProduct = new SingleProduct(product,stock,price);
        branches.get(branch).addSingleProduct(newProduct);
        branches.get(branch).updateStock(newProduct);
        return true;
    }



}
