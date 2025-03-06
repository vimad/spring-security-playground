package com.example.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecureResource {
    private String resourceId;
    private String resourceName;
    private String owner;
}
