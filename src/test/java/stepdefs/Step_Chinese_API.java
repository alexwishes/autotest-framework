package stepdefs;

import helper.Utils;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class Step_Chinese_API{

    private Response resp;
    private RequestSpecification request;
    private String host = Utils.readProperty("config.properties", "apiTestUrl");
    private String url;

    @假如("Restful服务含有以下{string}")
    public void Restful服务含有以下(String uri){
        url = host + uri;
    }

    @当("我用{string}发起{string}请求")
    public void 我用_发起_请求(String input, String method){
        if(method.equalsIgnoreCase("GET")) {
            RequestSpecification request;
            request = given().param(input);
            resp = request.when().get(url);
        }else if (method.equalsIgnoreCase("POST")) {
            resp = given().contentType("application/json;charset=UTF-8").body(input).post(url);
        }
    }

    @那么("我应该能够得到{string}")
    public void 我应该能够得到(String statusCode){
        resp.then().statusCode(Integer.parseInt(statusCode));
    }
}