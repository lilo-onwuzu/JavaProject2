import java.util.ArrayList;
import java.time.LocalDateTime;

public class Word {
  private String mName; // this is an attribute/property/instance variable of the Word Class. this global scope instance variable is of reference type and so gets initialized as null. Global scope variables of primitive type gets initialized as '0'. Local scope variables do not get automativally instantiated and will cause compiler error if not initiatlized.
  private int wordId; // id to keep track to number of words
  // arraylist to add the full list of words
  private static ArrayList<Word> wordInstances = new ArrayList<Word>();
  private ArrayList<Definition> mDefinitions;

  // this is a constructor of the Word Class. If the constructor is not defined, the compiler definies one automatically assuming that no arguments are needed. In this case, an argument of the word name is needed to define to construct.
  public Word(String wordName){
    mName = wordName;
    // everytime a word instance object is created with a word name, add that instance object to the full list of word objects
    wordInstances.add(this);
    mDefinitions = new ArrayList<Definition>();
  }

  public String getWordName(){
    return mName;
  }

  // allWords() method returns the full list of word objects that have been instanced through the Word Class
  public static ArrayList<Word> allWords(){
    return wordInstances;
  }

  // returns an array of definition object instances
  public ArrayList<Definition> addDefinition(){
    return mDefinitions;
  }

  public void addDefinition(Definition definition){
    // mDefinitions.add(definition);
  }

}
