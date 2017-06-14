package com.plalab.api.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRequest {
    private String name;
    private Integer age;

    public static class Builder{
        private String name;
        private Integer age;

        private Builder(String name, Integer age){
            this.name = name;
            this.age = age;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(Integer age){
            this.age = age;
            return this;
        }

        public static Builder newBuilder(String name, Integer age){
            return new Builder(name, age);
        }

        public static Builder newBuilder(MemberRequest memberRequest){
            return new Builder(memberRequest.getName(), memberRequest.getAge());
        }

        public MemberRequest build() {
            if(this.name == null){
                throw new IllegalStateException("Name of MemberRequest is required field");
            }
            if(this.age == null){
                throw new IllegalStateException("Age of MemberRequest is required field");
            }

            return new MemberRequest(this.name, this.age);
        }
    }

}
