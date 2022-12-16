package Implementation;

import Implementation.Homepage.MovieData;

import java.util.ArrayList;

public class UserData {
    Credentials credentials;
    int tokensCount;
    int numFreePremiumMovies;
    public ArrayList<MovieData> purchasedMovies;
    public ArrayList<MovieData> watchedMovies;
    public ArrayList<MovieData> likedMovies;
    public ArrayList<MovieData> ratedMovies;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<MovieData> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<MovieData> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MovieData> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<MovieData> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MovieData> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<MovieData> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MovieData> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<MovieData> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public UserData(Credentials credentials) {
        this.credentials = credentials;
        this.setNumFreePremiumMovies(15);
        this.setTokensCount(0);
        this.purchasedMovies = new ArrayList<MovieData>();
        this.watchedMovies = new ArrayList<MovieData>();
        this.likedMovies = new ArrayList<MovieData>();
        this.ratedMovies = new ArrayList<MovieData>();
    }
    public UserData() {

    }
    public UserData (UserData userData) {
        this.credentials = new Credentials(userData.credentials);
        this.setNumFreePremiumMovies(userData.getNumFreePremiumMovies());
        this.setTokensCount(userData.tokensCount);
        this.purchasedMovies = new ArrayList<MovieData>();
        this.watchedMovies = new ArrayList<MovieData>();
        this.likedMovies = new ArrayList<MovieData>();
        this.ratedMovies = new ArrayList<MovieData>();
    }
}
