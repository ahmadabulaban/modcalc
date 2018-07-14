package com.insanwalat.modcalc.fanesp.module.response;

public class FanEspCoefficientLookupResponse {

    private String name;
    private String documentRelated;
    private String typeName;
    private String document;
    private Integer height;
    private Integer width;
    private String image;
    private String tableSource;
    private Integer[] fixedHeaderHeight;
    private Integer[] fixedBodyHeight;

    public FanEspCoefficientLookupResponse() {
    }

    public FanEspCoefficientLookupResponse(String name, String documentRelated, String typeName, String document, Integer height, Integer width, String image, String tableSource, Integer[] fixedHeaderHeight, Integer[] fixedBodyHeight) {
        this.name = name;
        this.documentRelated = documentRelated;
        this.typeName = typeName;
        this.document = document;
        this.height = height;
        this.width = width;
        this.image = image;
        this.tableSource = tableSource;
        this.fixedHeaderHeight = fixedHeaderHeight;
        this.fixedBodyHeight = fixedBodyHeight;
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

    public String getTableSource() {
        return tableSource;
    }

    public void setTableSource(String tableSource) {
        this.tableSource = tableSource;
    }

    public Integer[] getFixedHeaderHeight() {
        return fixedHeaderHeight;
    }

    public void setFixedHeaderHeight(Integer[] fixedHeaderHeight) {
        this.fixedHeaderHeight = fixedHeaderHeight;
    }

    public Integer[] getFixedBodyHeight() {
        return fixedBodyHeight;
    }

    public void setFixedBodyHeight(Integer[] fixedBodyHeight) {
        this.fixedBodyHeight = fixedBodyHeight;
    }
}
