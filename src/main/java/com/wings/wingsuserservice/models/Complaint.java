package com.wings.wingsuserservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private ComplaintType complaintType;
    private String description;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    User user;

    @ManyToOne
    @JoinColumn(name = "receiverId", referencedColumnName = "id")
    User receiver;
}
