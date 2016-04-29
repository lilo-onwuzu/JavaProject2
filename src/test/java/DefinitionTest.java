import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest{

  @Test
  public void Definition_instantiatesCorrectly_true(){
    Definition newDefinition = new Definition("");
    assertEquals(true, newDefinition instanceof Definition);
  }

  @Test
  public void Definition_instantiatedwithString_String(){
    Definition newDefinition = new Definition("this word is good");
    assertEquals("this word is good", newDefinition.addDefinition());
  }

}
