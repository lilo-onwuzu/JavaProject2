import java.util.ArrayList;

public class Definition {
  private String mDefine;
  private static ArrayList<Definition> defInstances = new ArrayList<Definition>();

  public Definition(String define){
    mDefine = define;
    defInstances.add(this); // whenever a definition class instance is created, add it to an arraylist
  }

  public String addDefinition(){
    return mDefine;
  }

  public static ArrayList<Definition> addAll(){
    return defInstances;
  }

}
