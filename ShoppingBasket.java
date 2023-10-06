import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<Product> products;

    public ShoppingBasket() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }

    public double calculateDiscount(double discountRate) {
        return calculateTotalPrice() * discountRate;
    }

    public double calculateSGST(double sgstRate) {
        return calculateTotalPrice() * sgstRate;
    }

    public double calculateCGST(double cgstRate) {
        return calculateTotalPrice() * cgstRate;
    }

    public double calculateInvoiceTotal(double discountRate, double sgstRate, double cgstRate) {
        double total = calculateTotalPrice();
        double discount = calculateDiscount(discountRate);
        double sgst = calculateSGST(sgstRate);
        double cgst = calculateCGST(cgstRate);

        return total - discount + sgst + cgst;
    }

    public void displayBasketContents() {
        System.out.println("Basket: ");
        Product.displayFormat();
        for (Product product : products) {
            product.display();
        }
    }
}
