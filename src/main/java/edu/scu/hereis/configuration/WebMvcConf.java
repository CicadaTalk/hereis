package edu.scu.hereis.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {

    @Value("${image.image-folder-path}")
    private String mImagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        mImagesPath = "file:"+mImagesPath;

        if(mImagesPath.equals("") || mImagesPath.equals("${image.image-folder-path}")){
            String imagesPath = WebMvcConf.class.getClassLoader().getResource("").getPath();
            if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/images/";
            mImagesPath = imagesPath;
        }
        LoggerFactory.getLogger(WebMvcConf.class).info("imagesPath="+mImagesPath);
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);

        // TODO Auto-generated method stub
        super.addResourceHandlers(registry);
    }
}