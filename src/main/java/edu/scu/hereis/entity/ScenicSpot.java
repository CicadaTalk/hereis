package edu.scu.hereis.entity;

public class ScenicSpot {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenic_spot.spot_id
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    private Integer spotId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenic_spot.intro
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    private String intro;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenic_spot.warning
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    private String warning;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenic_spot.spot_id
     *
     * @return the value of scenic_spot.spot_id
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    public Integer getSpotId() {
        return spotId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenic_spot.spot_id
     *
     * @param spotId the value for scenic_spot.spot_id
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenic_spot.intro
     *
     * @return the value of scenic_spot.intro
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    public String getIntro() {
        return intro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenic_spot.intro
     *
     * @param intro the value for scenic_spot.intro
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenic_spot.warning
     *
     * @return the value of scenic_spot.warning
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    public String getWarning() {
        return warning;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenic_spot.warning
     *
     * @param warning the value for scenic_spot.warning
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    public void setWarning(String warning) {
        this.warning = warning == null ? null : warning.trim();
    }
}