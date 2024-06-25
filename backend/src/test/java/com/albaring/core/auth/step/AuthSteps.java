package com.albaring.core.auth.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.albaring.core.authentication.presentation.dto.OauthProviderCodeRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class AuthSteps {

    public static ExtractableResponse<Response> 카카오_로그인_요청(OauthProviderCodeRequest request) {
        return given().log().all()
            .body(request)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/login/{oauth_provider}", "kakao")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    public static void 실패하는_로그인_요청() {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/login/kakao")
            .then().log().all()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .extract();
    }

    public static void 토큰_확인(ExtractableResponse<Response> 카카오_로그인_요청_응답) {
        String 발급된_액세스_토큰 = 카카오_로그인_요청_응답.jsonPath().getString("accessToken");
        assertThat(발급된_액세스_토큰).isNotBlank();

        String 발급된_리프레시_토큰 = 카카오_로그인_요청_응답.cookie("refresh-token");
        assertThat(발급된_리프레시_토큰).isNotBlank();
    }
}
