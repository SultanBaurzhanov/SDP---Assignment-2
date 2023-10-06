import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;    
import java.util.Date;    
import java.util.Calendar;  


public class ShoppingBill   
{  
    public static void main(String args[])   
        {  
            // variables start with invoice
            System.out.println("\t\t\t\t--------------------Invoice-----------------");  
            System.out.println("\t\t\t\t\t "+"  "+"Metro Mart Grocery Shop");  
            System.out.println("\t\t\t\t\t3/98 Mecrobertganj New Mumbai");  
            System.out.println("\t\t\t\t\t"  +"    " +"Opposite Metro Walk");  
            System.out.println("GSTIN: 03AWBPP8756K592"+"\t\t\t\t\t\t\tContact: (+91) 9988776655");  
            //format of date and time  
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
            Date date = new Date();    
            Calendar calendar = Calendar.getInstance();  
            String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };  
            //prints current date and time  
            System.out.println("Date: "+formatter.format(date)+"  "+days[calendar.get(Calendar.DAY_OF_WEEK) - 1]+"\t\t\t\t\t\t (+91) 9998887770");  
            
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Customer Name: ");
            String customerName = scan.nextLine();

            // Create a shopping basket
            ShoppingBasket basket = new ShoppingBasket();

            char choice = '\0';

            do {
                //read product details
                System.out.println("Enter the product details: ");
                System.out.print("Product ID: ");
                String id = scan.nextLine();
                System.out.print("Product Name: ");
                String productName = scan.nextLine();
                System.out.print("Quantity: ");
                int quantity = scan.nextInt();
                System.out.print("Price (per unit): ");
                double price = scan.nextDouble();

                //math total price for 1 product
                double totalPrice = price * quantity;

                //create product object > add to basket
                basket.addProduct(new Product(id, productName, quantity, price, totalPrice));

                //ask for continue shopping
                System.out.print("Want to add more items? (y or n): ");
                choice = scan.next().charAt(0);
                scan.nextLine(); // Read remaining characters

            } while (choice == 'y' || choice == 'Y');

            //display the shopping basket
            basket.displayBasketContents();

            //math and display total price, discounts, sgst, cgst
            double totalPrice = basket.calculateTotalPrice();
            double discountRate = 0.00; 
            double sgstRate = 0.0; 
            double cgstRate = 0.0; 
            double discount = basket.calculateDiscount(discountRate);
            double sgst = basket.calculateSGST(sgstRate);
            double cgst = basket.calculateCGST(cgstRate);
            double invoiceTotal = basket.calculateInvoiceTotal(discountRate, sgstRate, cgstRate);

            //save the transaction to a txt
            saveTransactionToTextFile(customerName, totalPrice, discount, sgst, cgst, invoiceTotal);

            //Close Scanner
            scan.close();
        }

    private static void saveTransactionToTextFile(String customerName, double totalPrice, double discount, double sgst, double cgst, double invoiceTotal) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String transactionDate = formatter.format(date);

            FileWriter fileWriter = new FileWriter("transactions.txt", true); //append new thingys
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("transaction Date: " + transactionDate);
            printWriter.println("Customer Name: " + customerName);
            printWriter.println("Total Amount: " + totalPrice);
            printWriter.println("Discount: " + discount);
            printWriter.println("SGST: " + sgst);
            printWriter.println("CGST: " + cgst);
            printWriter.println("Invoice Total: " + invoiceTotal);
            printWriter.println("---");

            printWriter.close();
            System.out.println("Transaction saved successfully.");
        } catch (IOException e) {
            System.err.println("Error: couldnt save transaction to txt");
        }
    }
}