package com.albaring.core.member.application;

import com.albaring.core.member.domain.Member;
import com.albaring.core.member.domain.MemberRepository;
import com.albaring.core.member.domain.MemberStatus;
import com.albaring.core.member.domain.OAuthProviderType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findOrCreateMember(String socialId, OAuthProviderType type) {
        return memberRepository.findBySocialIdAndOAuthProviderType(socialId, type)
            .orElseGet(() -> {
                Member newMember = new Member(String.valueOf(socialId), type,
                    MemberStatus.ACTIVE);
                memberRepository.save(newMember);
                return newMember;
            });
    }
}
