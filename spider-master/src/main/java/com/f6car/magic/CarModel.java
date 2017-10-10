package com.f6car.magic;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by D_YY on 2017/9/18.
 */
public class CarModel extends Model<CarModel> {
    private String supplier;
    private String subNo;
    private String car;
    private String driveForm;
    private String products;
    private String productsName;

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getDriveForm() {
        return driveForm;
    }

    public void setDriveForm(String driveForm) {
        this.driveForm = driveForm;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarModel{");
        sb.append("supplier='").append(supplier).append('\'');
        sb.append(", subNo='").append(subNo).append('\'');
        sb.append(", car='").append(car).append('\'');
        sb.append(", driveForm='").append(driveForm).append('\'');
        sb.append(", products='").append(products).append('\'');
        sb.append(", productsName='").append(productsName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
