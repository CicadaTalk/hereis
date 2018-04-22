package edu.scu.hereis.service;

import edu.scu.hereis.entity.SchoolBuilding;
import edu.scu.hereis.entity.SchoolBuildingExample;
import edu.scu.hereis.exception.SchoolBuildingException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static edu.scu.hereis.exception.SchoolBuildingException.*;

/**
 * Created by Vicent_Chen on 2018/4/22.
 * 测试方法：
 * 1. 首先获取当前数据库中函数CURRENT_ROW_NUM
 * 2. 然后获取当前数据库中自增索引CURRENT_MAX_ID
 * 3. 运行测试
 * 4. 测试以后使用 alter table school_building AUTO_INCREMENT=CURRENT_MAX_ID + 1 重置数据库自增索引
 * 所有测试均已通过 2018/4/22
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SchoolBuildingServiceTest {

    // 当前SchoolBuilding表中最大的ID
    public final static int CURRENT_MAX_ID = 0;
    // 当前SchoolBuilding表中存在的行数
    public final static int CURRENT_ROW_NUM = 0;

    @Autowired
    SchoolBuildingService schoolBuildingService;

    SchoolBuilding nullSchoolBuilding;
    SchoolBuilding emptyFloorSchoolBuilding, emptyClassroomSchoolBuilding;
    SchoolBuilding emptyTypeSchoolBuilding;
    SchoolBuilding emptySpotIdSchoolBuilding;

    SchoolBuilding emptyIDSchoolBuilding, additionIDSchoolBuilding;
    SchoolBuilding emptyIsFreeSchoolBuilding;

    SchoolBuilding toUpdate;

    @Before
    public void init() {
        nullSchoolBuilding = nullSchoolBuilding;

        // wrong
        emptyFloorSchoolBuilding = new SchoolBuilding();
        emptyFloorSchoolBuilding.setFloor(null); emptyFloorSchoolBuilding.setClassroom("101A");
        emptyFloorSchoolBuilding.setIsFree(true); emptyFloorSchoolBuilding.setClassroomType("普通教室");
        emptyFloorSchoolBuilding.setSpotId(2);

        emptyClassroomSchoolBuilding = new SchoolBuilding();
        emptyClassroomSchoolBuilding.setFloor(3); emptyClassroomSchoolBuilding.setClassroom(null);
        emptyClassroomSchoolBuilding.setIsFree(true); emptyClassroomSchoolBuilding.setClassroomType("普通教室");
        emptyClassroomSchoolBuilding.setSpotId(2);

        emptyTypeSchoolBuilding = new SchoolBuilding();
        emptyTypeSchoolBuilding.setFloor(3); emptyTypeSchoolBuilding.setClassroom("101A");
        emptyTypeSchoolBuilding.setIsFree(true); emptyTypeSchoolBuilding.setClassroomType(null);
        emptyTypeSchoolBuilding.setSpotId(2);

        emptySpotIdSchoolBuilding = new SchoolBuilding();
        emptySpotIdSchoolBuilding.setFloor(3); emptySpotIdSchoolBuilding.setClassroom("101A");
        emptySpotIdSchoolBuilding.setIsFree(true); emptySpotIdSchoolBuilding.setClassroomType("普通教室");
        emptySpotIdSchoolBuilding.setSpotId(null);

        // right
        emptyIDSchoolBuilding = new SchoolBuilding();
        emptyIDSchoolBuilding.setFloor(3); emptyIDSchoolBuilding.setClassroom("101A");
        emptyIDSchoolBuilding.setIsFree(true); emptyIDSchoolBuilding.setClassroomType("普通教室");
        emptyIDSchoolBuilding.setSpotId(2);

        additionIDSchoolBuilding = new SchoolBuilding(); additionIDSchoolBuilding.setSpotId(1);
        additionIDSchoolBuilding.setFloor(3); additionIDSchoolBuilding.setClassroom("101A");
        additionIDSchoolBuilding.setIsFree(true); additionIDSchoolBuilding.setClassroomType("普通教室");
        additionIDSchoolBuilding.setSpotId(2);

        emptyIsFreeSchoolBuilding = new SchoolBuilding();
        emptyIsFreeSchoolBuilding.setFloor(3); emptyIsFreeSchoolBuilding.setClassroom("101A");
        emptyIsFreeSchoolBuilding.setIsFree(null); emptyIsFreeSchoolBuilding.setClassroomType("普通教室");
        emptyIsFreeSchoolBuilding.setSpotId(2);

        toUpdate = new SchoolBuilding(); toUpdate.setId(CURRENT_MAX_ID + 1);
        toUpdate.setClassroomType("多媒体教室");
    }

    @Test
    public void test001getAllSchoolBuildings() {
        assertEquals(CURRENT_ROW_NUM, schoolBuildingService.getAllSchoolBuildings().size());
    }

    @Test
    public void test002insertSchoolBuilding() {
        try {
            schoolBuildingService.insertSchoolBuilding(nullSchoolBuilding);
        }
        catch (SchoolBuildingException e) {
            assertEquals(SB_EMPTY_ERROR_CODE, e.getCode());
        }
        try {
            schoolBuildingService.insertSchoolBuilding(emptyFloorSchoolBuilding);
        }
        catch (SchoolBuildingException e) {
            assertEquals(FLOOR_EMPTY_ERROR_CODE, e.getCode());
        }
        try {
            schoolBuildingService.insertSchoolBuilding(emptyClassroomSchoolBuilding);
        }
        catch (SchoolBuildingException e) {
            assertEquals(CLASSROOM_EMPTY_ERROR_CODE, e.getCode());
        }
        try {
            schoolBuildingService.insertSchoolBuilding(emptyTypeSchoolBuilding);
        }
        catch (SchoolBuildingException e) {
            assertEquals(CLASSTYPE_EMPTY_ERROR_CODE, e.getCode());
        }
        try {
            schoolBuildingService.insertSchoolBuilding(emptySpotIdSchoolBuilding);
        }
        catch (SchoolBuildingException e) {
            assertEquals(SPOT_ID_EMPTY_ERROR_CODE, e.getCode());
        }

        schoolBuildingService.insertSchoolBuilding(emptyIDSchoolBuilding);
        schoolBuildingService.insertSchoolBuilding(additionIDSchoolBuilding);
        schoolBuildingService.insertSchoolBuilding(emptyIsFreeSchoolBuilding);

        assertEquals(CURRENT_ROW_NUM + 3, schoolBuildingService.getAllSchoolBuildings().size());
    }

    @Test
    public void test003getSchoolBuildingByID() {
        SchoolBuilding schoolBuilding = schoolBuildingService.getSchoolBuildingByID(null);
        assertEquals(null, schoolBuilding);
        schoolBuilding = schoolBuildingService.getSchoolBuildingByID(CURRENT_MAX_ID + 4);
        assertEquals(null, schoolBuilding);
        schoolBuilding = schoolBuildingService.getSchoolBuildingByID(CURRENT_MAX_ID + 1);
        assertEquals(emptyIDSchoolBuilding.getClassroom(), schoolBuilding.getClassroom());
    }

    @Test
    public void test004updateSchoolBuilding() {
        try {
            schoolBuildingService.updateSchoolBuilding(nullSchoolBuilding);
        }
        catch (SchoolBuildingException e) {
            assertEquals(SB_EMPTY_ERROR_CODE, e.getCode());
        }

        try {
            schoolBuildingService.updateSchoolBuilding(emptyIDSchoolBuilding);
        }
        catch (SchoolBuildingException e) {
            assertEquals(SB_EMPTY_ERROR_CODE, e.getCode());
        }

        schoolBuildingService.updateSchoolBuilding(toUpdate);
        assertEquals(toUpdate.getClassroomType(), schoolBuildingService.getSchoolBuildingByID(toUpdate.getId()).getClassroomType());
    }

    @Test
    public void test005getAllClassroomBySpotID() {
        try {
            schoolBuildingService.getAllClassroomBySpotID(null);
        }
        catch (SchoolBuildingException e) {
            assertEquals(SB_EMPTY_ERROR_CODE, e.getCode());
        }
        try {
            schoolBuildingService.getAllClassroomBySpotID(emptyIDSchoolBuilding.getSpotId() + 1);
        }
        catch (SchoolBuildingException e) {
            assertEquals(SB_EMPTY_ERROR_CODE, e.getCode());
        }

        assertEquals(3, schoolBuildingService.getAllClassroomBySpotID(emptyIDSchoolBuilding.getSpotId()).size());
    }

    @Test
    public void test006getMaxFloorBySpotID() {
        try {
            schoolBuildingService.getMaxFloorBySpotID(null);
        }
        catch (SchoolBuildingException e) {
            assertEquals(SB_EMPTY_ERROR_CODE, e.getCode());
        }
        assertEquals(0, schoolBuildingService.getMaxFloorBySpotID(emptyIDSchoolBuilding.getSpotId() + 1));

        assertEquals((int)emptyIDSchoolBuilding.getFloor(), schoolBuildingService.getMaxFloorBySpotID(emptyIDSchoolBuilding.getSpotId()));
    }

    @Test
    public void test007deleteSchoolBuilding() {
        schoolBuildingService.deleteSchoolBuilding(CURRENT_MAX_ID + 1);
        assertEquals(CURRENT_ROW_NUM + 2, schoolBuildingService.getAllSchoolBuildings().size());
        schoolBuildingService.deleteSchoolBuilding(CURRENT_MAX_ID + 2);
        schoolBuildingService.deleteSchoolBuilding(CURRENT_MAX_ID + 3);
        assertEquals(CURRENT_ROW_NUM, schoolBuildingService.getAllSchoolBuildings().size());
    }




}