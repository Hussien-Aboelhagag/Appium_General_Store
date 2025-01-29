package Pages.MobileBrowser;

import Pages.PageBase;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TodoPage extends PageBase {
    public TodoPage(AndroidDriver driver) {
        super(driver);
    }
    private final By loginMsg = By.cssSelector("[data-testid='welcome']");
    private final By addBtn = By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root']");
    private final By todoInput = By.cssSelector("[data-testid='new-todo']");
    private final By createBtn = By.cssSelector("[data-testid='submit-newTask']");
    private final By todoItem = By.cssSelector("[data-testid='todo-text']");
    private final By deleteTodoItem = By.cssSelector("[data-testid='delete']");
    private final By deleteTodoItemMSg = By.cssSelector("[data-testid='no-todos']");

    @Step("add todo item")
    public void todoSteps(String todoItem){
        clickBtn(addBtn);
        sendKeys(todoInput,todoItem);
        clickBtn(createBtn);
    }
    @Step
    public Boolean loginMsgIsDisplayed(){
        return driver.findElement(loginMsg).isDisplayed();
    }
    public String getTodoItemName(){
        return driver.findElement(todoItem).getText();
    }
    @Step
    public void deleteTodo(){
        clickBtn(deleteTodoItem);
    }
    public String getTodoItemNameAfterDelete(){
        return driver.findElement(deleteTodoItemMSg).getText();
    }
    public TodoPage load(){
        driver.get("https://qacart-todo.herokuapp.com/todo");
        return this;
    }
}
