package com.insanwalat.modcalc.module.input;

public class DuctSizerCalcInput {

    private Integer uu;
    private Double uf;
    private String ufUnit;
    private Double temperatureInput;
    private Double temperature;
    private String temperatureUnit;
    private Double eps;
    private Double thicknessInput;
    private Double thickness;
    private String dimensionUnit;
    private Double rateInput;
    private Double q;
    private String rateUnit;
    private Integer shp;
    private Double dimensionInput;
    private Double dim1;
    private Integer sizingCriteria;
    private Double allowedPressureInput;
    private Double pMax;
    private String pressureUnit;
    private Double allowedVelocityInput;
    private Double vMax;
    private String velocityUnit;

    public DuctSizerCalcInput() {
    }

    public DuctSizerCalcInput(Integer uu, Double uf, String ufUnit, Double temperatureInput, Double temperature, String temperatureUnit, Double eps, Double thicknessInput, Double thickness, String dimensionUnit, Double rateInput, Double q, String rateUnit, Integer shp, Double dimensionInput, Double dim1, Integer sizingCriteria, Double allowedPressureInput, Double pMax, String pressureUnit, Double allowedVelocityInput, Double vMax, String velocityUnit) {
        this.uu = uu;
        this.uf = uf;
        this.ufUnit = ufUnit;
        this.temperatureInput = temperatureInput;
        this.temperature = temperature;
        this.temperatureUnit = temperatureUnit;
        this.eps = eps;
        this.thicknessInput = thicknessInput;
        this.thickness = thickness;
        this.dimensionUnit = dimensionUnit;
        this.rateInput = rateInput;
        this.q = q;
        this.rateUnit = rateUnit;
        this.shp = shp;
        this.dimensionInput = dimensionInput;
        this.dim1 = dim1;
        this.sizingCriteria = sizingCriteria;
        this.allowedPressureInput = allowedPressureInput;
        this.pMax = pMax;
        this.pressureUnit = pressureUnit;
        this.allowedVelocityInput = allowedVelocityInput;
        this.vMax = vMax;
        this.velocityUnit = velocityUnit;
    }

    public Integer getUu() {
        return uu;
    }

    public void setUu(Integer uu) {
        this.uu = uu;
    }

    public Double getUf() {
        return uf;
    }

    public void setUf(Double uf) {
        this.uf = uf;
    }

    public String getUfUnit() {
        return ufUnit;
    }

    public void setUfUnit(String ufUnit) {
        this.ufUnit = ufUnit;
    }

    public Double getTemperatureInput() {
        return temperatureInput;
    }

    public void setTemperatureInput(Double temperatureInput) {
        this.temperatureInput = temperatureInput;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getThicknessInput() {
        return thicknessInput;
    }

    public void setThicknessInput(Double thicknessInput) {
        this.thicknessInput = thicknessInput;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public Double getRateInput() {
        return rateInput;
    }

    public void setRateInput(Double rateInput) {
        this.rateInput = rateInput;
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

    public Integer getShp() {
        return shp;
    }

    public void setShp(Integer shp) {
        this.shp = shp;
    }

    public Double getDimensionInput() {
        return dimensionInput;
    }

    public void setDimensionInput(Double dimensionInput) {
        this.dimensionInput = dimensionInput;
    }

    public Double getDim1() {
        return dim1;
    }

    public void setDim1(Double dim1) {
        this.dim1 = dim1;
    }

    public Integer getSizingCriteria() {
        return sizingCriteria;
    }

    public void setSizingCriteria(Integer sizingCriteria) {
        this.sizingCriteria = sizingCriteria;
    }

    public Double getAllowedPressureInput() {
        return allowedPressureInput;
    }

    public void setAllowedPressureInput(Double allowedPressureInput) {
        this.allowedPressureInput = allowedPressureInput;
    }

    public Double getpMax() {
        return pMax;
    }

    public void setpMax(Double pMax) {
        this.pMax = pMax;
    }

    public String getPressureUnit() {
        return pressureUnit;
    }

    public void setPressureUnit(String pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public Double getAllowedVelocityInput() {
        return allowedVelocityInput;
    }

    public void setAllowedVelocityInput(Double allowedVelocityInput) {
        this.allowedVelocityInput = allowedVelocityInput;
    }

    public Double getvMax() {
        return vMax;
    }

    public void setvMax(Double vMax) {
        this.vMax = vMax;
    }

    public String getVelocityUnit() {
        return velocityUnit;
    }

    public void setVelocityUnit(String velocityUnit) {
        this.velocityUnit = velocityUnit;
    }
}
