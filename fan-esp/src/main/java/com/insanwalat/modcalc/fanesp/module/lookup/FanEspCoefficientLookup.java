package com.insanwalat.modcalc.fanesp.module.lookup;

import com.opencsv.bean.CsvBindByName;

public class FanEspCoefficientLookup {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "document-related")
    private String documentRelated;

    @CsvBindByName(column = "type-name")
    private String typeName;

    @CsvBindByName(column = "document")
    private String document;

    @CsvBindByName(column = "height")
    private Integer height;

    @CsvBindByName(column = "width")
    private Integer width;

    @CsvBindByName(column = "image")
    private String image;

    @CsvBindByName(column = "table-source")
    private String tableSource;

    @CsvBindByName(column = "fixed-header-height")
    private Integer fixedHeaderHeight;

    public FanEspCoefficientLookup() {
    }

    public FanEspCoefficientLookup(String name, String documentRelated, String typeName, String document, Integer height, Integer width, String image, String tableSource, Integer fixedHeaderHeight) {
        this.name = name;
        this.documentRelated = documentRelated;
        this.typeName = typeName;
        this.document = document;
        this.height = height;
        this.width = width;
        this.image = image;
        this.tableSource = tableSource;
        this.fixedHeaderHeight = fixedHeaderHeight;
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

    public Integer getFixedHeaderHeight() {
        return fixedHeaderHeight;
    }

    public void setFixedHeaderHeight(Integer fixedHeaderHeight) {
        this.fixedHeaderHeight = fixedHeaderHeight;
    }
}
