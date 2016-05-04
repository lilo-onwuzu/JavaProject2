// import org.junit.*;
// import static org.junit.Assert.*;
// import java.util.ArrayList;
//
// public class WordTest{
//
//   @Test
//   // test to create instance/object of the word class
//   public void Word_instantiatesCorrectly_true(){
//     Word testWord = new Word("");
//     assertEquals(true, testWord instanceof Word);
//   }
//
//   @Test
//   // test to create an object of the word class with the word name
//   public void Word_instantiatesWithName_String(){
//     Word testWord = new Word("word");
//     assertEquals("word", testWord.getWordName());
//   }
//
//   @Test
//   // test to see that one word object is automatically added to the full arraylist of word objects
//   public void Word_canAddOneWord_String(){
//     Word testWord = new Word("");
//     assertTrue(testWord.allWords().contains(testWord));
//   }
//
//   @Test
//   // test to see that allWords() method adds multiple word objects automatically to the full arraylist of word objects
//   public void allWords_canAddMultipleWords_String(){
//     Word testWord = new Word("");
//     Word testWord2 = new Word("");
//     assertTrue(testWord.allWords().contains(testWord));
//     assertTrue(testWord.allWords().contains(testWord2));
//   }
//
//   @Test
//   // test to see that addDefinition(arg) method adds one definition to the arraylist of definitions for one word object/instance
//   public void addDefinition_canAddOneDefinitionForOneWord_String(){
//     Word testWord = new Word("word");
//     Definition testDefinition = new Definition("this word is good");
//     testWord.addDefinition(testDefinition);
//     assertTrue(testWord.addDefinition(testDefinition).contains(testDefinition));
//   }
//
//   @Test
//   // test to see that addDefinition(arg) method adds multiple definitions to the arraylist of definitions for one word object/instance
//   public void addDefinition_canAddMultipleDefinitionsForOneWord_String(){
//     Word testWord = new Word("word");
//     Definition testDefinition = new Definition("this word is good");
//     Definition testDefinition2 = new Definition("this word is really good");
//     testWord.addDefinition(testDefinition);
//     testWord.addDefinition(testDefinition2);
//     assertEquals("this word is good", testWord.addDefinition(testDefinition).get(0).getDefinition());
//     assertTrue(testWord.addDefinition(testDefinition2).contains(testDefinition2));
//   }
//
//
// }
