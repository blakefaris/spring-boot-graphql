package com.blakefaris.graphql.docs;

import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;

public class Descriptors {

    protected static FieldDescriptor fieldWithArrayPath(String path) {
        return fieldWithPath("[]." + path).attributes(key("arrayPath").value(path));
    }

    protected static FieldDescriptor fieldWithMapPath(String path) {
        return fieldWithPath("*." + path).attributes(key("arrayPath").value(path));
    }

    protected static FieldDescriptor subsectionWithArrayPath(String path) {
        return subsectionWithPath("[]." + path).attributes(key("arrayPath").value(path));
    }

    protected static FieldDescriptor subsectionWithMapPath(String path) {
        return subsectionWithPath("*." + path).attributes(key("arrayPath").value(path));
    }

}
