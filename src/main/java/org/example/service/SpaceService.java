package org.example.service;


import org.example.dto.SpaceDTO;
import org.example.entity.Space;
import org.example.exception.BusinessException;
import org.example.repository.SpaceMemberRepository;
import org.example.repository.SpaceRepository;
import org.example.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.example.dto.CreateSpaceDTO;
import org.example.entity.SpaceMember;
import java.time.LocalDateTime;

import java.util.List;


@Service
public class SpaceService {


    private final SpaceRepository spaceRepository;
    private final SpaceMemberRepository spaceMemberRepository;


    public SpaceService(
            SpaceRepository spaceRepository,
            SpaceMemberRepository spaceMemberRepository
    ){

        this.spaceRepository = spaceRepository;

        this.spaceMemberRepository = spaceMemberRepository;

    }





    public List<SpaceDTO> listMySpaces(){


        Long userId =
                SecurityUtil.getUserId();



        List<Space> spaces =
                spaceRepository.findByOwnerId(userId);



        return spaces.stream()
                .map(space ->
                        new SpaceDTO(
                                space.getId(),
                                space.getName(),
                                space.getType(),
                                space.getDescription(),
                                space.getCreateTime()
                        )
                )
                .toList();

    }

    public Space createTeamSpace(CreateSpaceDTO dto){


        Long userId = SecurityUtil.getUserId();


        if(userId == null){

            throw new BusinessException(400,"用户未登录");

        }



        //创建团队空间

        Space space = new Space();


        space.setName(
                dto.getName()
        );


        space.setDescription(
                dto.getDescription()
        );


        space.setType(
                "TEAM"
        );


        space.setOwnerId(
                userId
        );


        Space saveSpace =
                spaceRepository.save(space);



        //创建成员关系

        SpaceMember member =
                new SpaceMember();


        member.setSpaceId(
                saveSpace.getId()
        );


        member.setUserId(
                userId
        );


        member.setRole(
                "OWNER"
        );


        member.setJoinTime(
                LocalDateTime.now()
        );


        spaceMemberRepository.save(member);



        return saveSpace;

    }

}