import org.junit.*;
import static org.junit.Assert.*;

public class WordTest{

  @Test
  public void Word_instantiatesCorrectly_true(){
    Word testWord = new Word("word");
    assertEquals(false, testWord instanceof Word);
  }

}
