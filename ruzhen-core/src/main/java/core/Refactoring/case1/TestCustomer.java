package core.Refactoring.case1;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试类〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class TestCustomer {
    public static void main(String[] args) {
        Customer customer = new Customer("Jack");
        Movie movie1 = new Movie("指环王1", Movie.REGULAR);
        Movie movie2 = new Movie("指环王2", Movie.NEW_RELEASE);
        Rental r1 = new Rental(movie1, 3);
        Rental r2 = new Rental(movie2, 3);

        customer.addRental(r1);
        customer.addRental(r2);
        System.out.println(customer.statement());
    }
}