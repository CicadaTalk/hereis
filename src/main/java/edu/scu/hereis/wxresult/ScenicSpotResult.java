package edu.scu.hereis.wxresult;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScenicSpotResult {

    /**
     * 景点ID
     */
    private int spotId;

    /**
     * 景点详细介绍
     */
    private String intro;

    /**
     * 景点活动信息列表
     */
    private Activity[] activities;

    /**
     * 景点注意事项
     */
    private String[] warning;

    /**
     * 构造函数
     *
     * @param spotId 景点ID
     * @param intro 景点详细介绍
     * @param warning 景点注意事项
     */
    public ScenicSpotResult(int spotId, String intro, List<edu.scu.hereis.entity.Activity> activityList,
                            String warning) {

        this.spotId = spotId;
        this.intro = intro;

        Activity[] activityArray = new Activity[activityList.size()];
        int i = 0;
        for (edu.scu.hereis.entity.Activity a : activityList) {
            activityArray[i++] = new Activity(a);
        }
        this.activities = activityArray;

        //解析JSON字符串
        JSONArray jsonArray = JSONObject.parseArray(warning);
        this.warning = new String[jsonArray.size()];
        jsonArray.toArray(this.warning);
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Activity[] getActivities() {
        return activities;
    }

    public void setActivities(Activity[] activities) {
        this.activities = activities;
    }

    public String[] getWarning() {
        return warning;
    }

    public void setWarning(String[] warning) {
        this.warning = warning;
    }
}

class Activity {

    /**
     * 活动ID
     */
    private int activityId;

    /**
     * 景点ID
     */
    private int spotId;

    /**
     * 活动介绍
     */
    private String intro;

    /**
     * 活动开始时间
     */
    private String beginTime;

    /**
     * 活动结束时间
     */
    private String endTime;

    /**
     * 活动名称
     */
    private String name;

    public Activity(edu.scu.hereis.entity.Activity activity) {
        this.activityId = activity.getId();
        this.spotId = activity.getSpotId();
        this.intro = activity.getIntro();

        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        this.beginTime = format.format(activity.getBeginTime());
        this.endTime = format.format(activity.getEndTime());

        this.name = activity.getName();
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
