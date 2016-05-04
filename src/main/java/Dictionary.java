// // class Dictionary in dictinary.java, class Word in word.java and class Definition in Definition.java are all in the src/main/java folder. These class libraries are in one "package" and you do not need to instance from one java file to another. The java.lang package which contains the class libraries for all the data types (e.g String, Integer etc.) are automatically imported by the java compiler into every package but you have to manually import some other java class libraries/packages as needed (e.g java.util) that make up the full java API (Application Programming Interface) as well as other class libraries you may need like Spark API class libraries. The class libraries are imported so the user does not have to call the full class name (e.g java.util.HashMap) whenever you need to call a class (e.g HashMap).
// import static spark.Spark.*;
// import spark.ModelAndView;
// import spark.template.velocity.VelocityTemplateEngine;
// import java.util.ArrayList;
// import java.util.HashMap;
//
// public class Dictionary {
//
//   public static void main(String [] args) {
//     // /public/resources path contains custom styles.css page. Add /Dictionary.css file link to the layout.vtl page
//     staticFileLocation("/public");
//     String layout = "templates/layout.vtl";
//
//     // get request to prompt user for name is there is no name saved in the session OR display customized homepage with user's name if otherwise.
//     // includes link to direct the user to wordList page
//     // includes link to direct the user to the addWord page
//     get("/", (request, response) -> {
//       HashMap model = new HashMap();
//       // if a $username is stored in the session, make the variable accessible in the home.vtl template using the model hashmap else the line below is not needed
//       model.put("userName", request.session().attribute("userName"));
//       model.put("template", "templates/home.vtl");
//       // ModelAndView is a class library in the spark API that allows variables in the .java files to be transferred through the model hashmaps into the templates that will be displayed by the server with the help of the spark application. Spark does not have the capability to use multiple templates with the same layout so the velocity template engine class in the spark API gives this capability.
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     // defines what happens in the case that no $userName is saved in the session and you make a post request to post the user's name to the session. This section defines how that post request is handled once it has been posted. get"/" and post"/" are essentially the same only get"/" describes what to do when the template only needs to be viewed for the first time or refreshed with $username available. Once the user's name is entered through the form, post"/" describes what to do when the template needs to be updated and then viewed. This action returns the user to the customized home page
//     post("/", (request, response) -> {
//       HashMap model = new HashMap();
//       // collect the user's name for="inputName" from the home.vtl page
//       String userName = request.queryParams("inputName");
//       // store the userName into the session using cookies
//       request.session().attribute("userName", userName);
//       // after server is updated, the page still needs to be viewed
//       model.put("userName", userName);
//       // after the user's name has been stored in the session return to the customized homepage
//       model.put("template", "templates/home.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     // defines the method to process a get request by the browser to the server through Spark in order to display the addWord form in addWord.vtl page. The form action for the addWord form triggers a "get" request when submitted to display the /defineWord page.
//     // <form action="/defineWord" method="get"> places a get request for /defineWord path next after the form is sucessfully submitted and posts the values e.g (inputWord=hello) to the end of the /defineWord URL path e.g the page that comes up after the form is submitted will likely be: /defineWord?inputWord=hello assuming hello was the input for the label with input id:inputWord
//     get("addWord", (request, response) -> {
//       HashMap model = new HashMap();
//       model.put("template", "templates/addWord.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     // allows user to define the word added in the addWord page and posts the word and first definition to the /wordList page
//     get("/define", (request, response) -> {
//       HashMap model = new HashMap();
//       // the inputWord string collected from the addWord form triggers the defineWord path and posts the query paramaeters to the url. Here you can access both the paramaters from the addWord form from the url path as well as the query parameters in the defineWord form at once
//       String newInputWord = request.queryParams("inputWord");
//       // create a new Word object using the Word(String wordName) construct. The String inputWord becomes the argument of the Word(String wordName) construct and a new word is created. Recall from Word.java class that whenever a new Word object is created, the word object is automatically added to a static (non-changing) arraylist of word objects "arraylist variable wordInstances" and an empty non-static (changing) arrayList variable is created within the word object that will receive all its definition objects.
//       Word newWord = new Word(newInputWord);
//       // apply Word Class method allwords() to return the full arraylist of words "wordInstances". Since wordInstances is a static arrayList, any word object can be used to return wordInstances. wordInstances is of type "ArrayList<Word>"
//       ArrayList<Word> fullWords = newWord.allWords();
//       model.put("fullWords", request.session().attribute("fullWords"));
//       // save the updated fullWords arraylist in the session everytime we post a new word so we do not have to create a new word object everytime we want to return use the allWords() method to return the fullWords arrayList. Recall that the fullWords arraylist is filled with word "objects" themselves. We need to access the wordName attribute/instance variable before we can display in the template
//       request.session().attribute("fullWords", fullWords);
//       // access the query parameters in defineWord form
//       String newDefine = request.queryParams("inputDefine");
//       Definition newDefinition = new Definition(newDefine);
//       // create a new arraylist variable (non-static) composing of empty Definition objects and run the addDefinition() method on the new Word object. This adds the newDefinition object argument to the arraylist and return the uodated arraylist to wordDefinition
//       ArrayList<Definition> wordDefinitions = newWord.addDefinition(newDefinition);
//       model.put("template", "templates/defineWord.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     // default get request for fullWordList page
//     get("/wordList", (request, response) -> {
//       HashMap model = new HashMap();
//       // retrieve the most recentlly updated fullWords arraylist of word objects saved in the session
//       model.put("fullWords", request.session().attribute("fullWords"));
//       // for loop to display definition object names for each word
//       for(){
//
//       }
//       model.put("template", "templates/wordList.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     // this defines how any post request to /wordList is handled and how the page is viewed. It is similar to get"/wordList" only includes some kind of update
//     post("/wordList", (request, response) -> {
//       HashMap model = new HashMap();
//       model.put("fullWords", request.session().attribute("fullWords"));
//       String newDefine = request.queryParams("inputDefine");
//       Definition newDefinition = new Definition(newDefine);
//       // create a new arraylist variable (non-static) composing of empty Definition objects and run the addDefinition() method on the new Word object. This adds the newDefinition object argument to the arraylist and return the uodated arraylist to wordDefinition
//       ArrayList<Definition> wordDefinitions = newWord.addDefinition(newDefinition);
//       model.put("template", "templates/wordList.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//   }
// }
//
// // <form action="/defineWord" method="get">
// //   <label for="hideWordName" type="hidden"></label>
// //   <input id="hideWordName" name= "hideWordName" type="hidden" value="$word.getWordName()">
// // </form>
