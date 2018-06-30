package com.insanwalat.modcalc.fanesp.module.response;

public class FanEspCoefficientLookupResponse {

    private String name;
    private String documentRelated;
    private String typeName;
    private String document;
    private Integer height;
    private Integer width;
    private String image;

    public FanEspCoefficientLookupResponse() {
    }

    public FanEspCoefficientLookupResponse(String name, String documentRelated, String typeName, String document, Integer height, Integer width, String image) {
        this.name = name;
        this.documentRelated = documentRelated;
        this.typeName = typeName;
        this.document = document;
        this.height = height;
        this.width = width;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentRelated() {
        return documentRelated;
    }

    public void setDocumentRelated(String documentRelated) {
        this.documentRelated = documentRelated;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
