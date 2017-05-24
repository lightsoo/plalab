package com.plalab.domain.service;

import com.plalab.domain.model.Member;
import com.plalab.domain.model.support.MemberNotFound;

public interface MemberService {
    Member getMember(Integer id) throws MemberNotFound;
    Member postMember(Member member);

}
