package com.skt.cloud.ext_batch.svc;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.cloud.ext_batch.dao.OperatingPriceBatchDao;

	
/**
* 운영비 배치 서비스
* @name OperatingPriceBatchSvc
* @author Hyeonjeong Jo
* @date 2016. 1. 18.
*/
@Service
public class OperatingPriceBatchSvc {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private OperatingPriceBatchDao operatingPriceBatchDao;
	
	public OperatingPriceBatchSvc() {
		logger.info("OperatingPriceBatchSvc created !!!!!");
	}
	
	/**
	* 미터링 리스트 조회
	* @method selectMeteringList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectMeteringList(Map<String, Object> mapParam) {
		return operatingPriceBatchDao.selectMeteringList(mapParam);
	}
	
	/**
	* 빌링 카운트 조회
	* @method selectBillingCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public int selectBillingCount(Map<String, Object> mapParam) {
		return operatingPriceBatchDao.selectBillingCount(mapParam);
	}
	
	/**
	* 빌링 정보 저장
	* @method insertBillingInfo
	* @param mapParam
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public void insertBillingInfo(Map<String, Object> mapParam) {
		operatingPriceBatchDao.insertBillingInfo(mapParam);
	}
	
	/**
	* 마스터 가격 정보 조회
	* @method selectPriceMaster
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public int selectPriceMaster(Map<String, Object> mapParam) {
		return operatingPriceBatchDao.selectPriceMaster(mapParam);
	}
	
	/**
	* 프로젝트 요율 리스트 조회
	* @method selectProjectRateList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectProjectRateList() {
		return operatingPriceBatchDao.selectProjectRateList();
	}
	
	/**
	* SERVER CONSIST 정보조회
	* @method selectServerConsistInfo
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectServerConsistInfo() {
		return operatingPriceBatchDao.selectServerConsistInfo();
	}
	
	/**
	* SERVER CONSIST IDX 조회
	* @method selectServerConsistIdxList
	* @param project_id
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Integer> selectServerConsistIdxList(String project_id) {
		return operatingPriceBatchDao.selectServerConsistIdxList(project_id);
	}
	
	/**
	* 가격 정보 조회
	* @method selectPriceList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 18. / Hyeonjeong Jo / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectPriceList(Map<String, Object> mapParam) {
		return operatingPriceBatchDao.selectPriceList(mapParam);
	}	
}
