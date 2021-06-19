/**
 *
 * Product class are used for Products such as chairs, meeting tables. Model and Color are the number of them. It holds productList the hold different models and colors.
 *
 * */

public class Product {
    private String name;
    private int modelNumber;
    private int colorNumber;
    private int stock;
    private double price;
    private HybridList<SingleProduct> productList;

    public Product(String name, int model , int color , int stock , double price){
        this.name = name;
        this.modelNumber = model;
        this.colorNumber = color;
        this.stock = stock;
        this.price = price;
        productList = new HybridList<SingleProduct>();
    }

    public Product(Product product , int stock , double price){
        this.name = product.name;
        this.modelNumber = product.modelNumber;
        this.colorNumber = product.colorNumber;
        this.price = price;
        this.stock = stock;
        productList = new HybridList<SingleProduct>();
    }

    public Product(){
        productList = new HybridList<SingleProduct>();
    }

    public HybridList<SingleProduct> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public int getColorNumber() {
        return colorNumber;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public int getStock() {
        return stock;
    }

    public void setColorNumber(int colorNumber) {
        this.colorNumber = colorNumber;
    }

    public void setModelNumber(int modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @throws IllegalArgumentException
     * Adds new product to the productList. Throws exception when the given product name is not same as Product object name
     *
     * */
    public void addProduct(SingleProduct product) throws IllegalArgumentException{
        if(!product.getName().equals(getName())){
            throw new IllegalArgumentException();
        }
        productList.add(product);
    }

}

