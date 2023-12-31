package com.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length =200)
    private String CustomerName;

    @Column(nullable = false,length = 200)
    private Long contactNumber;

    @OneToOne
    @JoinColumn(name = "appUser_id",referencedColumnName = "id")
    private AppUser appUser;


}
