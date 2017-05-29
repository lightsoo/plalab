package com.plalab.api.response;

import com.plalab.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Integer id;
    private String name;
    private Integer age;

    public static MemberResponse newInstance(Member member){
        return new MemberResponse(member.getId(), member.getName(), member.getAge());
    }
}
