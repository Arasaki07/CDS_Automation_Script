package com.moodys.ma.ds.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
@Getter
@Setter
@ToString
public class Metadata {


    private String sectionID;
    private Integer version;
    private byte[] sectionHash;
    private ArrayList<MetadataItem> items;

}
