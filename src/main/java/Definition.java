import java.util.ArrayList;

public class Definition {
  private String mDefine;
    // create an array list to store many definitions within one category

  // definition construct
  public Definition(String define){
    mDefine = define;
    // add this defintion created to a category but this is not requ9ired to create a definition since it is not in the argument list
  }

  // method to add a string for definition
  public String getDefinition(){
    return mDefine;
  }


}
