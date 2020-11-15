package es.arf.mca.tfm.apimanager;

import static org.hamcrest.CoreMatchers.equalTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import es.arf.mca.tfm.apimanager.utils.Status;
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
		given().
			pathParam("symbol", "TSLA").
		when().
			get("/api/symbols/{symbol}").
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
		given().
			pathParam("symbol", "TSLA").
			pathParam("id", 1).
		when().
			get("/api/symbols/{symbol}/results/{id}").
		then().
			assertThat().
			statusCode(200).
			and().
			body("status",equalTo(Status.FINISHED.name()));
	}

	@Test
	public void getResultNotFound() {
		given().
			pathParam("symbol", "AMZN").
			pathParam("id", 1).
		when().
			get("/api/symbols/{symbol}/results/{id}").
		then().
			assertThat().
			statusCode(404);
	}
}
