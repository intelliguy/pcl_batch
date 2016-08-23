package com.skt.cloud.ext_batch.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

	
/**
* ���͸� ���� ��ȸ ��  ���� DAO
* @name MeteringBatchDao
* @author joHyeonJeong
* @date 2016. 1. 15.
*/
@Repository
public class MeteringBatchDao {
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;
	
	/**
	* ������Ʈ ������ ��ȸ
	* @method selectProjectList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectProjectList() {
		return sqlSession.selectList("meteringBatchSQL.selectProjectList");
	}
	
	/**
	* ��������Ʈ�� ��ȸ
	* @method selectServerList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectServerList(Map<String, Object> mapParam) {
		return sqlSession.selectList("meteringBatchSQL.selectServerList", mapParam);
	}
	
	/**
	* ��������Ʈ�� ��ȸ 
	* @method selectVolumeList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectVolumeList(Map<String, Object> mapParam) {
		return sqlSession.selectList("meteringBatchSQL.selectVolumeList", mapParam);
	}
	
	
	/**
	* ���ܼ� ī��Ʈ�� ��ȸ 
	* @method selectSnapshotCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public int selectSnapshotCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("meteringBatchSQL.selectSnapshotCount", mapParam);
	}
	
	/**
	* �̵���� ī��Ʈ�� ��ȸ
	* @method selectMiddlewareCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public int selectMiddlewareCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("meteringBatchSQL.selectMiddlewareCount", mapParam);
	}
	
	/**
	* �ε�뷱�� ������ ��ȸ
	* @method selectLbaasList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public List<Map<String, Object>> selectLbaasList(Map<String, Object> mapParam) {
		return sqlSession.selectList("meteringBatchSQL.selectLbaasList", mapParam);
	}
	/**
	* ���͸� ī��Ʈ�� ��ȸ  ��ȸ 
	* @method selectMeteringCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public int selectMeteringCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("meteringBatchSQL.selectMeteringCount", mapParam);
	}
	
	/**
	* ���͸� ������ ��ȸ 
	* @method insertMeteringInfo
	* @param mapParam
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	public void insertMeteringInfo(Map<String, Object> mapParam) {
		sqlSession.insert("meteringBatchSQL.insertMeteringInfo", mapParam);
	}
	
}
