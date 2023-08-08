package com.rh.vehicle.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "categoryPermis")
public class CategoryPermis {
    @Id
    private String id;


    @Field("categoryPermis")
    private String categoryPermis;
}
