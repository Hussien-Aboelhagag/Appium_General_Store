package Pages.MobileBrowser.API;

import Pages.MobileBrowser.API.POGO.TodoAPI;
import Pages.MobileBrowser.MobileConfiq.APIResources;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AddTodoAPI {
    TodoAPI todoItem=new TodoAPI(false,"learn rest assured");
    public void addNewTask(String token){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type","application/json")
                .body(todoItem)
                .auth().oauth2(token)
                .when()
                .post(APIResources.ADD_TASK_RESOURCE)
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract().response();
    }
}
