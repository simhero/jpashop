package com.example.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@DiscriminatorValue("M")
public class Movie extends Item {
    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id;
}
