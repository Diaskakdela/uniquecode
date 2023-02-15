package kz.geekprtnrs.queueinterviewtask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UniqueCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String code;
}
