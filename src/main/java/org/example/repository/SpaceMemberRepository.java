package org.example.repository;


import org.example.entity.SpaceMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpaceMemberRepository
        extends JpaRepository<SpaceMember, Long> {


}