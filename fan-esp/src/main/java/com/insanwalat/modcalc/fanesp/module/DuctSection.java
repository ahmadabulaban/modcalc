package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

import java.util.List;

public class DuctSection {

    @FieldDescription(uiField = "i6", note = "Start")
    private String startPoint;

    @FieldDescription(uiField = "i7", note = "End")
    private String endPoint;

    @FieldDescription(uiField = "i8" , note = "Flow Rate")
    private Double rateInput;

    @FieldDescription(uiField = "tx2" , note = "Flow Rate Unit")
    private String rateUnit;

    @FieldDescription(uiField = "i9" , note = "Length")
    private Double lengthInput;

    @FieldDescription(uiField = "tx3" , note = "Length Unit")
    private String lengthUnit;

    @FieldDescription(uiField = "i10" , note = "Duct Shape")
    private Integer shp;

    @FieldDescription(uiField = "i11" , note = "Section Function")
    private Integer fun;

    @FieldDescription(uiField = "i12" , note = "Duct Diameter")
    private Double ductDiameterInput;

    @FieldDescription(uiField = "tx4" , note = "Duct Diameter Unit")
    private String ductDiameterUnit;

    @FieldDescription(uiField = "i13" , note = "Duct Width")
    private Double ductWidthInput;

    @FieldDescription(uiField = "tx4" , note = "Duct Width Unit")
    private String ductWidthUnit;

    @FieldDescription(uiField = "i14" , note = "Duct Height")
    private Double ductHeightInput;

    @FieldDescription(uiField = "tx4" , note = "Duct Height Unit")
    private String ductHeightUnit;

    @FieldDescription(uiField = "i15" , note = "Duct Thickness")
    private Double ductThicknessInput;

    @FieldDescription(uiField = "tx4" , note = "Duct Thickness Unit")
    private String ductThicknessUnit;

    @FieldDescription(uiField = "" , note = "Fittings")
    private List<Fitting> fittingList;

    @FieldDescription(uiField = "" , note = "Dampers & Obstructions")
    private List<DampersAndObstructions> dampersAndObstructionsList;

    @FieldDescription(uiField = "" , note = "Duct-Mounted Equipment")
    private List<DuctMountedEquipment> ductMountedEquipmentList;

    @FieldDescription(uiField = "" , note = "Special Component")
    private List<SpecialComponent> specialComponentList;

    public DuctSection() {
    }

    public DuctSection(String startPoint, String endPoint, Double rateInput, String rateUnit, Double lengthInput, String lengthUnit, Integer shp, Integer fun, Double ductDiameterInput, String ductDiameterUnit, Double ductWidthInput, String ductWidthUnit, Double ductHeightInput, String ductHeightUnit, Double ductThicknessInput, String ductThicknessUnit, List<Fitting> fittingList, List<DampersAndObstructions> dampersAndObstructionsList, List<DuctMountedEquipment> ductMountedEquipmentList, List<SpecialComponent> specialComponentList) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.rateInput = rateInput;
        this.rateUnit = rateUnit;
        this.lengthInput = lengthInput;
        this.lengthUnit = lengthUnit;
        this.shp = shp;
        this.fun = fun;
        this.ductDiameterInput = ductDiameterInput;
        this.ductDiameterUnit = ductDiameterUnit;
        this.ductWidthInput = ductWidthInput;
        this.ductWidthUnit = ductWidthUnit;
        this.ductHeightInput = ductHeightInput;
        this.ductHeightUnit = ductHeightUnit;
        this.ductThicknessInput = ductThicknessInput;
        this.ductThicknessUnit = ductThicknessUnit;
        this.fittingList = fittingList;
        this.dampersAndObstructionsList = dampersAndObstructionsList;
        this.ductMountedEquipmentList = ductMountedEquipmentList;
        this.specialComponentList = specialComponentList;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Double getRateInput() {
        return rateInput;
    }

    public void setRateInput(Double rateInput) {
        this.rateInput = rateInput;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    public Double getLengthInput() {
        return lengthInput;
    }

    public void setLengthInput(Double lengthInput) {
        this.lengthInput = lengthInput;
    }

    public String getLengthUnit() {
        return lengthUnit;
    }

    public void setLengthUnit(String lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public Integer getShp() {
        return shp;
    }

    public void setShp(Integer shp) {
        this.shp = shp;
    }

    public Integer getFun() {
        return fun;
    }

    public void setFun(Integer fun) {
        this.fun = fun;
    }

    public Double getDuctDiameterInput() {
        return ductDiameterInput;
    }

    public void setDuctDiameterInput(Double ductDiameterInput) {
        this.ductDiameterInput = ductDiameterInput;
    }

    public String getDuctDiameterUnit() {
        return ductDiameterUnit;
    }

    public void setDuctDiameterUnit(String ductDiameterUnit) {
        this.ductDiameterUnit = ductDiameterUnit;
    }

    public Double getDuctWidthInput() {
        return ductWidthInput;
    }

    public void setDuctWidthInput(Double ductWidthInput) {
        this.ductWidthInput = ductWidthInput;
    }

    public String getDuctWidthUnit() {
        return ductWidthUnit;
    }

    public void setDuctWidthUnit(String ductWidthUnit) {
        this.ductWidthUnit = ductWidthUnit;
    }

    public Double getDuctHeightInput() {
        return ductHeightInput;
    }

    public void setDuctHeightInput(Double ductHeightInput) {
        this.ductHeightInput = ductHeightInput;
    }

    public String getDuctHeightUnit() {
        return ductHeightUnit;
    }

    public void setDuctHeightUnit(String ductHeightUnit) {
        this.ductHeightUnit = ductHeightUnit;
    }

    public Double getDuctThicknessInput() {
        return ductThicknessInput;
    }

    public void setDuctThicknessInput(Double ductThicknessInput) {
        this.ductThicknessInput = ductThicknessInput;
    }

    public String getDuctThicknessUnit() {
        return ductThicknessUnit;
    }

    public void setDuctThicknessUnit(String ductThicknessUnit) {
        this.ductThicknessUnit = ductThicknessUnit;
    }

    public List<Fitting> getFittingList() {
        return fittingList;
    }

    public void setFittingList(List<Fitting> fittingList) {
        this.fittingList = fittingList;
    }

    public List<DampersAndObstructions> getDampersAndObstructionsList() {
        return dampersAndObstructionsList;
    }

    public void setDampersAndObstructionsList(List<DampersAndObstructions> dampersAndObstructionsList) {
        this.dampersAndObstructionsList = dampersAndObstructionsList;
    }

    public List<DuctMountedEquipment> getDuctMountedEquipmentList() {
        return ductMountedEquipmentList;
    }

    public void setDuctMountedEquipmentList(List<DuctMountedEquipment> ductMountedEquipmentList) {
        this.ductMountedEquipmentList = ductMountedEquipmentList;
    }

    public List<SpecialComponent> getSpecialComponentList() {
        return specialComponentList;
    }

    public void setSpecialComponentList(List<SpecialComponent> specialComponentList) {
        this.specialComponentList = specialComponentList;
    }
}
