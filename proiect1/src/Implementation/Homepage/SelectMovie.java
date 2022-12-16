package Implementation.Homepage;

import Implementation.Input;
import Implementation.UserData;

import java.util.ArrayList;

public class SelectMovie {

    public void SelectMovies (ArrayList <MovieData> currentMoviesList, Input inputData, UserData currUser) {
        for (int j = 0; j < inputData.getMovies().size(); j++) {
            boolean ok = false;
            MovieData tmp = new MovieData(inputData.getMovies().get(j));
            for (int k = 0; k < tmp.getCountriesBanned().size(); k++) {
                if (currUser != null)
                    if (tmp.getCountriesBanned().get(k).equals(currUser.getCredentials().getCountry())) {
                        ok = true;
                        break;
                    }
                    else {

                    }

            }
            if (ok == false) {
                currentMoviesList.add(tmp);
            }
        }
    }
}
