package com.moodys.ma.ds.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Record {



    private Header header;
    private Map<String, Object> entityInfo;
    private List<Metadata> metadata;
    private List<Map<String, Object>> names;
    private List<Map<String, Object>> addresses;
    private List<Map<String, Object>> identifiers;
    private List<Map<String, Object>> digitalPresences;
    private List<Map<String, Object>> activityDescriptions;
    private List<Map<String, Object>> industryActivityCodes;
    private List<Map<String, Object>> companyStatuses;
    private List<Map<String, Object>> activityOverviews;
}
