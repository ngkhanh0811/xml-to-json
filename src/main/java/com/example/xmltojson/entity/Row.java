package com.example.xmltojson.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

public class Row {
    @JacksonXmlProperty(localName = "Field1")
    private String field1;

    @JacksonXmlProperty(localName = "Field2")
    private String field2;
    public Row(){};
}
