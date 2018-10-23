package core.Refactoring.case1;

/**
 * 〈一句话功能简述〉<br>
 * 〈租赁〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public double getCharge() {
       return movie.getCharge(this.daysRented);
    }

    public int getFrequentRenterPoints() {
        if (getMovie().getPriceCode() == Movie.NEW_RELEASE
                && getDaysRented() > 1) {
            return 2;
        }else{
            return 1;
        }
    }
}