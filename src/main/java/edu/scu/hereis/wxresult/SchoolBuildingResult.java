package edu.scu.hereis.wxresult;

import edu.scu.hereis.entity.SchoolBuilding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicent_Chen on 2018/5/7.
 */
class Classroom {
    private Integer id;
    private String classroom;
    private String classroomType;
    private boolean isFree;

    public Classroom(SchoolBuilding schoolBuilding) {
        this.id = schoolBuilding.getId();
        this.classroom = schoolBuilding.getClassroom();
        this.classroomType = schoolBuilding.getClassroomType();
        this.isFree = schoolBuilding.getIsFree();
    }

    /**
     * 将原始的SchoolBuilding转换为教室列表
     * @param sbs 待转换的**同一层**的教室
     * @return
     */
    public static List<Classroom> toList(List<SchoolBuilding> sbs) {
        List<Classroom> classrooms = new ArrayList<>();
        for (SchoolBuilding sb : sbs)
            classrooms.add(new Classroom(sb));
        return classrooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClassroomType() {
        return classroomType;
    }

    public void setClassroomType(String classroomType) {
        this.classroomType = classroomType;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}

public class SchoolBuildingResult {
    private boolean open;
    private Integer floor;
    private List<Classroom> classroomList;

    public SchoolBuildingResult(List<SchoolBuilding> sbs, int floor) {
        this.open = false;
        this.floor = floor;

        List<SchoolBuilding> sbsInFloor = new ArrayList<>();
        for (SchoolBuilding sb : sbs) {
            if (sb.getFloor() == floor) {
                sbsInFloor.add(sb);
            }
        }

        this.classroomList = Classroom.toList(sbsInFloor);
    }

    /**
     * 转换原始SchoolBuilding列表为楼层列表
     * @param sbs
     * @param maxFloor
     * @return
     */
    public static List<SchoolBuildingResult> toList(List<SchoolBuilding> sbs, int maxFloor) {
        List<SchoolBuildingResult> schoolBuildingResults = new ArrayList<>();
        for (int i = 1; i <= maxFloor; i++) {
            schoolBuildingResults.add(new SchoolBuildingResult(sbs, i));
        }
        return schoolBuildingResults;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    public void setClassroomList(List<Classroom> classroomList) {
        this.classroomList = classroomList;
    }
}
