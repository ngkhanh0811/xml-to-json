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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/")
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

    @PostMapping(value = "/parseJsonToXml")
    public ResponseEntity<?> parseJsonToXml() throws  JsonProcessingException{

        List<Row> rowsList = new ArrayList<>();
        Row row  = new Row(){{setField1("value1");
        setField2("value2");}};
        rowsList.add(row);

        Rows rows = new Rows();
        rows.setRows(rowsList);

        try {
            // Tạo đối tượng JAXBContext
            JAXBContext jaxbContext = JAXBContext.newInstance(Rows.class);

            // Tạo đối tượng Marshaller từ JAXBContext
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Chuyển đối tượng Rows thành XML và in ra màn hình
            StringWriter writer = new StringWriter();
            jaxbMarshaller.marshal(rows, writer);
            System.out.println("XML Data:");
            System.out.println(writer.toString());
            return ResponseEntity.ok(writer.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}