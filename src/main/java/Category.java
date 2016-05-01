import java.util.ArrayList;

public class Category {
  private String mCategoryName;

  // category class construct with its instance variables whenever a definition is created, it is added to "one" instance of the category blueprint/class
  public Category(String categoryName){
    mCategoryName = categoryName;
  }

  // a category can be created at any point as long as the argument name is given
  public String getCategoryName(){
    return mCategoryName;
  }

}
