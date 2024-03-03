package com.example.xmltojson.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "Rows")
@Data
@XmlRootElement
public class Rows {
    @JacksonXmlProperty(localName = "Row")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Row> rows;

    public List<Row> getRows(){
        return this.rows;
    }
}
