package com.moodys.ma.ds.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.BsonDateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
public class MetadataItem {

    private String source;
    private LocalDateTime acquiredDateTime;
    private String lineageID;
    private LocalDateTime updateDateTime;
    private String updateLineageId;
    private LocalDateTime confirmDateTime;
    private String confirmLineageId;
    private byte[] itemObjectHash;

    public void setUpdateDateTime(Object date) {
        if (date instanceof BsonDateTime)
            this.updateDateTime =
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(((BsonDateTime) date).getValue()), ZoneId.systemDefault());
    }
}
