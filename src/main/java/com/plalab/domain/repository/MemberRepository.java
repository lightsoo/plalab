package com.plalab.domain.repository;

import com.plalab.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findById(Integer id);
}
