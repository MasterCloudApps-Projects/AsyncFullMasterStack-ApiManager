package es.arf.mca.tfm.apimanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("REST tests")
class ApiManagerApplicationTests {
	
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
	
	@Test
	public void getStockValue() {
		when().
			get("/api/symbols/TSLA").
		then().
			assertThat().
			statusCode(200);
	}
	
	@Test
	public void getStockValueNoSymbol() {
		when().
			get("/api/symbols/").
		then().
			assertThat().
			statusCode(404);
	}
	
	@Test
	public void getResult() {
		when().
			get("/api/results/1").
		then().
			assertThat().
			statusCode(200);
	}
}
