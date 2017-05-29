package com.plalab.domain.model;

import com.plalab.api.request.MemberRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity //
@Table  //DDL
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "hiddenBuilder")
public class Member {
    @Id @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;

    public static MemberBuilder builder(MemberRequest memberRequest){
        return hiddenBuilder()
                .name(memberRequest.getName())
                .age(memberRequest.getAge());
    }
}