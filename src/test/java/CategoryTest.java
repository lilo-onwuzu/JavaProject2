import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest{

  @Test
  public void Category_instantiatesCorrectly_true(){
    Category testCategory = new Category("word");
    assertEquals(true, testCategory instanceof Category);
  }

  @Test
  public void Category_instantiatesWithName_true(){
    Category testCategory = new Category("word");
    assertEquals("word", testCategory.getCategoryName());
  }

}
