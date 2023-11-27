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

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;

    @OneToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<Booked> bookedList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "agent_id",referencedColumnName ="id")
    private Agent agent;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    private boolean isApproved = false;

}
