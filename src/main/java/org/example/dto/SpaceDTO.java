package org.example.dto;


import java.time.LocalDateTime;


public class SpaceDTO {


    private Long id;


    private String name;


    private String type;


    private String description;


    private LocalDateTime createTime;



    public SpaceDTO(
            Long id,
            String name,
            String type,
            String description,
            LocalDateTime createTime
    ){

        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.createTime = createTime;

    }



    public Long getId(){
        return id;
    }


    public String getName(){
        return name;
    }


    public String getType(){
        return type;
    }


    public String getDescription(){
        return description;
    }


    public LocalDateTime getCreateTime(){
        return createTime;
    }

}