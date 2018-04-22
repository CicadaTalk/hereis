package edu.scu.hereis.service;

import edu.scu.hereis.dao.SchoolBuildingMapper;
import edu.scu.hereis.entity.SchoolBuilding;
import edu.scu.hereis.entity.SchoolBuildingExample;
import edu.scu.hereis.exception.SchoolBuildingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.scu.hereis.exception.SchoolBuildingException.*;

/**
 * Created by Vicent_Chen on 2018/4/21.
 */
@Service
public class SchoolBuildingService {

    @Autowired
    SchoolBuildingMapper schoolBuildingMapper;

    /**
     * 插入schoolBuilding，自动忽略已有的ID
     * @param schoolBuilding 除ID，isFree外所有字段不得为空
     */
    public void insertSchoolBuilding(SchoolBuilding schoolBuilding) {
        if (schoolBuilding == null) {
            throw new SchoolBuildingException(SB_EMPTY_ERROR_CODE, SB_EMPTY_ERROR);
        }

        try {
            schoolBuilding.setId(null);
            schoolBuildingMapper.insertSelective(schoolBuilding);
        }
        catch (DataIntegrityViolationException e) {
            if (schoolBuilding.getFloor() == null)
                throw new SchoolBuildingException(FLOOR_EMPTY_ERROR_CODE, FLOOR_EMPTY_ERROR);
            else if (schoolBuilding.getClassroom() == null)
                throw new SchoolBuildingException(CLASSROOM_EMPTY_ERROR_CODE, CLASSROOM_EMPTY_ERROR);
            else if (schoolBuilding.getClassroomType() == null)
                throw new SchoolBuildingException(CLASSTYPE_EMPTY_ERROR_CODE, CLASSTYPE_EMPTY_ERROR);
            else if (schoolBuilding.getSpotId() == null)
                throw new SchoolBuildingException(SPOT_ID_EMPTY_ERROR_CODE, SPOT_ID_EMPTY_ERROR);
            else
                throw new SchoolBuildingException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 根据ID更新SchoolBuilding
     * @param schoolBuilding
     */
    public void updateSchoolBuilding(SchoolBuilding schoolBuilding) {
        if (schoolBuilding == null || schoolBuilding.getId() == null)
            throw new SchoolBuildingException(SB_EMPTY_ERROR_CODE, SB_EMPTY_ERROR);

        try {
            schoolBuildingMapper.updateByPrimaryKeySelective(schoolBuilding);
        }
        catch (RuntimeException e) {
            throw new SchoolBuildingException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 根据ID删除SchoolBuilding
     * @param id
     */
    public void deleteSchoolBuilding(Integer id) {
        try {
            schoolBuildingMapper.deleteByPrimaryKey(id);
        }
        catch (RuntimeException e) {
            throw new SchoolBuildingException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 获取一栋教学楼的层数
     * @param spotID 不能为空
     * @return
     */
    public int getMaxFloorBySpotID(Integer spotID) {
        if (spotID == null) {
            throw new SchoolBuildingException(SB_EMPTY_ERROR_CODE, SB_EMPTY_ERROR);
        }
        Integer result = schoolBuildingMapper.selectMaxFloorBySpotId(spotID);
        return result;
    }

    /**
     * 获取一栋教学楼所有教室，
     * @param spotID 不能为空
     * @return
     */
    public List<SchoolBuilding> getAllClassroomBySpotID(Integer spotID) {
        if (spotID == null) {
            throw new SchoolBuildingException(SB_EMPTY_ERROR_CODE, SB_EMPTY_ERROR);
        }

        SchoolBuildingExample schoolBuildingExample = new SchoolBuildingExample();
        schoolBuildingExample.createCriteria().andSpotIdEqualTo(spotID);
        List<SchoolBuilding> schoolBuildingList = schoolBuildingMapper.selectByExample(schoolBuildingExample);
        return schoolBuildingList;
    }

    /**
     * 根据ID获取教室，
     * @param id
     * @return 可能返回null
     */
    public SchoolBuilding getSchoolBuildingByID(Integer id) {
        SchoolBuilding schoolBuilding = null;
        schoolBuilding = schoolBuildingMapper.selectByPrimaryKey(id);
        return schoolBuilding;
    }

    /**
     * 获取所有教室
     * @return 可能返回空列表
     */
    public List<SchoolBuilding> getAllSchoolBuildings() {
        List<SchoolBuilding> schoolBuildingList = schoolBuildingMapper.selectByExample(new SchoolBuildingExample());
        return schoolBuildingList;
    }


}
