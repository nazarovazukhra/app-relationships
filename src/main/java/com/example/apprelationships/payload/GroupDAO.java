package com.example.apprelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDAO {
    private String groupName;
    private String facultyName;
    private String universityName;
    private Integer addressId;
}
