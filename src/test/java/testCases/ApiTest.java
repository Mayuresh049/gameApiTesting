package testCases;





import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


public class ApiTest {
	
//TestCase for GET Method	
@Test(priority=1)	

public void test_Get() {
	
	given()
	
	.when()
	  .get("http://localhost:8080/app/videogames")
	  .then()
	     .statusCode(200)
         .log().body();
}


@Test(priority=2)
public void test_Post() {
	
	
	HashMap data=new HashMap();
	data.put("id","18");
	data.put("name", "BasketBall");
	data.put("releaseDate", "2024-02-06T07:56:49.371Z");
	data.put( "reviewScore", "9");
	data.put("category", "Racing");
	data.put("rating", "Universal");

	Response res=
	given()
	   .contentType("application/json")
	   .body(data)
	.when()
	.post("http://localhost:8080/app/videogames")
	.then()
	.statusCode(200)
	.log().body()
	.extract().response();
	String jsonString=res.asString();
	Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
	
}
@Test(priority=3)
public void test_Getpost() {
given()
	
	.when()
	  .get("http://localhost:8080/app/videogames/18")
	  .then()
	     .statusCode(200)
	     .log().body()
         .body("videoGame.id", equalTo("18"))
         .body("videoGame.name", equalTo("BasketBall"));
}
@Test(priority=4)
public void test_Delete() {
	Response res=
given()
	
	.when()
	  .get("http://localhost:8080/app/videogames/18")
	  .then()
	     .statusCode(200)
	     .log().body()
	     .extract().response();
	String jsonString=res.asString();
	Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
}
}
