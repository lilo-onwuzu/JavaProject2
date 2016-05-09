import java.util.ArrayList;
import java.time.LocalDateTime;

public class Word {
  // this is where we declare the attribute/property/instance variables of the Word Class. "private String mName" global scope instance variable is of reference type and so gets initialized as null. Global scope variables of primitive type gets initialized as '0' 'null' or 'false'. Local scope variables do not get automatically initialized and will cause compiler error if not initiatlized within.
  // private access instance variables so that other Word objects or all other objects in general (within this class package) do not have access to these variables within methods in which they are called
  // mName is necessary customize word objects
  private String mName;
  // id to keep track of number of words
  private int mId;
  // arraylist to add the full list of word objects. There should exist only one list of words objects so it must be static/non-changing from word object to word object
  private static ArrayList<Word> wordInstances = new ArrayList<Word>();
  // arraylist to add list of definitions per word. Should not be static so mDefinitions should vary from word object to another
  private ArrayList<Definition> mDefinitions;
  private LocalDateTime mCreatedAt = LocalDateTime.now();

  // this is where we define the construct of the Word Class. If the constructor is not defined, the compiler definies one automatically assuming that no arguments are needed(Word())
  public Word(String wordName){
    // only the mName information is required to be defined before any word object can be made, every other function within the construct just happen whenever the Word(mName) construct is called
    mName = wordName;
    // automatically adds the newly formed word instance object to the full list of word objects
    wordInstances.add(this);
    // automatically creates an empty arraylist of definition objects called mDefinitions whenever a word object is created or instanced
    mDefinitions = new ArrayList<Definition>();
    mId = wordInstances.size();
  }

  // method to get word name. will be useful in the UI and testing.
  public String getWordName(){
    return mName;
  }

  // addDefinition(arg) defines a method to operate on a definition object. Definition mDefinition is an attribute/property/instance variable of the word class so all Word methods can operate on them
  // the argument of this method is needed so that the addDefinition method cannot be called upon on a Word object without including the name of the definition object that needs to be added
  // addDefinition(mdefinition) method adds the property mDefinition to the auto created arraylist variable mDefinitions
  // void methods do not return anything when they are called. it just updates or does something.
  public void addDefinition(Definition definition){
    mDefinitions.add(definition);
  }

  // allWords() method returns the full list of word objects that have been instanced through the Word Class (only one list). will be useful for looping through all the word objects created since program execution time while in the UI
  public static ArrayList<Word> allWords(){
    return wordInstances;
  }

  // returns the full arraylist of definition "objects" for this word class will be useful to loop through the list of definitions for a Word object in the UI
  public ArrayList<Definition> getmDefinitions(){
    return mDefinitions;
  }

  public static void clear() {
    wordInstances.clear();
  }

  public int getId() {
    return mId;
  }

  public LocalDateTime getCreatedAt() {
    return mCreatedAt;
  }

  public static Word find(int id) {
    try {
      return wordInstances.get(id - 1);
    } catch (IndexOutOfBoundsException exception) {
        return null;
      }
  }

}
