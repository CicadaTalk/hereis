package edu.scu.hereis.wxresult;

import edu.scu.hereis.entity.Spot;
import edu.scu.hereis.exception.SpotException;

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

    public static SpotResult getSpotResult(Spot spot) {
        if (spot == null) {
            throw new SpotException(SPOT_EMPTY_ERROR_CODE, SPOT_EMPTY_ERROR);
        }

        SpotResult spotResult = new SpotResult(spot.getId(), spot.getName(), spot.getBriefIntro(), spot.getBgImg());
        return spotResult;
    }

    public static List<SpotResult> getSpotResultList(List<Spot> spotList) {
        List<SpotResult> spotResults = new ArrayList<>();
        for (Spot s : spotList) {
            spotResults.add(getSpotResult(s));
        }
        return spotResults;
    }

    public SpotResult(Integer id, String name, String briefIntro, String img) {
        this.id = id;
        this.name = name;
        this.briefIntro = briefIntro;
        this.img = img;
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
}
