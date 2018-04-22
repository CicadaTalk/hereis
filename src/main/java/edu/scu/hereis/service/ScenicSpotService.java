package edu.scu.hereis.service;

import edu.scu.hereis.dao.ScenicSpotMapper;
import edu.scu.hereis.entity.ScenicSpot;
import edu.scu.hereis.exception.ScenicSpotException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 景点相关操作的服务类
 */
@Service
public class ScenicSpotService {

	@Autowired
	private SqlSession sqlSession;
    
    /**
     * 传入景点对象，保存景点信息
     * @param scenicSpot 景点对象
     */
	@Transactional
	public void insertScenicSpot(ScenicSpot scenicSpot) {
		// 检查输入是否为空
		if (scenicSpot == null) {
			throw new ScenicSpotException(ScenicSpotException.SCENIC_SPOT_EMPTY_ERROR);
		}

		// 插入新的景点记录
		try {
			ScenicSpotMapper scenicSpotMapper = sqlSession.getMapper(ScenicSpotMapper.class);
			scenicSpotMapper.insert(scenicSpot);
		} catch (ScenicSpotException e){
			throw new ScenicSpotException(e.UNKNOWN_ERROR);
		}
	}

    /**
     * 传入景点id，删除对应的景点
     * @param id 需要删除的景点的id
     */
	@Transactional
	public void deleteScenicSpot(int id) {
		// 检查输入是否为空
		if (id <= 0) {
			throw new ScenicSpotException(ScenicSpotException.SCENIC_SPOT_ID_EMPTY_ERROR);
		}

		// 删除id对应的景点记录
		try {
			ScenicSpotMapper scenicSpotMapper = sqlSession.getMapper(ScenicSpotMapper.class);
			scenicSpotMapper.deleteByPrimaryKey(id);
		} catch (ScenicSpotException e){
			throw new ScenicSpotException(e.UNKNOWN_ERROR);
		}
	}
}