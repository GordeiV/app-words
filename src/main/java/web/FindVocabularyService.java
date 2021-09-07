package web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/find")
public class FindVocabularyService {
    @GET
    public String findVocabulary() {
        return "Simple String";
    }
}
