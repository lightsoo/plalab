package com.plalab.domain.service;

import com.plalab.api.request.MemberRequest;
import com.plalab.domain.model.Member;
import com.plalab.domain.model.support.MemberNotFound;

public interface MemberService {
    Member getMember(Integer id);
    Member createMember(Member member);
    Member updateMember(Integer id, Member body);
    void deleteMember(Integer id);
}
