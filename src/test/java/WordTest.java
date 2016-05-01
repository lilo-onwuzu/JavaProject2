import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class WordTest{

  @Test
  public void Word_instantiatesCorrectly_true(){
    Word testWord = new Word("word");
    assertEquals(true, testWord instanceof Word);
  }

  @Test
  public void Word_instantiatesWithName_String(){
    Word testWord = new Word("word");
    assertEquals("word", testWord.getWordName());
  }

  @Test
  public void allWords_canAddOneWord_String(){
    Word testWord = new Word("word");
    assertTrue(testWord.allWords().contains(testWord));
  }

  @Test
  public void allWords_canAddMultipleWords_String(){
    Word testWord = new Word("");
    Word testWord2 = new Word("");
    assertTrue(testWord2.allWords().contains(testWord));
    assertTrue(testWord2.allWords().contains(testWord2));
  }
  //
  // @Test
  // public void addDefinition_canAddOneDefinitionForOneWord_String(){
  //   Word testWord = new Word("");
  // }
}
