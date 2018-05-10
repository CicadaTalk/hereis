package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Activity;
import edu.scu.hereis.entity.ScenicSpot;
import edu.scu.hereis.service.ActivityService;
import edu.scu.hereis.service.ScenicSpotService;
import edu.scu.hereis.wxresult.ScenicSpotResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 与餐馆相关，包括获取菜单
 */
@Controller
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;
    @Autowired
    private ActivityService activityService;

    @ResponseBody
    @GetMapping("/getScenicSpotById")
    public ScenicSpotResult getScenicSpotById(int spotId) {

        //根据spotId获取景点详细信息
        ScenicSpot scenicSpot = scenicSpotService.getScenicSpotById(spotId);
        //根据spotId获取景点活动信息列表
        List<Activity> activityList = activityService.getActivitiesBySpotId(spotId);
        //
        ScenicSpotResult scenicSpotResult = null;
        if (scenicSpot != null && activityList != null && activityList.size() > 0) {
            scenicSpotResult = new ScenicSpotResult(scenicSpot.getSpotId(), scenicSpot.getIntro(),
                    activityList, scenicSpot.getWarning());
        }

        return scenicSpotResult;
    }
}
