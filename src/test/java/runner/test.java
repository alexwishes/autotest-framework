package runner;

import helper.DataHelper;
import helper.Utils;

import java.util.HashMap;
import java.util.List;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class test {
    public static void main(String[] args) {
        DataHelper d = new DataHelper();
        String excelFile = Utils.readProperty("config.properties", "apiFile");
        String host = Utils.readProperty("config.properties", "apiTestUrl");
        List<HashMap<String, String>> test = d.readTestData(excelFile, "API");
        for (int i = 0; i < test.size(); i++) {
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp = test.get(i);
            String tcName = tmp.get("Test Case Name");
            String uri = tmp.get("URI");
            String method = tmp.get("Method");
            String input = tmp.get("Input");
            String output = tmp.get("Output");
            String statusCode = tmp.get("StatusCode");

            System.out.println("---------");
            System.out.println("TC Name is: " + tcName);
            System.out.println("URL is : " + host + uri);
            System.out.println("Input param is : " + input);
            System.out.println("Request method is : " + method);
            System.out.println("Expected response is : " + output);
            System.out.println("Expected responseCode is : " + statusCode);

            Response resp = null;
            if(method.equalsIgnoreCase("GET")) {
                RequestSpecification request;
                request = given().param(input);
                resp = request.when().get(host + uri);
            }else if (method.equalsIgnoreCase("POST")) {
                resp = given().contentType("application/json;charset=UTF-8").body(input).post(host + uri);
            }
            if (resp != null) {
                System.out.println(resp.prettyPrint());
            }
        }
    }
}
