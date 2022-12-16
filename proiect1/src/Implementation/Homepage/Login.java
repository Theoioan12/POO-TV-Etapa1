package Implementation.Homepage;

import Implementation.Credentials;
import Implementation.Users;

import java.util.ArrayList;

public class Login extends Page {

    public int Login (ArrayList<Users> users, Credentials credentials) {
        int ok = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getCredentials().getName().equals(credentials.getName()) &&
                    users.get(i).getCredentials().getPassword().equals(credentials.getPassword())) {
                ok = i;
            }
        }

        return ok;
    }

    public Login() {

    }

    public Login(Page parent, Page children, String pageName, String pageDescription) {
        super(parent, children, pageName, pageDescription);
    }

}
