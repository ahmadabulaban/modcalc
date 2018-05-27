package com.insanwalat.modcalc.fanesp.module.input;

import java.util.List;

public class DuctSectionInput {

    private String startPoint;
    private String endPoint;
    private Double flowRateInput;
    private Double q;
    private String rateUnit;
    private Double lengthInput;
    private Double length;
    private String lengthUnit;
    private Integer shp;
    private Integer fun;
    private Double ductDiameterInput;
    private Double d;
    private String dimensionUnit;
    private Double widthInput;
    private Double w;
    private Double heightInput;
    private Double h;
    private Double thicknessInput;
    private Double t;
    private List<FittingInput> fittingInputList;
    private List<DampersAndObstructionsInput> dampersAndObstructionsInputList;
    private List<DuctMountedEquipmentInput> ductMountedEquipmentInputList;
    private List<SpecialComponentInput> specialComponentInputList;

    public DuctSectionInput() {
    }

    public DuctSectionInput(String startPoint, String endPoint, Double flowRateInput, Double q, String rateUnit, Double lengthInput, Double length, String lengthUnit, Integer shp, Integer fun, Double ductDiameterInput, Double d, String dimensionUnit, Double widthInput, Double w, Double heightInput, Double h, Double thicknessInput, Double t, List<FittingInput> fittingInputList, List<DampersAndObstructionsInput> dampersAndObstructionsInputList, List<DuctMountedEquipmentInput> ductMountedEquipmentInputList, List<SpecialComponentInput> specialComponentInputList) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.flowRateInput = flowRateInput;
        this.q = q;
        this.rateUnit = rateUnit;
        this.lengthInput = lengthInput;
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.shp = shp;
        this.fun = fun;
        this.ductDiameterInput = ductDiameterInput;
        this.d = d;
        this.dimensionUnit = dimensionUnit;
        this.widthInput = widthInput;
        this.w = w;
        this.heightInput = heightInput;
        this.h = h;
        this.thicknessInput = thicknessInput;
        this.t = t;
        this.fittingInputList = fittingInputList;
        this.dampersAndObstructionsInputList = dampersAndObstructionsInputList;
        this.ductMountedEquipmentInputList = ductMountedEquipmentInputList;
        this.specialComponentInputList = specialComponentInputList;
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

    public Double getFlowRateInput() {
        return flowRateInput;
    }

    public void setFlowRateInput(Double flowRateInput) {
        this.flowRateInput = flowRateInput;
    }

    public Double getQ() {
        return q;
    }

    public void setQ(Double q) {
        this.q = q;
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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
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

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public Double getWidthInput() {
        return widthInput;
    }

    public void setWidthInput(Double widthInput) {
        this.widthInput = widthInput;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public Double getHeightInput() {
        return heightInput;
    }

    public void setHeightInput(Double heightInput) {
        this.heightInput = heightInput;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getThicknessInput() {
        return thicknessInput;
    }

    public void setThicknessInput(Double thicknessInput) {
        this.thicknessInput = thicknessInput;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public List<FittingInput> getFittingInputList() {
        return fittingInputList;
    }

    public void setFittingInputList(List<FittingInput> fittingInputList) {
        this.fittingInputList = fittingInputList;
    }

    public List<DampersAndObstructionsInput> getDampersAndObstructionsInputList() {
        return dampersAndObstructionsInputList;
    }

    public void setDampersAndObstructionsInputList(List<DampersAndObstructionsInput> dampersAndObstructionsInputList) {
        this.dampersAndObstructionsInputList = dampersAndObstructionsInputList;
    }

    public List<DuctMountedEquipmentInput> getDuctMountedEquipmentInputList() {
        return ductMountedEquipmentInputList;
    }

    public void setDuctMountedEquipmentInputList(List<DuctMountedEquipmentInput> ductMountedEquipmentInputList) {
        this.ductMountedEquipmentInputList = ductMountedEquipmentInputList;
    }

    public List<SpecialComponentInput> getSpecialComponentInputList() {
        return specialComponentInputList;
    }

    public void setSpecialComponentInputList(List<SpecialComponentInput> specialComponentInputList) {
        this.specialComponentInputList = specialComponentInputList;
    }
}
