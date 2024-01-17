package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length =200)
    private String AgentName;

    @Column(nullable = false,length = 200)
    private Long contactNumber;

    @OneToOne
    @JoinColumn(name = "appUser_id",referencedColumnName = "id")
    private AppUser appUser;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "agent")
    private List<Property> propertyList= new ArrayList<>();

}
