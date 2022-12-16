package Implementation.Homepage;

import Implementation.UserData;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Upgrades {
    public boolean buyPremiumAccount (UserData currUser, ArrayNode output) {
        if (currUser.getTokensCount() >= 10) {
            currUser.getCredentials().setAccountType("premium");
            currUser.setTokensCount(currUser.getTokensCount() - 10);
            return true;
        } else
            return false;
    }

    public void buyTokens (UserData currUser, String count) {
        int balance = Integer.valueOf(currUser.getCredentials().getBalance());
        int coutInt = Integer.valueOf(count);
        if (balance >= coutInt)
            balance -= coutInt;
        currUser.setTokensCount(currUser.getTokensCount() + coutInt);
        currUser.getCredentials().setBalance(String.valueOf(balance));
    }
}
