package com.example.model;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@ToString

@Data

@Entity

@Table(name = "[ticket]")

public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private int priority;

    private String status;

    private String subject;

    private String created;

    @Column(unique = true, nullable = false)
    private String username;

    private String query;

    private Integer assigneeId;

  //  @ElementCollection(fetch = FetchType.EAGER)
  //  @CollectionTable(name = "user_contacts",
   //         joinColumns = @JoinColumn(name = "user_id"))
    private String contacts;


}
