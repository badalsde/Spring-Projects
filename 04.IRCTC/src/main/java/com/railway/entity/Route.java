package com.railway.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "route")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String source;
    private String destination;
    @ElementCollection
    @CollectionTable(name = "route_trainList", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "train_id")
    private List<Train> trainList;
}
