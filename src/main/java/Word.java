import java.util.ArrayList;
import java.time.LocalDateTime;

public class Word {
  private String mName; //this is an attribute/property/instance variable of the Word Class. this global scope instance variable is of reference type and so gets initialized as null. Global scope variables of primitive type gets initialized as '0'. Local scope variables do not get automativally instantiated and will cause compiler error if not initiatlized.
  private int wordId; //Id to keep track to number of words
  private static ArrayList<Word> wordInstances = new ArrayList<Word>(); //arraylist to add the full list of words
  private static ArrayList<Definition> definitionInstances = new ArrayList<Definition>(); //arraylist to add full list of deifinitions for each word
  private LocalDateTime mCreatedAt; // to keep track of when the word was created

  public Word(String wordName){
    mName = wordName; //this is a constructor of the Word Class. If the constructor is not defined, the compiler definies one automatically assuming that no arguments are needed. In this case, an argument of the word name is needed to define to construct.
    wordInstances.add(this); //everytime a word instance object is created with a word name, add the instance object to the full list of word objects

    mCreatedAt = LocalDateTime.now();
  }

  public String getWordName(){
    return mName;
  }


}
