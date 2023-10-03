package com.parkingcar.model.pakingLot;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 65535, columnDefinition = "LONGTEXT")
    private String urlImg;
    @ManyToOne
    @JoinColumn(name = "carId", referencedColumnName = "id")
    private Car car;
}
