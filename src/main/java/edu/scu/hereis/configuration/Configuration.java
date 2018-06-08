package edu.scu.hereis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@ConfigurationProperties("wxapp")
@PropertySource("file:${configure-file-path}")
public class Configuration {

    private String appID;//微信小程序ID
    private String appSecret;//微信小程序秘钥
    private String LOGIN_URL;//微信小程序登录
    private String grant_type;//微信小程序登录
    private String defaultImagePath;//默认用户头像路径


    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getLOGIN_URL() {
        return LOGIN_URL;
    }

    public void setLOGIN_URL(String LOGIN_URL) {
        this.LOGIN_URL = LOGIN_URL;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getDefaultImagePath() {
        return defaultImagePath;
    }

    public void setDefaultImagePath(String defaultImagePath) {
        this.defaultImagePath = defaultImagePath;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "appID='" + appID + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", LOGIN_URL='" + LOGIN_URL + '\'' +
                ", grant_type='" + grant_type + '\'' +
                ", defaultImagePath='" + defaultImagePath + '\'' +
                '}';
    }
}
