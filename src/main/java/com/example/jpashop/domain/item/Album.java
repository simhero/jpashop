package com.example.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter @Getter
@DiscriminatorValue("A")
public class Album extends Item {
    @Id
    @GeneratedValue
    @Column(name = "album_id")
    private Long id;


}
