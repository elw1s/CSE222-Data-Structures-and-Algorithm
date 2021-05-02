/**
 * SingleProduct holds products which have different models and colors
 *
 * */

public class SingleProduct extends Product{
    private int model;
    private int color;

    public SingleProduct(){
        super();
    }

    public SingleProduct(Product product , int model , int color){
        super(product,product.getStock(),product.getPrice());
        this.color = color;
        this.model = model;
    }

    public SingleProduct(SingleProduct product , int stock , double price){
        super(product, stock , price);
    }

    public int getModel() {
        return model;
    }

    public int getColor() {
        return color;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
