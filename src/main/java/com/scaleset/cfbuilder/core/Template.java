package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"AWSTemplateFormatVersion", "Description", "Parameters", "Mappings", "Resources", "Outputs"})
public class Template {

    @JsonProperty("AWSTemplateFormatVersion")
    private String version = "2010-09-09";

    private String description = "";

    private Map<String, Resource> resources = new HashMap<>();

    private Map<String, Parameter> parameters = new HashMap<>();

    private Map<String, Output> outputs = new HashMap<>();

    public Template() {
    }

    public static JsonNode json(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json);
    }

    public Fn fnGetAtt(String resource, String attribute) {
        return new Fn("GetAtt", resource, attribute);
    }

    public Map<String, Output> getOutputs() {
        return outputs;
    }

    public Map<String, Parameter> getParameters() {
        return parameters;
    }

    public Resource getResource(String id) {
        return resources.get(id);
    }

    public Map<String, Resource> getResources() {
        return resources;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Parameter numParam(String id) {
        Parameter result = new Parameter().id(id).type("Number");
        parameters.put(id, result);
        return result;
    }

    public Output output(String id, Object value, String description) {
        Output result = new Output(id, value, description);
        outputs.put(id, result);
        return result;
    }

    public Ref ref(String id) {
        return new Ref(id);
    }

    public Template resource(String id, Resource resource) {
        resources.put(id, resource);
        return this;
    }

    public <T extends Resource> T resource(Class<T> type, String id) throws Exception {
        ResourceInvocationHandler<T> proxy = new ResourceInvocationHandler(type, id);
        T result = proxy.proxy();
        resources.put(id, result);
        return result;
    }

    public Parameter strParam(String id) {
        Parameter result = new Parameter().id(id).type("String");
        parameters.put(id, result);
        return result;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Template parse(InputStream in) throws IOException {
        return this;
    }
}
