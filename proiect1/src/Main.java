import Implementation.Homepage.*;
import Implementation.Input;
import Implementation.Movies;
import Implementation.Output.Output;
import Implementation.UserData;
import Implementation.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        File in = new File(args[0]);
        File out = new File(args[1]);
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(in,
                Input.class);
        ArrayNode output = objectMapper.createArrayNode();


        // start of the implementation
        // current page field
        String currPage = "Homepage Neautentificat";

        // current user field
        UserData currUser = null;

        // movie database
        ArrayList<MovieData> movies = new ArrayList<>();
        ArrayList<MovieData> currentMoviesList = new ArrayList<>();
        ArrayList<MovieData> watchedMovie = new ArrayList<>();
        Output outputgenerator = new Output();
        Filter filter = new Filter();
        ArrayList<MovieData> filteredMovies = new ArrayList<MovieData>();
        int test10 = 0;

        int commandCount = inputData.getActions().size();
        for (int i = 0; i < commandCount; i++) {

            // change page
            if (inputData.getActions().get(i).getType().equals("change page")) {

                // homepage neautentificat - comenzi
                if (currPage.equals("Homepage Neautentificat")) {
                    // login page
                    if (inputData.getActions().get(i).getPage().equals("login")) {
                        currPage = "login";

                        // register page
                    } else if (inputData.getActions().get(i).getPage().equals("register")) {
                        currPage = "register";

                        // error
                    } else {
                        outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                    }

                    // homepage autentificat - comenzi
                } else if (currPage.equals("Homepage Autentificat")) {
                    if (inputData.getActions().get(i).getPage().equals("logout")) {
                        currPage = "Homepage Neautentificat";
                        currUser = null;
                        currentMoviesList = new ArrayList<>();

                    } else if (inputData.getActions().get(i).getPage().equals("movies")) {
                        currPage = "movies";
                        outputgenerator.outputgenerator(output, currentMoviesList, currUser, objectMapper);

                    } else if (inputData.getActions().get(i).getPage().equals("upgrades")) {
                        currPage = "upgrades";

                    } else {
                        outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                    }

                    // movies page - commands
                } else if (currPage.equals("movies")) {
                    if (inputData.getActions().get(i).getPage().equals("see details")) {
                        if (currentMoviesList.size() == 0) {
                            outputgenerator.outputErrorGenerator(output, movies, objectMapper);

                        } else if (!filteredMovies.isEmpty()) {
                            boolean ok = false;
                            for (int j = 0; j < filteredMovies.size(); j++) {
                                ArrayList<MovieData> tmpMovie = new ArrayList<>();
                                tmpMovie.add(filteredMovies.get(j));
                                if (inputData.getActions().get(i).getMovie().equals(filteredMovies.get(j).getName()))
                                {
                                    ok = true;
                                    outputgenerator.outputgenerator(output, tmpMovie, currUser, objectMapper);
                                    watchedMovie.add(currentMoviesList.get(j));
                                    currPage = "see details";
                                }
                            }
                            if (!ok) {
                                outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                            }

                        } else if (test10 == -1) {
                            outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                            test10 = 0;
                        }else {
                            for (int j = 0; j < currentMoviesList.size(); j++) {
                                ArrayList<MovieData> tmpMovie = new ArrayList<>();
                                tmpMovie.add(currentMoviesList.get(j));
                                if (inputData.getActions().get(i).getMovie().equals(currentMoviesList.get(j).getName()))
                                {
                                    outputgenerator.outputgenerator(output, tmpMovie, currUser, objectMapper);
                                    watchedMovie.add(currentMoviesList.get(j));
                                    currPage = "see details";
                                }
                            }

                        }
                    } else if (inputData.getActions().get(i).getPage().equals("movies")) {
                        outputgenerator.outputgenerator(output, currentMoviesList, currUser, objectMapper);
                    }
                } else if (currPage.equals("upgrades")) {
                    if (inputData.getActions().get(i).getPage().equals("movies")) {
                        currPage = "movies";
                        outputgenerator.outputgenerator(output, currentMoviesList, currUser, objectMapper);
                    }
                } else if (currPage.equals("see details")) {
                    if (inputData.getActions().get(i).getPage().equals("logout")) {
                        currPage = "logout";
                    } else if (inputData.getActions().get(i).getPage().equals("movies")) {
                        outputgenerator.outputgenerator(output, currentMoviesList, currUser, objectMapper);
                    }
                }
            }

            // on page
            if (inputData.getActions().get(i).getType().equals("on page")) {

                // login action
                if (currPage.equals("login") && inputData.getActions().get(i).getFeature().equals("login")) {
                    Login login = new Login();

                    if (login.Login(inputData.getUsers(), inputData.getActions().get(i).getCredentials()) != -1) {
                        currPage = "Homepage Autentificat";
                        int currUserIndex = login.Login(inputData.getUsers(), inputData.getActions().get(i).getCredentials());
                        currUser = new UserData (inputData.getUsers().get(currUserIndex).getCredentials());
                        UserData tmp1 = new UserData(currUser);
                        tmp1.purchasedMovies = (ArrayList<MovieData>) currUser.getPurchasedMovies().clone();
                        tmp1.watchedMovies = (ArrayList<MovieData>) currUser.getWatchedMovies().clone();

                        outputgenerator.outputgenerator(output, movies, tmp1, objectMapper);

                        SelectMovie selectMovie = new SelectMovie();
                        selectMovie.SelectMovies(currentMoviesList, inputData, currUser);

                    } else {
                        outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                        currPage = "Homepage Neautentificat";
                    }
                }

                // register action
                else if (currPage.equals("register")
                        && inputData.getActions().get(i).getFeature().equals("register")) {
                    Users tmpUser = new Users(inputData.getActions().get(i).getCredentials());
                    Register register = new Register();
                    register.register(inputData.getUsers(), tmpUser);
                    currPage = "Homepage Autentificat";
                    currUser =
                            new UserData(inputData.getUsers().get(inputData.getUsers().size() - 1).getCredentials());
                    outputgenerator.outputgenerator(output, movies, currUser, objectMapper);
                    SelectMovie selectMovie = new SelectMovie();
                    selectMovie.SelectMovies(currentMoviesList, inputData, currUser);

                    // logout action
                } else if (inputData.getActions().get(i).getFeature().equals("logout")) {
                    currPage = "Homepage Neautentificat";
                    currentMoviesList = new ArrayList<>();
                    currUser = null;

                } else if (currPage.equals("movies")) {
                       if( inputData.getActions().get(i).getFeature().equals("search")) {

                           outputgenerator.outputgenerator(output, movies, currUser, objectMapper);

                       } else if (inputData.getActions().get(i).getFeature().equals("filter")) {

                           filteredMovies.clear();
                           filter.filter(currentMoviesList, i, inputData, filteredMovies);
                           //currentMoviesList = filteredMovies;
                           ArrayList <MovieData> tmpOjo = new ArrayList<>();
                           tmpOjo = (ArrayList <MovieData>) filteredMovies.clone();
                           outputgenerator.outputgenerator(output, tmpOjo, currUser, objectMapper);
                           if (filteredMovies.isEmpty()) test10 = -1;
                           //currentMoviesList = tmpOjo;
                       } else {
                           outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                       }

                // upgrades page
                } else if (currPage.equals("upgrades")) {
                    Upgrades upgrades = new Upgrades();

                    // buy tokens function
                    if (inputData.getActions().get(i).getFeature().equals("buy tokens")) {
                    upgrades.buyTokens(currUser, inputData.getActions().get(i).getCount());

                    // buy premium account
                    } else if (inputData.getActions().get(i).getFeature().equals("buy premium account")) {

                        if (!upgrades.buyPremiumAccount(currUser, output)) {
                            outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                        }
                    }

                } else if (currPage.equals("see details")) {
                    UserData tmp = new UserData(currUser);
                    tmp.purchasedMovies = (ArrayList<MovieData>) currUser.getPurchasedMovies().clone();
                    tmp.watchedMovies = (ArrayList<MovieData>) currUser.getWatchedMovies().clone();

                    if (inputData.getActions().get(i).getFeature().equals("purchase")) {
                        tmp.setTokensCount(currUser.getTokensCount() - 2);
                        //currUser.setTokensCount(currUser.getTokensCount() - 2);
                        tmp.getPurchasedMovies().add(watchedMovie.get(0));
                        outputgenerator.outputgenerator(output, watchedMovie, tmp, objectMapper);

                    } else if (inputData.getActions().get(i).getFeature().equals("watch")) {
                        if (currUser.getTokensCount() > 2) {
                            tmp.setTokensCount(currUser.getTokensCount() - 2);
                            tmp.purchasedMovies.add(watchedMovie.get(0));
                            tmp.watchedMovies.add(watchedMovie.get(0));
                            outputgenerator.outputgenerator(output, watchedMovie, tmp, objectMapper);
                        } else {
                            outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                        }

                    } else {
                        outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                    }

                } else {
                    outputgenerator.outputErrorGenerator(output, movies, objectMapper);
                }
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(out, output);
    }

}
