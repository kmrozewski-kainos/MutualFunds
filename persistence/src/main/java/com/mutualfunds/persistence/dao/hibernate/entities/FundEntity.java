package com.mutualfunds.persistence.dao.hibernate.entities;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.mutualfunds.persistence.domain.FundType;

@Entity
@Table(name = "fund")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "getFunds", query = "select f from Fund f"),

})
public class FundEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Enumerated(STRING)
    private FundType type;
}
