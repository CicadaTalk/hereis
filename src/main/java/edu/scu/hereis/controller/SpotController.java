package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Spot;
import edu.scu.hereis.service.SpotService;
import edu.scu.hereis.wxresult.MarkerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicent_Chen on 2018/5/11.
 */
@Controller
public class SpotController {

    @Autowired
    private SpotService spotService;

    @Value("${image.url-prefix}")
    private String imageUrlPrefix;//URL前缀

    @ResponseBody
    @RequestMapping("/getSpotsByGPS")
    public List<Spot> getSpotsByGPS(Double gpsLng, Double gpsLat, Double r) {
        // TODO: 验证数据合法性
        List<Spot> spotList = spotService.getSpotsByGPS(gpsLng - r, gpsLng + r, gpsLat - r, gpsLat + r);

        for (Spot spot :
                spotList) {
            spot.setBgImg(imageUrlPrefix + spot.getBgImg());
        }
        return spotList;
    }

    /**
     * 根据传入的经纬度和查找范围获取对应的标记点信息
     * @param gpsLng 经度
     * @param gpsLat 纬度
     * @param r 查找范围
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMarkerByGPS")
    public List<MarkerResult> getMarkerByGPS(Double gpsLng, Double gpsLat, Double r) {
        // TODO: 验证数据合法性
        List<Spot> spotList = spotService.getSpotsByGPS(gpsLng - r, gpsLng + r, gpsLat - r, gpsLat + r);
        List<MarkerResult> markerResults = new ArrayList<MarkerResult>();

        for (Spot spot :
                spotList) {
            markerResults.add(new MarkerResult(spot.getId(),spot.getGpsLat(),spot.getGpsLng(),spot.getName()));
        }
        return markerResults;
    }

    /**
     * 添加一个热点到数据库
     *
     * @param spot
     */
    @ResponseBody
    @PostMapping("/addSpot")
    public void addSpot(Spot spot) {

        //添加spot到数据库
        spotService.insertSpot(spot);

        System.out.println(spot);
    }
}
