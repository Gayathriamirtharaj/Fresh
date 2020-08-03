package com.example.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class Ticket
{
    private Integer id;
    private String email;
    private String priority;
    private Integer status;
    private Integer username;
    private String query;
    private String contact;
    private Integer assigneeId;


}
