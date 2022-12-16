package Implementation.Homepage;

import Implementation.Movies;

public class MovieData extends Movies {
    int numLikes;
    double rating;
    int numRatings;

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }
    public MovieData() {

    }
    public MovieData(Movies movie) {
        this.setName(movie.getName());
        this.setYear(movie.getYear());
        this.setDuration(movie.getDuration());
        this.setGenres(movie.getGenres());
        this.setActors(movie.getActors());
        this.setCountriesBanned(movie.getCountriesBanned());
        this.setNumLikes(0);
        this.setRating(0.00);
        this.setNumRatings(0);
    }
}
