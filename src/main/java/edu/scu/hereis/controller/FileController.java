package edu.scu.hereis.controller;

import edu.scu.hereis.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vicent_Chen on 2018/5/7.
 * 此处所有接口仅为参考，具体实现需要自己修改
 */
@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 上传用户头像
     *
     * @param file 用户头像
     * @return 用户头像URL
     */
    @ResponseBody
    @PostMapping("/uploadUserImage")
    public String uploadUserImage(MultipartFile file) {
        // TODO: get hereisId
        String hereIsId = null;
        fileService.uploadUserImage(hereIsId, file);
        String imageUrl = fileService.getUserImageURL(hereIsId);
        return imageUrl;
    }

    /**
     * 上传景点图片
     *
     * @param spotId 景点ID
     * @param file   景点图片
     * @return 景点图片URL
     */
    @ResponseBody
    @PostMapping("/uploadSpotImage")
    public String uploadSpotImage(Integer spotId, MultipartFile file) {
        fileService.uploadSpotImage(spotId, file);
        String imageUrl = fileService.getSpotImageURL(spotId);
        return imageUrl;
    }

    /**
     * 上传菜单图片
     *
     * @param menuId 菜单ID
     * @param file   菜单图片
     * @return 菜单图片URL
     */
    @ResponseBody
    @PostMapping("/uploadMenuImage")
    public String uploadMenuImage(Integer menuId, MultipartFile file) {
        fileService.uploadMenuImage(menuId, file);
        String imageUrl = fileService.getMenuImageURL(menuId);
        return imageUrl;
    }

    /**
     * 根据SpotId获取对应的背景图片Url
     *
     * @param spotId
     * @return 背景图片Url
     */
    @ResponseBody
    @GetMapping("/getSpotImage")
    public String getSpotImage(Integer spotId) {
        return fileService.getSpotImageURL(spotId);
    }

    /**
     * 根据MenuId获取对应的菜单图片Url
     *
     * @param menuId
     * @return 背景图片Url
     */
    @ResponseBody
    @GetMapping("/getMenuImage")
    public String getMenuImage(Integer menuId) {
        return fileService.getMenuImageURL(menuId);
    }

    /**
     * 根据hereisId获取对应的用户头像Url
     *
     * @param hereisId
     * @return 背景图片Url
     */
    @ResponseBody
    @GetMapping("/getUserImage")
    public String getUserImage(String hereisId) {
        return fileService.getUserImageURL(hereisId);
    }
}
