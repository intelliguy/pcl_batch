package com.skt.cloud.ext_batch.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

	
/**
* 운영비 DAO
* @name OperatingPriceBatchDao
* @author Hyeonjeong Jo
* @date 2016. 1. 18.
*/
@Repository
public class OperatingPriceBatchDao {
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;
	
	/**
	* 미터링을 조회
	* @method selectMeteringList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectMeteringList(Map<String, Object> mapParam) {
		return sqlSession.selectList("operatingPriceBatchSQL.selectMeteringList", mapParam);
	}
	
	/**
	* 빌링 카운트조회
	* @method selectBillingCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public int selectBillingCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("operatingPriceBatchSQL.selectBillingCount", mapParam);
	}
	
	/**
	* 빌링정보조회
	* @method insertBillingInfo
	* @param mapParam
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public void insertBillingInfo(Map<String, Object> mapParam) {
		sqlSession.insert("operatingPriceBatchSQL.insertBillingInfo", mapParam);
	}
	
	
	/**
	* 표준가격정보조회
	* @method selectPriceMaster
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public int selectPriceMaster(Map<String, Object> mapParam) {
		return sqlSession.selectOne("operatingPriceBatchSQL.selectPriceMaster", mapParam);
	}
	
	/**
	* 요율을 조회
	* @method selectProjectRateList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectProjectRateList() {
		return sqlSession.selectList("operatingPriceBatchSQL.selectProjectRateList");
	}
	
	/**
	* Server Consist 정보조회
	* @method selectServerConsistInfo
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectServerConsistInfo() {
		return sqlSession.selectList("operatingPriceBatchSQL.selectServerConsistInfo");
	}
	
	/**
	* Server_Consist_idx 조회 
	* @method selectServerConsistIdxList
	* @param project_id
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Integer> selectServerConsistIdxList(String project_id) {
		return sqlSession.selectList("operatingPriceBatchSQL.selectServerConsistIdxList", project_id);
	}
	
	/**
	* 가격정보조회
	* @method selectPriceList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectPriceList(Map<String, Object> mapParam) {
		return sqlSession.selectList("operatingPriceBatchSQL.selectPriceList", mapParam);
	}
	
}
