// definition class is the smallest unit class in this project
public class Definition {
  private String mDefine;

  // definition construct. Default construct "Definition()" is not enough since we need to customize each definition
  public Definition(String define){
    mDefine = define;
  }

  // every definition instance can return its name
  public String getDefinition(){
    return mDefine;
  }
}
