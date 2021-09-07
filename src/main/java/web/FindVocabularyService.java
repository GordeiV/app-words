package web;

import entity.Vocabulary;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/find")
public class FindVocabularyService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Vocabulary findVocabulary(Vocabulary vocabulary) {
        System.out.println(vocabulary.toString());
        return new Vocabulary("good request");
    }
}

//    Vocabulary vocabulary = new Vocabulary(
//            "school",
//            LocalDateTime.now(),
//            LocalDateTime.now().plusDays(3),
//            VocabularyStatus.FIFTH_REPEAT,
//            88L);
//    ArrayList<Word> words = new ArrayList<>();
//        words.add(new Word("pen", "pen", "pen"));
//                words.add(new Word("desk", "desk", "desk"));
