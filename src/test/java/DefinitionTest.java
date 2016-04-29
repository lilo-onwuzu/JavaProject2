import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest{

  @Test
  public void Definition_instantiatesCorrectly_true(){
    Definition newDefinition = new Definition();
    assertEquals(true, newDefinition instanceof Definition);
  }


}
