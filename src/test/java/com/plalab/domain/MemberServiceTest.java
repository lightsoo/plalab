package com.plalab.domain;

import com.plalab.api.request.MemberRequest;
import com.plalab.domain.model.Member;
import com.plalab.domain.model.support.MemberNotFound;
import com.plalab.domain.repository.MemberRepository;
import com.plalab.domain.service.MemberService;
import com.plalab.testmother.MemberTestMother;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class MemberServiceTest {

    @MockBean
    private MemberRepository repository;

    @Autowired
    MemberService service;

    @Test
    public void testGetMember() throws Exception {
        final MemberRequest memberRequest = MemberTestMother.createMemberRequest("lightsoo",27);
        final Member expectedMember = Member.builder(memberRequest).build();
        final Integer testId = 999;
        given(repository.findById(anyInt())).willReturn(expectedMember);

        assertThat(service.getMember(testId))
                .isInstanceOf(Member.class)
                .isNotNull();
    }

    @Test(expected = MemberNotFound.class)
    public void testGetMember404() throws Exception {
        final Integer testId = 999;
        given(repository.findById(anyInt())).willReturn(null);
        service.getMember(testId);

    }

    @Test
    public void testCreateMember() throws Exception {
        final MemberRequest memberRequest = MemberTestMother.createMemberRequest("lightsoo",27);
        final Member expectedMember = Member.builder(memberRequest).build();

        given(repository.save(any(Member.class))).willReturn(expectedMember);

        assertThat(service.createMember(expectedMember))
                .isNotNull()
                .isInstanceOf(Member.class)
                .hasFieldOrPropertyWithValue("name", "lightsoo")
                .hasFieldOrPropertyWithValue("age", 27);
    }
}
