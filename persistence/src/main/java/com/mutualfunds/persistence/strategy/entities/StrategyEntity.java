package com.mutualfunds.persistence.strategy.entities;

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
@Table(name = "strategy")
@NamedQueries({
    @NamedQuery(name = "getInvestmentStrategiesByName", query = "select i from StrategyEntity i where i.name = :styleType"),
    @NamedQuery(name = "getAllStrategyNames", query = "select distinct i.name from StrategyEntity i")
})
public class StrategyEntity implements Serializable {

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
