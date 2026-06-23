// src/java/src/main/java/com/example/api/controller/XmlController.java
// PASO 6: XXE (XML External Entity) — DocumentBuilderFactory con secure processing

package com.example.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
@RestController
@RequestMapping("/api/xml")
public class XmlController {

// CODIGO SEGURO
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

// Deshabilitar DOCTYPE completamente (opcion mas segura)
factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

// Por si acaso: deshabilitar entidades externas generales y de parametro
factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

// Deshabilitar carga de DTD externas y XInclude
factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
factory.setXIncludeAware(false);
factory.setExpandEntityReferences(false);

DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new InputSource(new StringReader(xml)));
