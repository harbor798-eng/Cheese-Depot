package org.example.repository;


import org.example.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SpaceRepository
        extends JpaRepository<Space, Long> {


    List<Space> findByOwnerId(Long ownerId);


}