package com.plalab.api;

import com.plalab.api.request.MemberRequest;
import com.plalab.api.response.MemberResponse;
import com.plalab.domain.model.Member;
import com.plalab.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping("/members/{id}")
    public MemberResponse getMember(@PathVariable Integer id){
        return MemberResponse.newInstance(service.getMember(id));
    }

    @PostMapping("/member")
    public MemberResponse createMember(@RequestBody MemberRequest memberRequest){
//        MemberRequest validMember = MemberRequest.Builder
//                .newBuilder(memberRequest.getName(), memberRequest.getAge()).build();
        MemberRequest validMember = MemberRequest.Builder.newBuilder(memberRequest).build();

        Member member = Member.builder(validMember).build();

        return MemberResponse.newInstance(service.createMember(member));
    }

    @PutMapping("/members/{id}")
    public MemberResponse putMember(@PathVariable Integer id, @RequestBody MemberRequest body){

//        update할 필드가 필수는 아니라는 가정.
//        update할때는 RequestDTO의 required/ optional valid확인을 안한다
//        MemberRequest validMember = MemberRequest.Builder.newBuilder(body).build();

        Member member = Member.builder(body).build();

        return MemberResponse.newInstance(service.updateMember(id, member));
    }

    @DeleteMapping("/members/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOauthProvider(@PathVariable Integer id) {
        service.deleteMember(id);
    }
}