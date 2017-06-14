package com.plalab.domain.model.support;


public class MemberNotFound extends NotFoundException{

    public MemberNotFound(){super("Member is Not Found.");}

    public MemberNotFound(String msg) {
        super(msg);
    }
}
