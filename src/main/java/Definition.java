import java.time.LocalDateTime;
// definition class is the smallest unit class in this project
public class Definition {
  private String mDefine;
  private LocalDateTime mCreatedAt;

  // definition construct. Default construct "Definition()" is not enough since we need to customize each definition
  public Definition(String define){
    mDefine = define;
    // LocalDateTime.now() defines the time a definition is created using the construct
    mCreatedAt = LocalDateTime.now();
  }

  // every definition instance can return its name
  public String getDefinition(){
    return mDefine;
  }

}
