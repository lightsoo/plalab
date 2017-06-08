package com.plalab.api.v1;

import com.plalab.api.request.MemberRequest;
import com.plalab.domain.model.Member;
import com.plalab.domain.service.MemberService;
import com.plalab.testmother.MemberTestMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(MemberController.class)
public class MemberController {

    @Autowired
    MockMvc mvc;
    @MockBean
    MemberService service;

    private Integer testId;
    private MemberRequest memberRequest;
    private Member member;

    @Before
    public void setUp() throws Exception {
        testId = 999;
        memberRequest = MemberTestMother.createMemberRequest("김경수", 27);
        member = Member.builder(memberRequest).build();
    }


    @Test
    public void getMember() throws Exception {
        given(service.getMember(anyInt())).willReturn(member);

        mvc.perform(get("/members/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).getMember(anyInt());

    }

//    @Test
//    public void getMember404() throws Exception {
//        given(service.getMember(anyInt())).willThrow(MemberNotFound.class);
//
//        mvc.perform(get("/members/{id}", testId))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//
//        verify(service, times(1)).getMember(anyInt());
//
//    }
}
