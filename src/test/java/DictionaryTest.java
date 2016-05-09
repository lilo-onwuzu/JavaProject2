import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
// filter Constructor is the fluentinum API class that allows us to use the click "withText" method
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class DictionaryTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @After
  public void tearDown() {
    // whenever the testing program executes (even in the integration test), new word objects are being created that gets added to the wordInstances arraylist. @After tells the test program to clear all the word objects used in testingso they are not mixed up with the word objects in the program itself. We also need to clear the word objects so each new test starts on a fresh note and the word ids are not mixed up. To use "@After" rule, we have to call the jUnit class library in the integration test
    Word.clear();
  }

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  // test that home page UI works
  public void homePageTest_homePage() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("please enter your name");
  }

  @Test
  // test that the user's name is collected and customized home page works
  public void userName_get() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
  }

  @Test
  // test that the user's name posts correctly to the session
  public void userName_getAndPost() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
    click("a", withText("Add A Word"));
    click("a", withText("Return To HomePage"));
    assertThat(pageSource()).contains("Welcome Jane Doe");
  }

  @Test
  // test that a word is posted to the list
  public void addWord_addAndPost() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Epicodus");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
  }

  @Test
  // test that multiple words are posted to the list
  public void addMultipleWords_addAndPost() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Epicodus");
    submit(".btn");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Epicenter");
    submit(".btn");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Software");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
    assertThat(pageSource()).contains("Epicenter");
    assertThat(pageSource()).contains("Software");
  }

  @Test
  // test that one definition can be added to the list for one word
  public void addOneDefinition_addAndPost() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Epicodus");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
    click("a", withText("Epicodus"));
    click("a", withText("Add A New Definition for Epicodus"));
    fill("#inputDefine").with("Epicodus is a special place to learn code");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
    assertThat(pageSource()).contains("Epicodus is a special place to learn code");
  }

  @Test
  // test that multiple definitions can be added to the list for one word
  public void addMultipleDefinitions_addAndPost() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Epicodus");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
    click("a", withText("Epicodus"));
    click("a", withText("Add A New Definition for Epicodus"));
    fill("#inputDefine").with("Epicodus is a special place to learn code");
    submit(".btn");
    click("a", withText("Add A New Definition for Epicodus"));
    fill("#inputDefine").with("Epicodus also has a relaxed atmosphere");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
    assertThat(pageSource()).contains("Epicodus is a special place to learn code");
    assertThat(pageSource()).contains("Epicodus also has a relaxed atmosphere");
  }

  @Test
  // test that multiple definitions can be added to the list for multiple words
  public void addMultipleDefinitionsMultipleWords_addAndPost() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Epicodus");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
    click("a", withText("Epicodus"));
    click("a", withText("Add A New Definition for Epicodus"));
    fill("#inputDefine").with("Epicodus is a special place to learn code");
    submit(".btn");
    click("a", withText("Add A New Definition for Epicodus"));
    fill("#inputDefine").with("Epicodus also has a relaxed atmosphere");
    submit(".btn");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Portland");
    submit(".btn");
    assertThat(pageSource()).contains("Portland");
    click("a", withText("Portland"));
    click("a", withText("Add A New Definition for Portland"));
    fill("#inputDefine").with("Portland is filled with such happy people");
    submit(".btn");
    click("a", withText("Add A New Definition for Portland"));
    fill("#inputDefine").with("My city Portland is my home");
    submit(".btn");
    assertThat(pageSource()).contains("Portland");
    assertThat(pageSource()).contains("Portland is filled with such happy people");
    assertThat(pageSource()).contains("My city Portland is my home");
    click("a", withText("See Full Word List"));
    click("a", withText("Epicodus"));
    assertThat(pageSource()).contains("Epicodus");
    assertThat(pageSource()).contains("Epicodus is a special place to learn code");
    assertThat(pageSource()).contains("Epicodus also has a relaxed atmosphere");
  }

}
