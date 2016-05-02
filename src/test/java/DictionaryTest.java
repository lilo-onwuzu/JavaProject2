import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class DictionaryTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

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
  // test that a word can be added to the list
  public void addWord_getAndPost() {
    goTo("http://localhost:4567/");
    fill("#inputName").with("Jane Doe");
    submit(".btn");
    assertThat(pageSource()).contains("Welcome Jane Doe");
    click("a", withText("Add A Word"));
    fill("#inputWord").with("Epicodus");
    submit(".btn");
    assertThat(pageSource()).contains("Epicodus");
  }

}
