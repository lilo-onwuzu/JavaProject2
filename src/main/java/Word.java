import java.util.ArrayList;
import java.time.LocalDateTime;

public class Word {
  // this is an attribute/property/instance variable of the Word Class. this global scope instance variable is of reference type and so gets initialized as null. Global scope variables of primitive type gets initialized as '0'. Local scope variables do not get automativally instantiated and will cause compiler error if not initiatlized.
  private String mName;
  // id to keep track to number of words
  private int wordId;
  // arraylist to add the full list of word objects. Only one list of words objects so it can be static
  private static ArrayList<Word> wordInstances = new ArrayList<Word>();
  //arraylist to add one list of definitions per word. Should NOT be static
  private ArrayList<Definition> mDefinitions;

  // this is a constructor of the Word Class. If the constructor is not defined, the compiler definies one automatically assuming that no arguments are needed. In this case, an argument of the word name is needed to define to construct.
  public Word(String wordName){
    mName = wordName;
    // automatically adds the newly formed word instance object to the full list of word objects
    wordInstances.add(this);
    // automatically create an empty arraylist of definition objects called mDefinitions whenever a word object is created or instanced
    mDefinitions = new ArrayList<Definition>();
  }

  // method to get word name
  public String getWordName(){
    return mName;
  }

  // allWords() method returns the full list of word objects that have been instanced through the Word Class. Everytime a word instance object is created, add that instance object itself to the full list of word objects wordInstances
  public static ArrayList<Word> allWords(){
    return wordInstances;
  }

  // addDefinition(arg) method adds the definition object in the argument to the empty arraylist (definition arraylist "mdefinition" auto created with word object instance) of definition objects
  public ArrayList<Definition> addDefinition(Definition definition){
    mDefinitions.add(definition);
    return mDefinitions;
  }

}
