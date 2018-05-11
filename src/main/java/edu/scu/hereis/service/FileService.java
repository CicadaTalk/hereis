package edu.scu.hereis.service;

import edu.scu.hereis.dao.MenuMapper;
import edu.scu.hereis.dao.SpotMapper;
import edu.scu.hereis.dao.UserMapper;
import edu.scu.hereis.entity.Menu;
import edu.scu.hereis.entity.Spot;
import edu.scu.hereis.entity.User;
import edu.scu.hereis.exception.FileException;
import edu.scu.hereis.exception.MenuServiceException;
import edu.scu.hereis.exception.SpotException;
import edu.scu.hereis.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static edu.scu.hereis.exception.FileException.*;
import static edu.scu.hereis.exception.MenuServiceException.*;
import static edu.scu.hereis.exception.SpotException.*;
import static edu.scu.hereis.exception.UserServiceException.*;

/**
 * Created by Vicent_Chen on 2018/5/8.
 */
@Service
public class FileService {

    @Value("${image.image-folder-path}")
    private String image_folder_path;

    @Value("${image.image-relate-path}")
    private String image_relate_path;

    @Value("${image.default-img-URL}")
    private String defaultImageURL;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SpotMapper spotMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 上传用户头像，若用户头像已经存在则删除原有文件，写入当前文件并对数据库进行覆盖
     * @param hereIsId
     */
    @Transactional
    public void uploadUserImage(String hereIsId, MultipartFile file) {

        if (file == null) {
            throw new FileException(EMPTY_FILE, EMPTY_FILE_MESSAGE);
        }

        User user = userMapper.selectByPrimaryKey(hereIsId);

        if (user == null) {
            throw new FileException(EMPTY_USER, EMPTY_USER_MESSAGE);
        }

        // 删除文件
        String relatePath = user.getImgPath();
        String absolutePath = getAbsolutePathFromRelatePath(relatePath);
        deleteFile(absolutePath);

        // 新建文件
        relatePath = generateRelatePath();
        absolutePath = getAbsolutePathFromRelatePath(relatePath);
        writeFile(absolutePath, file);

        // 写入数据库
        user.setImgPath(relatePath);
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 上传景点图片，若景点图片已经存在则删除原有文件，写入当前文件并对数据库进行覆盖
     * @param spotId
     * @param file
     */
    @Transactional
    public void uploadSpotImage(int spotId, MultipartFile file) {
        if (file == null) {
            throw new FileException(EMPTY_FILE, EMPTY_FILE_MESSAGE);
        }

        Spot spot = spotMapper.selectByPrimaryKey(spotId);

        if (spot == null) {
            throw new SpotException(SPOT_EMPTY_ERROR_CODE, SPOT_EMPTY_ERROR);
        }

        // 删除文件
        String relatePath = spot.getBgImg();
        String absolutePath = getAbsolutePathFromRelatePath(relatePath);
        deleteFile(absolutePath);

        // 新建文件
        relatePath = generateRelatePath();
        absolutePath = getAbsolutePathFromRelatePath(relatePath);
        writeFile(absolutePath, file);

        // 写入数据库
        spot.setBgImg(relatePath);
        spotMapper.updateByPrimaryKey(spot);
    }

    /**
     * 上传菜单图片，若菜单图片已经存在则删除原有文件，写入当前文件并对数据库进行覆盖
     * @param menuId
     * @param file
     */
    @Transactional
    public void uploadMenuImage(int menuId, MultipartFile file) {
        if (file == null) {
            throw new FileException(EMPTY_FILE, EMPTY_FILE_MESSAGE);
        }

        Menu menu = menuMapper.selectByPrimaryKey(menuId);

        if (menu == null) {
            throw new MenuServiceException(NOT_EXIST);
        }

        // 删除文件
        String relatePath = menu.getImgPath();
        String absolutePath=  getAbsolutePathFromRelatePath(relatePath);
        deleteFile(absolutePath);

        // 新建文件
        relatePath = generateRelatePath();
        absolutePath = getAbsolutePathFromRelatePath(relatePath);
        writeFile(absolutePath, file);

        // 写入数据库
        menu.setImgPath(relatePath);
        menuMapper.updateByPrimaryKey(menu);
    }

    /**
     * 获取用户头像URL
     * @param hereIsId
     * @return 用户头像URL
     */
    public String getUserImageURL(String hereIsId) {
        User user = userMapper.selectByPrimaryKey(hereIsId);
        if (user == null) {
            throw new UserServiceException(USER_NOT_EXSIST);
        }
        return user.getImgPath();
    }

    /**
     * 获取景点图片URL
     * @param spotId
     * @return 景点图片URL
     */
    public String getSpotImageURL(int spotId) {
        Spot spot = spotMapper.selectByPrimaryKey(spotId);
        if (spot == null) {
            throw new SpotException(SPOT_EMPTY_ERROR_CODE, SPOT_EMPTY_ERROR);
        }
        return spot.getBgImg();
    }
    /**
     * 获取菜单图片URL
     * @param menuId
     * @return 菜单图片URL
     */
    public String getMenuImageURL(int menuId) {
        Menu menu = menuMapper.selectByPrimaryKey(menuId);
        if (menu == null) {
            throw new MenuServiceException(NOT_EXIST);
        }
        return menu.getImgPath();
    }

    /**
     * 根据相对路径获取绝对路径
     * @param relatePath 相对路径
     * @return 绝对路径
     */
    private String getAbsolutePathFromRelatePath(String relatePath) {
        return image_folder_path + relatePath;
    }

    /**
     * 生成文件相对路径，文件名通过UUID自动生成
     * @return 文件相对路径
     */
    private String generateRelatePath() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        Date date = new Date();
        return dateFormat.format(date) + UUID.randomUUID();
    }

    /**
     * Write file to disk.
     * @param fileAbsolutePath: The <b>absolute</b> path of file
     * @param file: file uploaded from browser
     */
    private void writeFile(String fileAbsolutePath, MultipartFile file) {
        // TODO: log
        if (fileAbsolutePath == null || fileAbsolutePath.equals("")) {
            throw new FileException(IO_EXCEPTION, IO_EXCEPTION_MESSAGE);
        }
        if(file.isEmpty()) {
            throw new FileException(EMPTY_FILE, EMPTY_FILE_MESSAGE);
        }

        // check if directory exists
        File fileToSave = new File(fileAbsolutePath);
        if(!fileToSave.getParentFile().exists()){
            fileToSave.getParentFile().mkdirs();
        }

        // write file
        try {
            file.transferTo(fileToSave);
        } catch (IOException e) {
            String error = "不能写入文件: " + e.getMessage();
            throw new FileException(IO_EXCEPTION, IO_EXCEPTION_MESSAGE + error);
        }
    }

    /**
     * Delete file.
     * @param fileAbsolutePath
     */
    private void deleteFile(String fileAbsolutePath) {
        if (fileAbsolutePath == null || fileAbsolutePath.equals("")) {
            throw new FileException(IO_EXCEPTION, IO_EXCEPTION_MESSAGE);
        }

        File fileToDelete = new File(fileAbsolutePath);
        // ignore file that not exists or that is a directory
        if (!fileToDelete.exists() || !fileToDelete.isFile()) {
            return;
        }

        fileToDelete.delete();
    }
}
