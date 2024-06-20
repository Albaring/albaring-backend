package com.albaring.core.member.application;

import com.albaring.core.member.domain.Member;
import com.albaring.core.member.domain.MemberRepository;
import com.albaring.core.member.domain.MemberStatus;
import com.albaring.core.member.domain.MemberType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member findOrCreateMemberByKakaoId(Long kakaoId) {
    return memberRepository.findBySocialIdAndMemberType(String.valueOf(kakaoId), MemberType.KAKAO)
        .orElseGet(() -> {
          Member newMember = new Member(String.valueOf(kakaoId), MemberType.KAKAO,
              MemberStatus.ACTIVE);
          memberRepository.save(newMember);
          return newMember;
        });
  }
}
