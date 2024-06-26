package Implementation;

public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Credentials () {

    }

    public Credentials (Credentials credentials) {
        this.setName(credentials.getName());
        this.setAccountType(credentials.accountType);
        this.setCountry(credentials.getCountry());
        this.setPassword(credentials.getPassword());
        this.setBalance(new String(credentials.getBalance()));
    }
}
