import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class RegistrationTest {

    Properties env_properties = new Properties();
    ArrayList data = new ArrayList();

    @BeforeTest(groups = { "smoke" ,"regression"})
    public void getData() throws IOException {
        FileInputStream inputStream = new FileInputStream("..//demo//src//test//java//env.properties");
        env_properties.load(inputStream);
        DataDriven registrationData=new DataDriven();
        data=registrationData.getData("Registration"); // This will extract all the data of Registration row
                                                                   // and return an array of it.

    }

    @Test(groups = { "smoke" ,"regression"})
    public void post_registration_valid_credentials() {
        Response response = null;
        Register valid_registration = new Register();
        valid_registration.setEmail(data.get(1).toString()); // Email value from excel sheet
        valid_registration.setPassword(data.get(2).toString()); // Password from excel sheet
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json_object = mapper.writeValueAsString(valid_registration);
            RestAssured.baseURI = env_properties.getProperty("HOST");
            response = given().contentType(ContentType.JSON).body(json_object).when().
                    post(Resources.post_registration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(200, response.getStatusCode());
        response.then().assertThat().and()
                .body("id", equalTo(4)).and().
                body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .and().header("Server", equalTo(env_properties.getProperty("SERVER")));
    }

    @Test(groups = { "regression"})
    public void post_registration_unsuccessful() {
        Response response = null;
        Register invalid_registration = new Register();
        invalid_registration.setEmail("sydney@fife");
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json_object = mapper.writeValueAsString(invalid_registration);
            RestAssured.baseURI = env_properties.getProperty("HOST");
            response = given().contentType(ContentType.JSON).body(json_object).when().
                    post(Resources.post_registration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(400, response.getStatusCode());
        response.then().assertThat().and()
                .body("error", equalTo("Missing password")).and()
                .and().header("Server", equalTo(env_properties.getProperty("SERVER")));
    }

}
