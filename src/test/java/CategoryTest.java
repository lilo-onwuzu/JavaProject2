import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest{

  @Test
  public void Category_instantiatesCorrectly_true(){
    Category testCategory = new Category();
    assertEquals(true, testCategory instanceof Category);
  }

}
