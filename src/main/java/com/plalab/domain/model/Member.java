package com.plalab.domain.model;

import com.plalab.api.request.MemberRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
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

    public static class MemberBuilder{
        public MemberBuilder name(String name){
            if(name != null){
                this.name = name;
            }
            return this;
        }
        public MemberBuilder age(Integer age){
            if(age != null){
                this.age = age;
            }
            return this;
        }
    }

    public static MemberBuilder builder(MemberRequest memberRequest){
        return hiddenBuilder()
                .name(memberRequest.getName())
                .age(memberRequest.getAge());
    }

    public static MemberBuilder builder(Member body, Member origin){
        return hiddenBuilder().id(origin.getId())
                .name(origin.getName())
                .age(origin.getAge())
                .name(body.getName())
                .age(body.getAge());
    }

}