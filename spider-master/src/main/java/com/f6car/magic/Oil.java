package com.f6car.magic;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by qixiaobo on 2017/7/14.
 */
public class Oil extends Model<Oil> {
    private String name;
    private String cap;
    private String cat;
    public static final Oil dao = new Oil().dao();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Oil{");
        sb.append("name='").append(name).append('\'');
        sb.append(", cap='").append(cap).append('\'');
        sb.append(", cat='").append(cat).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
