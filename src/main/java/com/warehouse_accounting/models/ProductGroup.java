package com.warehouse_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product_group")
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "sort_number")
    private String sortNumber;

    @ManyToOne
    @JoinColumn(name = "parent_product_group_id")
    private ProductGroup productGroup;
}