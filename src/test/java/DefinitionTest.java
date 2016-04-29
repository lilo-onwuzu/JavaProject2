import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest{

  @Test
  public void Definition_instantiatesCorrectly_true(){
    Definition testDefinition = new Definition("");
    assertEquals(true, testDefinition instanceof Definition);
  }

  @Test
  public void Definition_instantiatedwithString_String(){
    Definition testDefinition = new Definition("this word is good");
    assertEquals("this word is good", testDefinition.addDefinition());
  }

  @Test
  public void Definition_OneDefinitionAddedToList_ArrayList(){
    Definition testDefinition = new Definition("this word is good");
    assertTrue(testDefinition.addAll().contains(testDefinition));
  }

}
