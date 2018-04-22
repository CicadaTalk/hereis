package edu.scu.hereis.service;

import edu.scu.hereis.dao.ActivityMapper;
import edu.scu.hereis.entity.Activity;
import edu.scu.hereis.exception.ActivityException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 景点活动相关操作的服务类
 */
@Service
public class ActivityService {

	@Autowired
	private SqlSession sqlSession;
    
    /**
     * 传入景点活动对象，保存景点活动信息
     * @param activity 活动对象
     */
	@Transactional
	public void insertActivity(Activity activity) {
		// 检查输入是否为空
		if (activity == null) {
			throw new ActivityException(ActivityException.ACTIVITY_EMPTY_ERROR);
		}

		// 插入新的活动记录
		try {
			ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
			activityMapper.insert(activity);
		} catch (ActivityException e){
			throw new ActivityException(e.UNKNOWN_ERROR);
		}
	}

	

    /**
     * 传入景点活动id，删除对应的景点活动
     * @param id 需要删除的景点活动的id
     */
	@Transactional
	public void deleteActivity(int id) {
		// 检查输入是否为空
		if (id <= 0) {
			throw new ActivityException(ActivityException.ACTIVITY_ID_EMPTY_ERROR);
		}

		// 删除id对应的活动记录
		try {
			ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
			activityMapper.deleteByPrimaryKey(id);
		} catch (ActivityException e){
			throw new ActivityException(e.UNKNOWN_ERROR);
		}
	}
}