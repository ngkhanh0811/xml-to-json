package com.example.xmltojson.Controller;

import com.example.xmltojson.entity.Row;
import com.example.xmltojson.entity.Rows;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class XmlToJSONController {

    private String data = "<Rows><Row><Field1>value1</Field1><Field2>value2</Field2></Row><Row><Field1>value3</Field1><Field2>value4</Field2></Row></Rows>";

    @PostMapping("/parserXmlToJSON")
    public ResponseEntity<?> parserXmlToJSON() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

        List<Row> rows = xmlMapper.readValue(data, new TypeReference<List<Row>>(){});

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String json = mapper.writeValueAsString(rows);

        return ResponseEntity.ok(json);
    }
}
