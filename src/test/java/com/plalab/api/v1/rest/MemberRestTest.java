package com.plalab.api.v1.rest;

import com.plalab.api.request.MemberRequest;
import com.plalab.api.response.MemberResponse;
import com.plalab.domain.model.Member;
import com.plalab.domain.repository.MemberRepository;
import com.plalab.testmother.MemberTestMother;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberRestTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    MemberRepository repository;

    private final String baseUrl = "/members";

    private MemberRequest memberRequest;
    private Member member;

    @Before
    public void setUp() throws Exception {
        memberRequest = MemberTestMother.createMemberRequest("lightsoo", 27);
        member = Member.builder(memberRequest).build();
        repository.save(member);
    }

    @After
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void test1_CreateMember() throws Exception {
        ResponseEntity<MemberResponse> res = testRestTemplate.postForEntity(baseUrl, memberRequest, MemberResponse.class);

        assertThat(res.getBody()).isInstanceOf(MemberResponse.class);
        assertThat(res.getBody().getAge()).isEqualTo(memberRequest.getAge());
        assertThat(res.getBody().getName()).isEqualTo(memberRequest.getName());
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void test2_GetMember() throws Exception {

    }

    @Test
    public void test2_UpdateMember() throws Exception {

    }

    @Test
    public void test4_DeleteMember() throws Exception {

    }
}
