package MobileBrowser;

import Pages.MobileBrowser.API.AddTodoAPI;
import Pages.MobileBrowser.API.RegisterAPI;
import Pages.MobileBrowser.TodoPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddTodo extends MobileBrowserBase{
    @Test(description = "test Add todo")
    @Story("login the email, add todo")
    @Feature("Adding Todo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("validate that user can login with valid data, and add todo")
    public void userCanAddTodo(){
        RegisterAPI registerAPI=new RegisterAPI();
        registerAPI.register();
        TodoPage todoPage=new TodoPage(driver);
        injectCookieIntoSelenium(registerAPI.getRegisterCookie());
        todoPage.todoSteps("learn selenium and Appium");
        Assert.assertTrue(todoPage.loginMsgIsDisplayed());
        Assert.assertEquals(todoPage.getTodoItemName(),"learn selenium and Appium");
    }
    @Test(description = "test Delete todo")
    @Story("login the email, add todo, and delete it")
    @Feature("Deleting Todo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("validate that user can login with valid data, add todo, and delete")
    public void userCanDeleteTodo(){
        RegisterAPI registerAPI=new RegisterAPI();
        registerAPI.register();
        AddTodoAPI addTodoAPI=new AddTodoAPI();
        addTodoAPI.addNewTask(registerAPI.getAccessToken());
        TodoPage todoPage=new TodoPage(driver);
        injectCookieIntoSelenium(registerAPI.getRegisterCookie());
        todoPage.deleteTodo();
        Assert.assertEquals(todoPage.getTodoItemNameAfterDelete(),"No Available Todos");
    }
}
