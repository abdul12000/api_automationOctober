package packageForUsingRestAssured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UsingRestAssured {
    @Test
    //Http Get method
    public void getASocialNetworkComment(){
        //given part is for the headers
        //when part is for the endpoint and the http methods
        //then is for the response
        given().contentType(ContentType.JSON).log().all().
                when().get("https://jsonplaceholder.typicode.com/comments/15").
                then().statusCode(200).log().all().
                body("name", is("debitis magnam hic odit aut ullam nostrum tenetur")).
                body("email", is("Maynard.Hodkiewicz@roberta.com")).
                body("body", is ("nihil ut voluptates blanditiis autem odio dicta rerum\nquisquam saepe et est\nsunt quasi nemo laudantium deserunt\nmolestias tempora quo quia"));

    }

@Test
    //Http post method
    public void PostASocialNetworkComment(){
        //given part is for the headers and body
        //when part is for the endpoint and the http methods
        //then is for the response
        HashMap<String, String> PostRequestBody = new HashMap<>();
        PostRequestBody.put("postId", "4");
        PostRequestBody.put("name","Comment on picture that I Like");
        PostRequestBody.put("email", "lateef.abdulsalam@ayana.info");
        PostRequestBody.put("body", "I like this... s");


        given().contentType(ContentType.JSON).log().all().with().body(PostRequestBody).
                when().post("https://jsonplaceholder.typicode.com/comments").
                then().statusCode(201).log().all().
                body("name", is("Comment on picture that I Like")).
                body("email", is("lateef.abdulsalam@ayana.info")).
                body("body", is ("I like this... s"));

    }

}
