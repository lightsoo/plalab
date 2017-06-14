package com.plalab.testmother;

import com.plalab.api.request.MemberRequest;

public class MemberTestMother {
    public static MemberRequest createMemberRequest(String name, Integer age){
        return MemberRequest.Builder.newBuilder(name, age).build();
    }

}
