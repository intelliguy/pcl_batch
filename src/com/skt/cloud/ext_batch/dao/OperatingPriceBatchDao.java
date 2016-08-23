package com.skt.cloud.ext_batch.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

	
/**
* ��� DAO
* @name OperatingPriceBatchDao
* @author Hyeonjeong Jo
* @date 2016. 1. 18.
*/
@Repository
public class OperatingPriceBatchDao {
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;
	
	/**
	* ���͸��� ��ȸ
	* @method selectMeteringList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectMeteringList(Map<String, Object> mapParam) {
		return sqlSession.selectList("operatingPriceBatchSQL.selectMeteringList", mapParam);
	}
	
	/**
	* ���� ī��Ʈ��ȸ
	* @method selectBillingCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public int selectBillingCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("operatingPriceBatchSQL.selectBillingCount", mapParam);
	}
	
	/**
	* ����������ȸ
	* @method insertBillingInfo
	* @param mapParam
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public void insertBillingInfo(Map<String, Object> mapParam) {
		sqlSession.insert("operatingPriceBatchSQL.insertBillingInfo", mapParam);
	}
	
	
	/**
	* ǥ�ذ���������ȸ
	* @method selectPriceMaster
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public int selectPriceMaster(Map<String, Object> mapParam) {
		return sqlSession.selectOne("operatingPriceBatchSQL.selectPriceMaster", mapParam);
	}
	
	/**
	* ������ ��ȸ
	* @method selectProjectRateList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectProjectRateList() {
		return sqlSession.selectList("operatingPriceBatchSQL.selectProjectRateList");
	}
	
	/**
	* Server Consist ������ȸ
	* @method selectServerConsistInfo
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectServerConsistInfo() {
		return sqlSession.selectList("operatingPriceBatchSQL.selectServerConsistInfo");
	}
	
	/**
	* Server_Consist_idx ��ȸ 
	* @method selectServerConsistIdxList
	* @param project_id
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Integer> selectServerConsistIdxList(String project_id) {
		return sqlSession.selectList("operatingPriceBatchSQL.selectServerConsistIdxList", project_id);
	}
	
	/**
	* ����������ȸ
	* @method selectPriceList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectPriceList(Map<String, Object> mapParam) {
		return sqlSession.selectList("operatingPriceBatchSQL.selectPriceList", mapParam);
	}
	
}
