package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 200)
    private String address;

    @Column(nullable = false,length = 200)
    private String city;

    @Column(nullable = false,length = 200)
    private Long zipcode;

    @Column(nullable = false,length = 200)
    private Long contactNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Property property;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Customer customer;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Agent agent;




}
