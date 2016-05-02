// class Dictionary in dictinary.java, class Word in word.java and class Definition in Definition.java are all in the src/main/java folder. These class libraries are in one "package" and you do not need to instance from one java file to another. The java.lang package which contains the class libraries for all the data types (e.g String, Integer etc.) are automatically imported by the java compiler into every package but you have to manually import some other java class libraries/packages as needed (e.g java.util) that make up the full java API (Application Programming Interface) as well as other class libraries you may need like Spark API class libraries. The class libraries are imported so the user does not have to call the full class name (e.g java.util.HashMap) whenever you need to call a class (e.g HashMap).
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {

  public static void main(String [] args) {
    // /public/resources path contains custom styles.css page. Add /Dictionary.css file link to the layout.vtl page
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // get request to prompt user for name is there is no name saved in the session OR display customized homepage with user's name if otherwise. Includes link to wordList page
    get("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/home.vtl");
      // in the event that a $username is stored in the session, else the line below is not needed
      request.session().attribute("userName", userName);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // defines what happens in the case that no $userName is saved in the session and  you make a post request to post the user's name to the session. This action should return the user back to the customized home page
    post("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/home.vtl");
      String userName = request.queryParams("inputName");
      model.put("userName", userName);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get request made to server to display the addWord form in addWord.vtl page which makes a post request to the session
    get("addWord", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/addWord.vtl")
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // defines what happens after you make a post request to post a new word to the session from addWord.vtl page. This should return the user to the fullWordList page
    post("/addWord", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates.wordList.vtl")
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());


    get("/wordList", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/wordList.vtl");
      String newInputWord = request.queryParams("inputWord");
      Word newWord = new Word(newInputWord);
      ArrayList<Word> fullWordList = new ArrayList<Word>(newWord.allWords());
      model.put("fullWordList", fullWordList);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
