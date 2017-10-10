package com.f6car.magic;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by duyingying on 2017/9/30.
 */
public class EngineDetail extends Model<CarModel> {

    private String brandName;
    private String company;
    private String typeName;
    private String exhaustVolume;
    private String engineType;
    private String engineYear;
    private String productsType;
    private String productsNo;
    private String productsYear;
    private String remark;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getExhaustVolume() {
        return exhaustVolume;
    }

    public void setExhaustVolume(String exhaustVolume) {
        this.exhaustVolume = exhaustVolume;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineYear() {
        return engineYear;
    }

    public void setEngineYear(String engineYear) {
        this.engineYear = engineYear;
    }

    public String getProductsType() {
        return productsType;
    }

    public void setProductsType(String productsType) {
        this.productsType = productsType;
    }

    public String getProductsNo() {
        return productsNo;
    }

    public void setProductsNo(String productsNo) {
        this.productsNo = productsNo;
    }

    public String getProductsYear() {
        return productsYear;
    }

    public void setProductsYear(String productsYear) {
        this.productsYear = productsYear;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EngineDetail{");
        sb.append("brandName='").append(brandName).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append(", exhaustVolume='").append(exhaustVolume).append('\'');
        sb.append(", engineType='").append(engineType).append('\'');
        sb.append(", engineYear='").append(engineYear).append('\'');
        sb.append(", productsType='").append(productsType).append('\'');
        sb.append(", productsNo='").append(productsNo).append('\'');
        sb.append(", productsYear='").append(productsYear).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
