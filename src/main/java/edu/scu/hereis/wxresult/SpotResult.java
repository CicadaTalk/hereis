package edu.scu.hereis.wxresult;

import edu.scu.hereis.entity.Spot;
import edu.scu.hereis.exception.SpotException;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static edu.scu.hereis.exception.SpotException.*;

/**
 * Created by Vicent_Chen on 2018/5/11.
 */
public class SpotResult {



    private Integer id;
    private String name;
    private String briefIntro;
    private String img;
    private Double gpsLng;
    private Double gpsLat;

    public SpotResult(Integer id, String name, String briefIntro, String img, Double gpsLng, Double gpsLat) {
        this.id = id;
        this.name = name;
        this.briefIntro = briefIntro;
        this.img = img;
        this.gpsLng = gpsLng;
        this.gpsLat = gpsLat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(Double gpsLng) {
        this.gpsLng = gpsLng;
    }

    public Double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
    }
}
