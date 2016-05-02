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

    // get request to prompt user for name is there is no name saved in the session OR display customized homepage with user's name if otherwise.
    // includes link to direct the user to wordList page
    // includes link to direct the user to the addWord page
    get("/", (request, response) -> {
      HashMap model = new HashMap();
      // if a $username is stored in the session, make the variable accessible in the home.vtl template using the model hashmap else the line below is not needed
      model.put("userName", request.session().attribute("userName"));
      model.put("template", "templates/home.vtl");
      // ModelAndView is a class library in the spark API that allows variables in the .java files to be transferred through the model hashmaps into the templates that will be displayed by the server with the help of the spark application. Spark does not have the capability to use multiple templates with the same layout so the velocity template engine class in the spark API gives this capability.
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // defines what happens in the case that no $userName is saved in the session and you make a post request to post the user's name to the url file path "/". This section defines how that post request is handled once it has been posted. get"/" and post"/" are essentially the same only get"/" describes what to do when the template only needs to be viewed for the first time or refreshed with $username available. Once the user's name is entered through the form, post"/" describes what to do when the template needs to be updated and then viewed.This action should return the user back to the customized home page
    post("/", (request, response) -> {
      HashMap model = new HashMap();
      // collect the user's name for="inputName" from the home.vtl page
      String userName = request.queryParams("inputName");
      // store the userName into the session using cookies
      request.session().attribute("userName", userName);
      // after server is updated, the page still needs to be viewed
      model.put("userName", userName);
      // after the user's name has been stored in the session return to the customized homepage
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // defines the method to process a get request to server in order to display the addWord form in addWord.vtl page
    // the form in addWord.vtl makes a post request to the url /wordList path so that the form info can be accessible in the /wordList url path
    get("addWord", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/addWord.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // default get request for fullWordList page
    get("/wordList", (request, response) -> {
      HashMap model = new HashMap();
      // retrieve the most recentlly updated fullWords arraylist saved in the session
      model.put("fullWords", request.session().attribute("fullWords"));
      model.put("template", "templates/wordList.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // once the form inputs have been posted to the url file path in wordList.vtl, this defines how that post request is handled and how the page is viewed similar to get"/wordList". Returns wordList.vtl page
    post("/wordList", (request, response) -> {
      HashMap model = new HashMap();
      // collect the inputWord string and save as a variable
      String newInputWord = request.queryParams("inputWord");
      // create a new Word object using the Word(String wordName) construct. The String inputWord becomes the argument of the Word(String wordName) construct and a new word is created. Recall from Word.java class that whenever a new Word object is created, the word object is automatically added to a static (non-changing) arraylist of word objects "arraylist variable wordInstances" and an empty non-static (changing) arrayList variable is created within the word object that will receive all its definition objects.
      Word newWord = new Word(newInputWord);
      // apply Word Class method allwords() to return the full arraylist of words "wordInstances". Since wordInstances is a static arrayList, any word object can be used to return wordInstances. wordInstances is of type "ArrayList<Word>"
      ArrayList<Word> fullWords = newWord.allWords();
      // save the updated fullWords arraylist in the session everytime we post a new word so we do not have to create a new word object everytime we want to return use the allWords() method to return the fullWords arrayList. Recall that the fullWords arraylist is filled with word "objects" themselves. We need to access the wordName attribute/instance variable before we can display in the template
      request.session().attribute("fullWords", fullWords);
      String myWordName = newWord.getWordName();
      // save every new word's name in the session
      request.session().attribute("myWordName", myWordName);
      // make fullWords list available in the template for display
      model.put("fullWords", fullWords);

      // // run this loop to attach specialized list of definition objects to each word object in the fullwords arraylist
      // for (int index = 0; index < fullWords.size; index++) {
      //   String concatEntry = ("inputDefine" + "word.getWordName()");
      //   while (concatEntry.equals("inputDefine$myWordName")) {
      //     // if inputDefine is not found, this is ignored. Whenever the post request in defineWord.vtl runs, the  path /wordsList will now see the String inputDefine
      //     String inputDefinition = request.queryParams("inputDefine$myWordName");
      //     // create new definition object using Definition(String define) construct
      //     Definition newDefinition = new Definition(inputDefinition);
      //     // for each word object in fullWords, attach a newDefinition object to its empty definitions arraylist and return the arraylist
      //     ArrayList<Definition> wordDefinitions = word.addDefinition(newDefinition);
      //     model.put("wordDefinitions", wordDefinitions);
      //   }
      // }

      model.put("template", "templates/wordList.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/defineWord:$myWordName", (request, response) -> {
      HashMap model = new HashMap();
      // model.put("myWordName", request.session().attribute(myWordName));
      model.put("template", "templates/defineWord.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  }
}
