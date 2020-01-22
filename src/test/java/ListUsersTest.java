import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class ListUsersTest {
    Properties env_properties = new Properties();

    @BeforeTest(groups = { "regression"})
    public void getData() throws IOException {
        FileInputStream inputStream = new FileInputStream("..//demo//src//test//java//env.properties");
        env_properties.load(inputStream);
    }

    @Test(groups = { "regression"})
    public void get_user_list() {
        Response response = null;

        try {
            RestAssured.baseURI = env_properties.getProperty("HOST");
            response = given().queryParam("page","2").when().
                    get(Resources.get_list_users());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(200, response.getStatusCode());
        response.then().assertThat().and()
                .body("data[0].id", equalTo(7))
                .and().body("page",equalTo(2));
    }
}
