package com.qa.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUsingRestAssured {
	
	@Test
	public void getUsingRest(){
		given().
		when().
		get("https://reqres.in/api/users").
		then().
		assertThat().
		statusCode(200).
		and().
		body("data.id",hasSize(3)).
		and().
		header("content-type", "application/json; charset=utf-8");			
		
				
		
	}

}
