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
    private Integer id;

    @Column(nullable = false,length =200)
    private String AgentName;

    @OneToOne
    @JoinColumn(name = "appUser_id",referencedColumnName = "id")
    private AppUser appUser;

    @OneToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "agent")
    private List<Property> propertyList= new ArrayList<>();

}
