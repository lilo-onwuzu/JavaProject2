// imports all(*) the junit class libraries that will be used in testing the units (classes:Word, Definition)
import org.junit.*;
// imports all the junit Assert class libraries that transfers the junit testing matrix into true/false testing matrix
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class DefinitionTest {

  @Test
  // test that a definition object can be created from the Definition class
  public void Definition_instantiatesCorrectly_true(){
    Definition testDefinition = new Definition("define");
    // pass this test if both statements returns things that equal
    // instanceof is likely a property of the junit class that checks if testDefinition is an objectof type Definition
    assertEquals(true, testDefinition instanceof Definition);
  }

  @Test
  // tests that the definition object created is correctly customized
  public void Definition_instantiatedwithString_String(){
    Definition testDefinition = new Definition("this word is good");
    // getDefinition() can be applied on any Definition object to return its own custom name
    assertEquals("this word is good", testDefinition.getDefinition());
  }

}
