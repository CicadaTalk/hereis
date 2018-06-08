package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Activity;
import edu.scu.hereis.entity.ScenicSpot;
import edu.scu.hereis.service.ActivityService;
import edu.scu.hereis.service.ScenicSpotService;
import edu.scu.hereis.wxresult.ActivityResult;
import edu.scu.hereis.wxresult.ScenicSpotResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * 与餐馆相关，包括获取菜单
 */
@RestController
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;
    @Autowired
    private ActivityService activityService;

    /**
     * 根据spotId获取景点详细信息
     * @param spotId 热点id
     * @return 景点详细信息结果
     */
    @GetMapping("/getScenicSpotById")
    public ScenicSpotResult getScenicSpotById(int spotId) {

        //根据spotId获取景点详细信息
        ScenicSpot scenicSpot = scenicSpotService.getScenicSpotById(spotId);
        //根据spotId获取景点活动信息列表
        List<Activity> activityList = activityService.getActivitiesBySpotId(spotId);
        //
        ScenicSpotResult scenicSpotResult = null;
        if (scenicSpot != null) {
            scenicSpotResult = new ScenicSpotResult(scenicSpot.getSpotId(), scenicSpot.getIntro(),
                    activityList, scenicSpot.getWarning());
        }

        return scenicSpotResult;
    }

    /**
     * 添加景点详细信息
     * @param scenicSpot 景点详细信息对象
     */
    @PostMapping("/addScenicSpot")
    public void addScenicSpot(ScenicSpot scenicSpot) {

        if (scenicSpot != null){
            scenicSpotService.insertScenicSpot(scenicSpot);
        }
    }

    /**
     * 添加景点活动信息
     * @param activityResult 景点活动对象
     */
    @PostMapping("/addActivity")
    public void addActivity(ActivityResult activityResult) {

        if (activityResult != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date begin = formatter.parse(activityResult.getBeginTime());
                Date end = formatter.parse(activityResult.getEndTime());
                Activity activity = new Activity();
                activity.setId(activityResult.getId());
                activity.setName(activityResult.getName());
                activity.setBeginTime(begin);
                activity.setEndTime(end);
                activity.setIntro(activityResult.getIntro());
                activity.setSpotId(activityResult.getSpotId());
                activityService.insertActivity(activity);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
