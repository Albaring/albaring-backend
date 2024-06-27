package com.albaring.core.auth.application;

import static com.albaring.core.auth.fixture.NaverMemberFixture.모든_네이버_회원_가져오기;
import static org.assertj.core.api.Assertions.assertThat;

import com.albaring.common.util.ApplicationTest;
import com.albaring.common.util.WebClientUtil;
import com.albaring.core.authentication.application.OauthUserProfile;
import com.albaring.core.authentication.application.naver.NaverOauthProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Naver Oauth Provider 테스트")
public class NaverOauthProviderTest extends ApplicationTest {

    @Autowired
    NaverOauthProvider naverOauthProvider;

    @Autowired
    WebClientUtil webClientUtil;

    @Nested
    class 네이버_인가_코드로_네이버_프로필_요청 {

        @Test
        void 성공() {
            모든_네이버_회원_가져오기().forEach(네이버_회원 -> {
                // when
                OauthUserProfile 네이버에서_발급한_토큰
                    = naverOauthProvider.getUserProfile(네이버_회원.인가_코드);

                // then
                assertThat(네이버에서_발급한_토큰.getSocialId()).isEqualTo(네이버_회원.네이버_회원_번호.toString());
            });

        }
    }
}
