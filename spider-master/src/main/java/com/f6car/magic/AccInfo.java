package com.f6car.magic;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by D_YY on 2017/9/18.
 */
public class AccInfo extends Model<AccInfo> {
    private String oeBrand;
    private String oeNo;
    private String car;
    private String startYear;
    private String stopYear;
    private String accStartYear;
    private String accStopYear;
    private String kw;
    private String hp;

    public String getOeBrand() {
        return oeBrand;
    }

    public void setOeBrand(String oeBrand) {
        this.oeBrand = oeBrand;
    }

    public String getOeNo() {
        return oeNo;
    }

    public void setOeNo(String oeNo) {
        this.oeNo = oeNo;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getStopYear() {
        return stopYear;
    }

    public void setStopYear(String stopYear) {
        this.stopYear = stopYear;
    }

    public String getAccStartYear() {
        return accStartYear;
    }

    public void setAccStartYear(String accStartYear) {
        this.accStartYear = accStartYear;
    }

    public String getAccStopYear() {
        return accStopYear;
    }

    public void setAccStopYear(String accStopYear) {
        this.accStopYear = accStopYear;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccInfo{");
        sb.append("oeBrand='").append(oeBrand).append('\'');
        sb.append(", oeNo='").append(oeNo).append('\'');
        sb.append(", car='").append(car).append('\'');
        sb.append(", startYear='").append(startYear).append('\'');
        sb.append(", stopYear='").append(stopYear).append('\'');
        sb.append(", accStartYear='").append(accStartYear).append('\'');
        sb.append(", accStopYear='").append(accStopYear).append('\'');
        sb.append(", kw='").append(kw).append('\'');
        sb.append(", hp='").append(hp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
