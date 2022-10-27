package stepDefinitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.RequestBodyService;

import org.hamcrest.core.Is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class SocialNetworkingStepDefs extends BaseSteps{
    Response responseForGetService, responseForGetAComment, responseForPostAComment;
    RequestBodyService requestBodyService;

    @Given("service is up and running")
    public void service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        responseForGetService= getCall();
//        getCall();
//      responseForGetService = getResponse();
      assertThat(responseForGetService.statusCode(), Is.is(200));

    }
    @When("i search for {string} of a comment with a GET method")
    public void i_search_for_of_a_comment_with_a_get_method(String id) {
        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint + id);
        responseForGetAComment=getCall();
//        getCall();
//        responseForGetAComment = getResponse();


    }
    @Then("i should get the correct {string}, {string}, {string} and {string} returned with status code of {int}")
    public void i_should_get_the_correct_and_returned_with_status_code_of(String id, String name, String email, String body, Integer sCode) {
        assertThat(responseForGetAComment.statusCode(), Is.is(sCode));
        assertThat(responseForGetAComment.body().jsonPath().get("id"),  Is.is (Integer.parseInt(id)));
        assertThat(responseForGetAComment.body().jsonPath().get("name"), is(equalTo(name)));
        assertThat(responseForGetAComment.body().jsonPath().get("email"), is(equalTo(email)));
        assertThat(responseForGetAComment.body().jsonPath().get("body"), is(equalTo(body)));
    }

    @When("I create a new comment with the following details {string},{string}, {string} and {string},")
    public void iCreateANewCommentWithTheFollowingDetailsAnd(String postId, String name, String email, String body) {
        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint);
        requestBodyService = new RequestBodyService();
        DocumentContext requestBody = loadJsonTemplate(MakeACommentPayload);
        requestBodyService.setRequestBodyForComment(requestBody, postId, name, email, body);


        responseForPostAComment=getPostCall();
//        getPostCall();
//        responseForPostAComment = getResponse();
    }

    @Then("i should get the correct {string},{string}, {string} and {string} returned and with status code of {int}")
    public void iShouldGetTheCorrectAndReturnedAndWithStatusCodeOf(String postId, String name, String email, String body, int sCode) {
        assertThat(responseForPostAComment.statusCode(), Is.is(sCode));
        assertThat(responseForPostAComment.body().jsonPath().get("postId"),  is(postId));
        assertThat(responseForPostAComment.body().jsonPath().get("name"), is(equalTo(name)));
        assertThat(responseForPostAComment.body().jsonPath().get("email"), is(equalTo(email)));
        assertThat(responseForPostAComment.body().jsonPath().get("body"), is(equalTo(body)));
    }
}
