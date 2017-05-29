package com.plalab.api;

import com.plalab.api.request.MemberRequest;
import com.plalab.api.response.MemberResponse;
import com.plalab.domain.model.Member;
import com.plalab.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    private MemberService service;

    @GetMapping("/member/{id}")
    public MemberResponse getMember(@PathVariable Integer id){
        return MemberResponse.newInstance(service.getMember(id));
    }

    @PostMapping("/member")
    public MemberResponse postMember(@RequestBody MemberRequest memberRequest){
        MemberRequest validMember = MemberRequest.Builder
                .newBuilder(memberRequest.getName(), memberRequest.getAge()).build();
        Member member = Member.builder(validMember).build();
        return MemberResponse.newInstance(service.postMember(member));
    }

}
