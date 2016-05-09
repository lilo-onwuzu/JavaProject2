import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class WordTest{

  @After
  public void tearDown() {
    // whenver the testing program execute, new word objects are being created that gets added to the wordInstances arraylist. @After tells the test program to clear the static arraylists in between and after tests so that indices and return objects are not mixed up
    Word.clear();
  }

  @Test
  // test to create instance/object of the word class
  public void Word_instantiatesCorrectly_true(){
    Word testWord = new Word("");
    assertEquals(true, testWord instanceof Word);
  }

  @Test
  // test to see if an object of the word class returns its name correctly
  public void Word_instantiatesWithName_String(){
    Word testWord = new Word("word");
    assertEquals("word", testWord.getWordName());
  }

  @Test
  // test to see that one word object is automatically added to the full arraylist of word objects. allWords() is a Word class method that returns the most updated static list of word objects since program execution
  public void Word_canAddOneWord_String(){
    Word testWord = new Word("word");
    // assertTrue is a method of the junit class library that passes this test if the argument evaluate to true
    assertTrue(Word.allWords().contains(testWord));
  }

  @Test
  // test to see that allWords() method adds multiple word objects automatically to the full arraylist of word objects. allWords() can be applied to any word object or the class object itself but will return the most updated list of wordInstances
  public void allWords_canAddMultipleWords_String(){
    Word testWord = new Word("my word");
    Word testWord2 = new Word("your word");
    assertTrue(Word.allWords().contains(testWord));
    assertTrue(Word.allWords().contains(testWord2));
  }

  @Test
  // test to see that addDefinition(arg) method adds one definition to the arraylist of definitions for one word object/instance
  public void addDefinition_canAddOneDefinitionForOneWord_String(){
    Word testWord = new Word("word");
    Definition testDefinition = new Definition("this word is good");
    testWord.addDefinition(testDefinition);
    assertTrue(testWord.getmDefinitions().contains(testDefinition));
  }

  @Test
  // test to see that addDefinition(arg) method adds multiple definitions to the arraylist of definitions for one word object/instance
  public void addDefinition_canAddMultipleDefinitionsForOneWord_String(){
    Word testWord = new Word("word");
    Definition testDefinition = new Definition("this word is good");
    Definition testDefinition2 = new Definition("this word is really good");
    testWord.addDefinition(testDefinition);
    testWord.addDefinition(testDefinition2);
    assertTrue(testWord.getmDefinitions().contains(testDefinition));
    assertEquals("this word is really good", testWord.getmDefinitions().get(1).getDefinition());
  }

  @Test
  public void getCreatedAt_instantiatesWithCurrentTime_today() {
    Word myWord = new Word("word");
    assertEquals(LocalDateTime.now().getDayOfWeek(), myWord.getCreatedAt().getDayOfWeek());
  }

  @Test
  public void find_returnsNullWhenNoWordFound_null() {
    assertTrue(Word.find(999) == null);
  }

}
