import java.util.ArrayList;

public class Definition {
  private String mDefine;
  private ArrayList<Definition> definitionInstance = new ArrayList<Definition>();

  public Definition(String define){
    mDefine = define;
    definitionInstance.add(this); // whenever a definition class instance is created, add it to an arraylist
  }

  public String addDefinition(){
    return null;
  }

}
