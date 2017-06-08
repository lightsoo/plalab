package com.plalab.domain.service;

import com.plalab.api.request.MemberRequest;
import com.plalab.domain.model.Member;
import com.plalab.domain.model.support.MemberNotFound;
import com.plalab.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public Member getMember(Integer id) {
        Member member = repository.findById(id);
        if(member == null){
            throw new MemberNotFound();
        }
        return member;
    }

    @Override
    public Member createMember(Member member){
        return repository.save(member);
    }

    @Override
    public Member updateMember(Integer id, Member body) {
        Member origin = repository.findById(id);
        if(origin == null){
            throw new MemberNotFound();
        }
        Member member = Member.builder(body, origin).build();
        return repository.save(member);
    }

    @Override
    public void deleteMember(Integer id) {
        repository.delete(id);
    }
}
