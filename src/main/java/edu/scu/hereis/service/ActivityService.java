package edu.scu.hereis.service;

import edu.scu.hereis.dao.ActivityMapper;
import edu.scu.hereis.entity.Activity;
import edu.scu.hereis.entity.ActivityExample;
import edu.scu.hereis.exception.ActivityException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	/**
	 * 传入景点活动id, 获取对应的景点活动信息
	 * @param id  需要获取的景点活动的id
	 */
	@Transactional
	public Activity getActivityById(int id) {
		// 检查输入是否为空
		if (id <= 0){
			throw new ActivityException(ActivityException.ACTIVITY_ID_EMPTY_ERROR);
		}

		// 根据id获取对应的活动信息
		try {
			Activity activity;
			ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
			activity = activityMapper.selectByPrimaryKey(id);
			return activity;
		} catch (ActivityException e){
			throw new ActivityException(e.UNKNOWN_ERROR);
		}
	}

	/**
	 * 传入景点ID，获取该景点所有活动信息
	 * @param spot_id  需要获取活动列表的景点ID
	 * @return
	 */
	@Transactional
	public List<Activity> getActivitiesBySpotId(int spot_id) {
		// 检查输入是否为空
		if (spot_id <= 0) {
			throw new ActivityException(ActivityException.SPOT_ID_EMPTY_ERROR);
		}

		// 根据输入的景点ID，获取该景点所有活动
		try {
			ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
			ActivityExample activityExample = new ActivityExample();
			activityExample.createCriteria().andSpotIdEqualTo(spot_id);
			List<Activity> list = activityMapper.selectByExample(activityExample);
			return list;
		} catch (ActivityException e){
			throw new ActivityException(e.UNKNOWN_ERROR);
		}
	}

	/**
	 * 传入景点活动对象，更新景点信息
	 * @param activity  景点活动对象
	 */
	@Transactional
	public void updateActivity(Activity activity) {

		// 检查输入是否为空
		if (activity == null) {
			throw new ActivityException(ActivityException.ACTIVITY_ID_EMPTY_ERROR);
		}

		// 更新对应景点活动的信息
		try {
			ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
			activityMapper.updateByPrimaryKeySelective(activity);
		} catch (ActivityException e){
			throw new ActivityException(e.UNKNOWN_ERROR);
		}
	}
}