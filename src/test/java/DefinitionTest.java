import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class DefinitionTest{

  @Test
  public void Definition_instantiatesCorrectly_true(){
    Definition testDefinition = new Definition("");
    assertEquals(true, testDefinition instanceof Definition);
  }

  @Test
  public void Definition_instantiatedwithString_String(){
    Definition testDefinition = new Definition("this word is good");
    assertEquals("this word is good", testDefinition.getDefinition());
  }

  // @Test
  // public void Definition_OneDefinitionAddedToOneCategoryList_ArrayList(){
  //   Definition testDefinition = new Definition("this word is good");
  //   Category testCategory = new Category("urban");
  //   assertEquals(testDefinition.addCat().contains(testDefinition));
  // }

  // @Test
  // public void Definition_MultipleDefinitionsAddedToList_ArrayList(){
  //   Definition testDefinition = new Definition("this word is good");
  //   Definition testDefinition2 = new Definition("this word is really good");
  //   assertTrue(Definition.addAll().contains(testDefinition));
  //   assertTrue(Definition.addAll().contains(testDefinition2));
  // }
}
