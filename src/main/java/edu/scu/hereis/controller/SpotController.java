package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Spot;
import edu.scu.hereis.service.SpotService;
import edu.scu.hereis.wxresult.SpotResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Vicent_Chen on 2018/5/11.
 */
@Controller
public class SpotController {

    @Autowired
    private SpotService spotService;

    @ResponseBody
    @RequestMapping("/getSpotsByGPS")
    public List<SpotResult> getSpotsByGPS(Double gpsLng, Double gpsLat, Double r) {
        // TODO: 验证数据合法性
        List<Spot> spotList = spotService.getSpotsByGPS(gpsLng - r, gpsLng + r, gpsLat - r, gpsLat + r);
        List<SpotResult> spotResultList = SpotResult.getSpotResultList(spotList);
        return spotResultList;
    }

    /**
     * 添加一个热点到数据库
     * @param spot
     */
    @ResponseBody
    @PostMapping("/addSpot")
    public void addSpot(Spot spot){

        //添加spot到数据库
        spotService.insertSpot(spot);

        System.out.println(spot);
    }
}
