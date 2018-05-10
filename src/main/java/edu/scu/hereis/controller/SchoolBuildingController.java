package edu.scu.hereis.controller;

import edu.scu.hereis.entity.SchoolBuilding;
import edu.scu.hereis.service.SchoolBuildingService;
import edu.scu.hereis.wxresult.SchoolBuildingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Vicent_Chen on 2018/5/7.
 */
@Controller
public class SchoolBuildingController {

    @Autowired
    private SchoolBuildingService schoolBuildingService;

    @ResponseBody
    @RequestMapping("/getClassroomList")
    public List<SchoolBuildingResult> getClassroomList(Integer spotId) {
        if (spotId == null) return null;

        List<SchoolBuilding> sbs = schoolBuildingService.getAllClassroomBySpotID(spotId);
        int maxFloor = schoolBuildingService.getMaxFloorBySpotID(spotId);
        List<SchoolBuildingResult> results = SchoolBuildingResult.toList(sbs, maxFloor);
        return results;
    }
}
