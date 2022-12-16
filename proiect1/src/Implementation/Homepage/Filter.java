package Implementation.Homepage;

import Implementation.Input;
import Implementation.UserData;

import java.util.ArrayList;

public class Filter {
    public void filter (ArrayList<MovieData> currentMoviesList, int i, Input input, ArrayList<MovieData> filteredMovies) {
        String actor = null;
        int var = 1;
        //filteredMovies = new ArrayList<MovieData>();
        if (input.getActions().get(i).getFilters().getContains() != null) {
            if (input.getActions().get(i).getFilters().getContains().getActors() != null) {
                actor = input.getActions().get(i).getFilters().getContains().getActors().get(0);
            } else {
                actor = input.getActions().get(i).getFilters().getContains().getGenre().get(0);
                var = 2;
                //filteredMovies = new ArrayList<MovieData>();
            }
        }
        boolean ok = false;
        if (currentMoviesList.size() > 0) {
            for (int j = 0; j < currentMoviesList.size() && var == 1; j++) {
                ok = false;
                for (int k = 0; k < currentMoviesList.get(j).getActors().size(); k++) {
                    if (currentMoviesList.get(j).getActors().get(k).equals(actor) && actor != null && var == 1) {
                        ok = true;
                    }
                }
                if (ok) {
                    filteredMovies.add(currentMoviesList.get(j));
                }
            }
            for (int j = 0; j < currentMoviesList.size() && var == 2; j++) {
                ok = false;
                for (int k = 0; k < currentMoviesList.get(j).getGenres().size(); k++) {
                    if (currentMoviesList.get(j).getGenres().get(k).equals(actor)) {
                        ok = true;
                    }
                }
                if (ok) {
                    filteredMovies.add(currentMoviesList.get(j));
                }
            }
            if (input.getActions().get(i).getFilters().getContains().getGenre() != null && var == 1) {
                actor = actor = input.getActions().get(i).getFilters().getContains().getGenre().get(0);;
                ArrayList<MovieData> tmp = (ArrayList<MovieData>) filteredMovies.clone();
                filteredMovies.clear();
                for (int j = 0; j < tmp.size(); j++) {
                    ok = false;
                    for (int k = 0; k < tmp.get(j).getGenres().size(); k++) {
                        if (tmp.get(j).getGenres().get(k).equals(actor)) {
                            ok = true;
                        }
                    }
                    if (ok) {
                        filteredMovies.add(currentMoviesList.get(j));
                    }
                }
            }
        }

        //currentMoviesList = filteredMovies;
    }
}
