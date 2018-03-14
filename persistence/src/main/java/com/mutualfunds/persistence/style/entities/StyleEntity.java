package com.mutualfunds.persistence.style.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Getter
@Setter
@Entity
@Table(name = "investment_style")
@NamedQueries({
    @NamedQuery(name = "getInvestmentStyleByName", query = "select i from StyleEntity i where i.name = :styleType")
})
public class StyleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Double share;
}
