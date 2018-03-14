package com.mutualfunds.persistence.fund;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

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
@Table(name = "fund")
@NamedQueries({
    @NamedQuery(name = "getFunds", query = "select f from FundEntity f")
})
public class FundEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String type;
}
