package com.scaleset.cfbuilder.ec2.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * ATM only supports Strings as content, no source url
 */
@JsonPropertyOrder({"content", "encoding", "mode", "owner", "group"})
public class CFNFile {

    @JsonProperty("content")
    public String content;

    @JsonProperty("encoding")
    public String encoding;

    @JsonProperty("mode")
    public String mode;

    @JsonProperty("owner")
    public String owner;

    @JsonProperty("group")
    public String group;

    //TODO add: source(source and content exclude each other)

    @JsonIgnore
    public String id;

    /**
     * The id is the path where the file will be stored
     *
     * @param id
     */
    public CFNFile(String id) {
        this.id = id;
    }

    public CFNFile setContent(String content) {
        this.content = content;
        return this;
    }

    public CFNFile setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public CFNFile setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public CFNFile setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public CFNFile setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getId() {
        return this.id;
    }
}
