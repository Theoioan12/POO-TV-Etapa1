package Implementation;

public class Users {
    Credentials credentials;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Users(Credentials credentials) {
        this.credentials = credentials;
    }

    public Users() {

    }
}
