package org.example.controller;


import jakarta.validation.Valid;
import org.example.dto.CreateSpaceDTO;
import org.example.dto.SpaceDTO;
import org.example.entity.ResponseResult;
import org.example.entity.Space;
import org.example.service.SpaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class SpaceController {


    private final SpaceService spaceService;


    public SpaceController(
            SpaceService spaceService
    ){

        this.spaceService = spaceService;

    }



    /**
     * 查询当前用户拥有的空间
     */
    @GetMapping("/spaces")
    public ResponseResult<List<SpaceDTO>> list(){


        List<SpaceDTO> spaces =
                spaceService.listMySpaces();



        return new ResponseResult<>(
                200,
                "查询成功",
                spaces
        );

    }

    @PostMapping("/spaces")
    public ResponseResult<SpaceDTO> create(
            @Valid
            @RequestBody CreateSpaceDTO dto
    ){


        Space space =
                spaceService.createTeamSpace(dto);



        SpaceDTO spaceDTO =
                new SpaceDTO(
                        space.getId(),
                        space.getName(),
                        space.getType(),
                        space.getDescription(),
                        space.getCreateTime()
                );



        return new ResponseResult<>(
                200,
                "创建成功",
                spaceDTO
        );

    }


}