package com.moodys.ma.ds.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Document(collection = "legalentities-firmographics")
public class LegalEntitiesRecord {

    @Field("_id")
    private String _id;
    private Object k;
    private long a;
    private Object d;
}
