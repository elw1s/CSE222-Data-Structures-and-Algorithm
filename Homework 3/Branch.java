/**
 *Branch class holds products, employees and products which are out of stock.
 *
 * */

public class Branch {
    private HybridList<Product> products; //TODO: SingleProduct Yap (Customer alırken bunu degil içindeki single productu kontrol et
    private KWArrayList<Employee> employees;
    private HybridList<SingleProduct> outOfStock;

    public Branch(){
        products = new HybridList<Product>();
        employees = new KWArrayList<Employee>();
        outOfStock = new HybridList<SingleProduct>();
    }

    public HybridList<SingleProduct> getOutOfStock() {
        return outOfStock;
    }

    public HybridList<Product> getProducts() {
        return products;
    }

    public KWArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     *
     * Adds new Product to products List
     * */
    public void addProduct(Product item){
        products.add(item);
    }
    /**
     *
     * Removes the given product from list
     * */
    public void removeProduct(Product product){
        products.remove(product);
    }
    /**
     *
     * Adds new Employee to the list using the given object
     * */
    public void addEmployee(Employee emp){
        employees.add(emp);
    }
    /**
     *
     * Removes the given Employee from employees list
     * */
    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }

    /**
     *
     * Adds a single product object into the Branch. If there is a same object in Branch then sets it with parameter.
     */
    public void addSingleProduct(SingleProduct product){
        int productIndex = 0;
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getName().equals(product.getName())){
                productIndex = i;
            }
        }
        for(int k = 0; k < products.get(productIndex).getProductList().size(); k++){
            if(products.get(productIndex).getProductList().get(k).getModel() == product.getModel() && products.get(productIndex).getProductList().get(k).getColor() == product.getColor()){
                products.get(productIndex).getProductList().set(k,product);
                return;
            }
        }
        products.get(productIndex).addProduct(product);
    }
    /**
     *
     * Updates stock by removing the given product
     * */
    public void updateStock(SingleProduct product){
        outOfStock.remove(product);
    }
    /**
     *
     * Searches for the given parameter in products list.
     * @return True when given parameter is available in list
     * */
    public boolean isAvailable(Product product){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).equals(product)) return true;
        }
        return false;
    }


}
