package stepdefs;

import helper.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Step_Book_API {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private String API_URL = Utils.readProperty("config.properties", "apiTestUrl");

    @Given("a book exists with an isbn of {string}")
    public void a_book_exists_with_isbn(String isbn){
        request = given().param("q", isbn);
    }

    @When("a user retrieves the book by isbn")
    public void a_user_retrieves_the_book_by_isbn(){
        response = request.when().get(API_URL + "/api/test");
//        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status code is {int}")
    public void verify_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    /**
     * asserts on json fields with single values
     */
    @And("response includes the following$")
    public void response_equals(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }
}
