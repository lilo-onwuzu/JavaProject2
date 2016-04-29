public class Word {
private String mName; //this is an attribute/property/instance variable of the Word Class. this global scope instance variable is of reference type and so gets initialized as null. Global scope variables of primitive type gets initialized as '0'. Local scope variables do not get automativally instantiated and will cause compiler error if not initiatlized.

  public Word(String wordName){
    mName = wordName; //this is a constructor of the Word Class. If the constructor is not defined, the compiler definies one automatically assuming that no arguments are needed. In this case, an argument of the word name is needed to define to construct.
  }

  public String getWordName(){
    return null;
  }

}
