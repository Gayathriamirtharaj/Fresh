package com.example.model;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name="agent")
@Data
@ToString
@NoArgsConstructor

public class AgentEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    private String contacts;
    private String email;


}
