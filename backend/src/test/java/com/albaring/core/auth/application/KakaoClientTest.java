package com.albaring.core.auth.application;

import static com.albaring.core.auth.fixture.KakaoMemberFixture.모든_카카오_회원_가져오기;
import static org.assertj.core.api.Assertions.assertThat;

import com.albaring.common.util.ApplicationTest;
import com.albaring.common.util.WebClientUtil;
import com.albaring.core.authentication.application.dto.KakaoProfileResponse;
import com.albaring.core.authentication.application.kakao.KakaoClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Kakao Client 테스트")
public class KakaoClientTest extends ApplicationTest {

    @Autowired
    KakaoClient kakaoClient;

    @Autowired
    WebClientUtil webClientUtil;

    @Nested
    class 카카오_인가_코드로_카카오_프로필_요청 {

        @Test
        void 성공() {
            모든_카카오_회원_가져오기().forEach(카카오_회원 -> {
                // when
                KakaoProfileResponse 카카오에서_발급한_토큰
                    = kakaoClient.requestKakaoProfile(카카오_회원.인가_코드);

                // then
                assertThat(카카오에서_발급한_토큰.getId()).isEqualTo(카카오_회원.카카오_회원_번호);
            });

        }
    }
}
