// class Dictionary existing in dictionary.java, class Word in word.java and class Definition in Definition.java are all in the src/main/java folder. These classes are in one package/library and you do not need to import the full class name from othese java files to another. The java.lang package which contains the class libraries for all the data types (e.g String, Integer etc.) are automatically imported by the java compiler into every package but you have to manually import some other java class libraries/packages as needed (e.g java.util) that make up the full java API (Application Programming Interface) as well as other class libraries you may need like Spark API class libraries. The class libraries are imported so the user does not have to call the full class name (e.g java.util.HashMap) whenever you need to call a class (e.g HashMap).
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {

  public static void main(String [] args) {
    // ""/public/resources" path contains my custom styles.css page. Add /Dictionary.css file as a link to the custom css page in the layout.vtl page
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // get request to prompt user for name if there is no name saved in the session OR display customized homepage with user's name if otherwise.
    // includes link to direct the user to wordList page
    // includes link to direct the user to the addWord page
    get("/", (request, response) -> {
      HashMap model = new HashMap();
      // if a $username is stored in the session, make the variable accessible in the home.vtl template using the model hashmap else the line below will return null when userName is called in the .vtl page
      // sessions make variables accessible from page to page
      model.put("userName", request.session().attribute("userName"));
      model.put("template", "templates/home.vtl");
      // ModelAndView is a class library in the spark API that allows variables from .java files such as this to be transferred through the model hashmaps into the templates that will be displayed by the server with the help of the spark application
      return new ModelAndView(model, layout);
      // spark does not have the capability to use multiple templates with the same layout so the velocity template engine class in the spark API gives this added capability
    }, new VelocityTemplateEngine());

    // defines what happens in the case that no $userName is saved in the session. Since there is no $userName in the session, the form will be displayed for the user to input name. The form accepts this info and directs the page using form action="/" to back to the initial page "/" only now, there will be $userName saved in the session and a customized welcome page is displayed.
    // now you have made a post request using the form, we have to define how to handle the info collected in that form. do we want to operate on that info it here? or make it directly available in the session? or operate on it, collect some secondary info and then make that info available in the session? We define all that here
    // posts request are typically used to update pages
    // for this program, there is only one "/" path, in one case where the userName is stored, the "default" get request is sent to the server and the template welcomes the user with the customize page and the other case where $userName is not saved in the session cookie so the default get request runs first, which displays the form that asks for the user's name and triggers a post request that does the same function as the get "/" request only this time, $userName will be available in the session
    post("/", (request, response) -> {
      HashMap model = new HashMap();
      // collect the user's name from the name/value pair (for="inputName") in home.vtl
      String userName = request.queryParams("inputName");
      // store the userName into the session using cookies to make it available from page to page
      request.session().attribute("userName", userName);
      // after the server has handles the post request and the session has been updated with $userName, we want to display the home.vtl page again only this time $userName will not be null/false. and will display a customized welcome page
      model.put("userName", userName);
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // once the user clicks the link in the "/" path to add a word, this triggers a get "/addWord" request to the serve. this defines the method to process that get request by the browser to the server through Spark in order to display the addWord form in the addWord.vtl page. The form action for the addWord form triggers a "get" request to get the "/define" path once the form is submitted and to display the /define page.
    // <form action="/define" method="get"> places a get request for the "/define" path next after the form is sucessfully submitted and posts the name/value pair values e.g (inputWord=hello) to the end of the /define URL path e.g the page that comes up after the form is submitted will likely be: /define?inputWord=hello assuming hello was the input value for the label with name:inputWord
    get("/addWord", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/addWord.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // if a get request is made using a form, the name/value pairs are appended to the url of the form action/triggered page
    // Our "/addWord" page had triggered a get request to "/define" on submit. Therefore in this get request method, we need to define how the server should handle the name/value pairs appended to the URL using the addWord form
    get("/define", (request, response) -> {
      // define hashmap operation is to map string to variable. We need to tell this hashmap called model to map String to Object
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newInputWord = request.queryParams("inputWord");
      // create a new Word object using the Word(String wordName) construct. The String inputWord becomes the argument of the Word(String wordName) construct and a new word is created. Recall from Word.java class that whenever a new Word object is created, the word object is automatically added to a static (non-changing) arraylist of word objects "arraylist variable wordInstances" and an empty non-static (changing) arrayList variable is created within the word object that will receive all its definition objects.
      Word newWord = new Word(newInputWord);
      // whenever a new Word object is created, apply Word Class method allwords() to return the full arraylist of words. Since it is a static arrayList, any word object can be used to return the most recently updated arraylist of word objects. The arraylist should be of type ArrayList<Word>
      // whenever a new word object is created, save the most recently updated arraylist fullWords into the session
      ArrayList<Word> fullWords = newWord.allWords();
      request.session().attribute("newWord", newWord);
      request.session().attribute("fullWords", fullWords);
      // and then make it available in the UI template
      model.put("fullWords", fullWords);
      model.put("newWord", newWord);
      model.put("template", "templates/defineWord.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // default get request in the case that "/wordList" is accessed by clicking on a link
    get("/wordList", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // retrieve the most recently updated fullWords arraylist of word objects saved in the session
      ArrayList<Word> fullWords = request.session().attribute("fullWords");
      model.put("fullWords", fullWords);
      // retrieve the full arraylist of definition objects for every word that has been instanced/created
      for (Word word : fullWords) {
        ArrayList<Definition> wordDefinitions = word.getmDefinitions();
        model.put("wordDefinitions", wordDefinitions);
      }
      model.put("newWord", request.session().attribute("newWord"));
      model.put("template", "templates/wordList.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post request in the case that "/wordList" is accessed by submitting the form in "/define"
    post("/wordList", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      ArrayList<Word> fullWords = request.session().attribute("fullWords");
      model.put("fullWords", fullWords);
      // access the query parameters in the /define form
      String newDefine = request.queryParams("inputDefine");
      Definition newDefinition = new Definition(newDefine);
      // an empty new/non-static arraylist variable (mDefinitions) of type ArrayList<Definition> was already created within the newWord object. Recall addDefinition(Definition define) is a Word method that adds a Definition to the mDefinitions arraylist. This will add the first definition to the word
      Word newWord = request.session().attribute("newWord");
      newWord.addDefinition(newDefinition);
      for (Word word : fullWords) {
        ArrayList<Definition> wordDefinitions = word.getmDefinitions();
        model.put("wordDefinitions", wordDefinitions);
      }
      model.put("template", "templates/wordList.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
