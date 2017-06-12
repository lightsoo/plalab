package com.plalab.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plalab.api.MemberController;
import com.plalab.api.request.MemberRequest;
import com.plalab.api.response.MemberResponse;
import com.plalab.domain.model.Member;
import com.plalab.domain.model.support.InvalidArgumentException;
import com.plalab.domain.model.support.MemberNotFound;
import com.plalab.domain.service.MemberService;
import com.plalab.testmother.MemberTestMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService service;

    public static final String baseUrl = "/members";

    private Integer testId;
    private MemberRequest memberRequest;
    private Member member;
    private MemberResponse memberResponse;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        testId = 999;
        memberRequest = MemberTestMother.createMemberRequest("김경수", 27);
        member = Member.builder(memberRequest).build();
        memberResponse = MemberResponse.newInstance(member);
        mapper = new ObjectMapper();
    }


    @Test
    public void getMember() throws Exception {

        given(service.getMember(anyInt())).willReturn(member);

        mvc.perform(get(baseUrl + "/{id}", testId))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(member.getId())))
                .andExpect(jsonPath("$.name", is(member.getName())))
                .andExpect(jsonPath("$.age", is(member.getAge())))
                .andExpect(status().isOk());

        verify(service, times(1)).getMember(anyInt());
    }

    @Test
    public void getMember404() throws Exception {
        given(service.getMember(anyInt())).willThrow(MemberNotFound.class);

        mvc.perform(get(baseUrl + "/{id}", testId))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).getMember(anyInt());
    }

    @Test
    public void createMember() throws Exception {
        given(service.createMember(any(Member.class))).willReturn(member);

        mvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(memberRequest)))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(memberResponse.getId())))
                .andExpect(jsonPath("$.name", is(memberResponse.getName())))
                .andExpect(jsonPath("$.age", is(memberResponse.getAge())))
                .andExpect(status().isCreated());

        verify(service, times(1)).createMember(any(Member.class));
    }

    @Test
    public void createMember400() throws Exception {
        given(service.createMember(any(Member.class))).willThrow(InvalidArgumentException.class);
        memberRequest.setAge(null);

        mvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(memberRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).createMember(any(Member.class));
    }


//    @Test
//    public void createMember409() throws Exception {
//        given(service.createMember(any(Member.class))).willThrow(MemberNotFound.class);
//        memberRequest.setAge(null);
//
//        mvc.perform(post("/members")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(memberRequest)))
//                .andDo(print())
//                .andExpect(status().isConflict());
//
//        verify(service, times(0)).createMember(any(Member.class));
//    }


}
