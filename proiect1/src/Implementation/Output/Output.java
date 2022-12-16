package Implementation.Output;
import Implementation.Homepage.MovieData;
import Implementation.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class Output {
    public void outputgenerator(ArrayNode output, ArrayList<MovieData> currentMoviesList, UserData currUser, ObjectMapper objectMapper) {
        ObjectNode out2 = objectMapper.createObjectNode();
        out2.set("error", null);
        out2.putPOJO("currentMoviesList", currentMoviesList);
        out2.putPOJO("currentUser", currUser).deepCopy();
        output.add(out2.deepCopy()).deepCopy();
    }

    public void outputErrorGenerator(ArrayNode output, ArrayList<MovieData> movies, ObjectMapper objectMapper) {
        ObjectNode out2 = objectMapper.createObjectNode();
        out2.put("error", "Error");
        out2.putPOJO("currentMoviesList", movies);
        out2.putPOJO("currentUser", null);
        output.add(out2);
    }
}
