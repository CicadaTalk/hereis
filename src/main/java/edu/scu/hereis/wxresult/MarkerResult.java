package edu.scu.hereis.wxresult;

public class MarkerResult {

    private final String iconPath = "/image/location.png";//marker图标
    private Integer id;//marker的Id，与SpotId相同
    private double latitude;//纬度
    private double longitude;//经度
    private final Integer width = 35;
    private final Integer height = 40;
    private String title;//标注点名

    public MarkerResult(Integer id, double latitude, double longitude, String title) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
