package edu.scu.hereis.controller;

import edu.scu.hereis.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @param file 用户头像
     * @return 用户头像URL
     */
    @RequestMapping("/uploadUserImage")
    public String uploadUserImage(MultipartFile file) {
        // TODO: get hereisId
        String hereIsId = null;
        fileService.uploadUserImage(hereIsId, file);
        String imageUrl = fileService.getUserImageURL(hereIsId);
        return imageUrl;
    }

    /**
     * 上传景点图片
     * @param spotId 景点ID
     * @param file 景点图片
     * @return 景点图片URL
     */
    @RequestMapping("/uploadSpotImage")
    public String uploadSpotImage(Integer spotId, MultipartFile file) {
        fileService.uploadSpotImage(spotId, file);
        String imageUrl = fileService.getSpotImageURL(spotId);
        return imageUrl;
    }

    /**
     * 上传菜单图片
     * @param menuId 菜单ID
     * @param file 菜单图片
     * @return 菜单图片URL
     */
    @RequestMapping("/uploadMenuImage")
    public String uploadMenuImage(Integer menuId, MultipartFile file) {
        fileService.uploadMenuImage(menuId, file);
        String imageUrl = fileService.getMenuImageURL(menuId);
        return imageUrl;
    }
}
