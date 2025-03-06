package com.example.authorization.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "secure_resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecureResource {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Use UUID strategy
    @Column(name = "resource_id", nullable = false, updatable = false)
    private String resourceId;

    @Column(name = "resource_name", nullable = false)
    private String resourceName;

    @Column(name = "owner", nullable = false)
    private String owner;
}
