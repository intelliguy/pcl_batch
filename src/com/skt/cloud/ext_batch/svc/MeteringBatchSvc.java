package com.skt.cloud.ext_batch.svc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.cloud.ext_batch.dao.MeteringBatchDao;

	
/**
* 미터링조회
* @name MeteringBatchSvc
* @author Hyeonjeong Jo
* @date 2016. 1. 18.
*/
@Service
public class MeteringBatchSvc {
	private Logger logger = Logger.getLogger(this.getClass());
	
	public MeteringBatchSvc() {
		logger.info("MeteringBatchSvc created !!!!!");
	}
	
	@Autowired
	private MeteringBatchDao meteringBatchDao;
	
	/**
	* 프로젝트 조회
	* @method selectProjectList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectProjectList(){
		return meteringBatchDao.selectProjectList();	
	}
	
	/**
	* 서버 항목 조회
	* @method selectProjectList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectServerList(Map<String, Object> mapParam) {
		return meteringBatchDao.selectServerList(mapParam);
	}
	
	/**
	* 볼륨항목조회
	* @method selectProjectList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectVolumeList(Map<String, Object> mapParam) {
		return meteringBatchDao.selectVolumeList(mapParam);
	}
	
	
	/**
	* 스넵샷 카운트 조회
	* @method selectSnapshotCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public int selectSnapshotCount(Map<String, Object> mapParam) {
		return meteringBatchDao.selectSnapshotCount(mapParam);
	}
	
	/**
	* 미들웨어 카운트 조회
	* @method selectMiddlewareCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public int selectMiddlewareCount(Map<String, Object> mapParam) {
		return meteringBatchDao.selectMiddlewareCount(mapParam);
	}
	
	/**
	* 로드밸런스 리스트 조회
	* @method selectLbaasList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectLbaasList(Map<String, Object> mapParam) {
		return meteringBatchDao.selectLbaasList(mapParam);
	}
	
	
	/**
	* 미터링 카운트 조회
	* @method selectMeteringCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public int selectMeteringCount(Map<String, Object> mapParam) {
		return meteringBatchDao.selectMeteringCount(mapParam);
	}
	
	/**
	* 미터링정보 저장
	* @method insertMeteringInfo
	* @param mapParam
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public void insertMeteringInfo(Map<String, Object> mapParam) {
		meteringBatchDao.insertMeteringInfo(mapParam);
	}
	
	
}
