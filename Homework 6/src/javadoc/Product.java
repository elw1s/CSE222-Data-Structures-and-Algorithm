/***
 * PRODUCT CLASS for products
 */

public class Product {
    String id;
    String name;
    String category;
    String price;
    String discount_percentage; //Holds discounted price
    String trader;
    String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount_percentage() {
        return discount_percentage;
    }

    public String getTrader() {
        return trader;
    }

    public Product(){

    }

    /***
     * Constructor
     * @param id
     * @param name
     * @param category
     * @param price
     * @param discount_percentage
     * @param description
     * @param trader
     */
    public Product(String id, String name, String category, String price,String discount_percentage, String description,String trader){
        this.id = id;
        this.name = name;
        this.category = category.replaceAll("[\"\\[\\]]","");
        this.discount_percentage = discount_percentage;
        this.trader = trader;
        this.price = price;
        this.description = description;
    }

    /***
     * Constructor
     * @param id
     */
    public Product(String id){
        this.id = id;
    }

    /***
     *
     * @return percentage of the discount
     */
    public String productPercentage(){
        double price = Double.parseDouble(this.price);
        double discounted_price = Double.parseDouble(this.discount_percentage);
        double discount_percentage = ((price - discounted_price) / price) * 100;
        return String.format("%.0f",discount_percentage);
    }
}
