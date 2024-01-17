package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
    private String propertyName;

    @Column(nullable = false, length = 200)
    private Double price;

    @Column
    private String photo;

    @OneToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "property")
    private List<Booked> bookedList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id",referencedColumnName ="id")
    private Agent agent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    private boolean isApproved = false;

}
