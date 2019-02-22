package core.effectiveJava.refactoring;

import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br>
 * 〈顾客〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class Customer {
    private String name;
    private ArrayList<Rental> rentals = new ArrayList();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental :
                rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.getCharge()) + "\n";
        }
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        for (Rental rental :
                rentals) {
            result += rental.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        for (Rental rental :
                rentals) {
            result+= rental.getFrequentRenterPoints();
        }
        return result;
    }

}