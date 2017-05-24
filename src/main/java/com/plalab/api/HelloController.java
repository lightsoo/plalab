package com.plalab.api;

import com.plalab.domain.model.Member;
import com.plalab.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    private MemberService service;

    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable Integer id){
        return service.getMember(id);
    }
    @PostMapping("/member")
    public Member postMember(@RequestBody Member member){
        return service.postMember(member);
    }

}
