package com.skt.cloud.ext_batch.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

	
/**
* 미터링 관련 조회 맟  저장 DAO
* @name MeteringBatchDao
* @author joHyeonJeong
* @date 2016. 1. 15.
*/
@Repository
public class MeteringBatchDao {
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;
	
	/**
	* 프로젝트 정보를 조회
	* @method selectProjectList
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectProjectList() {
		return sqlSession.selectList("meteringBatchSQL.selectProjectList");
	}
	
	/**
	* 서버리스트를 조회
	* @method selectServerList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectServerList(Map<String, Object> mapParam) {
		return sqlSession.selectList("meteringBatchSQL.selectServerList", mapParam);
	}
	
	/**
	* 볼륨리스트를 조회 
	* @method selectVolumeList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectVolumeList(Map<String, Object> mapParam) {
		return sqlSession.selectList("meteringBatchSQL.selectVolumeList", mapParam);
	}
	
	
	/**
	* 스넵샷 카운트를 조회 
	* @method selectSnapshotCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public int selectSnapshotCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("meteringBatchSQL.selectSnapshotCount", mapParam);
	}
	
	/**
	* 미들워어 카운트를 조회
	* @method selectMiddlewareCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public int selectMiddlewareCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("meteringBatchSQL.selectMiddlewareCount", mapParam);
	}
	
	/**
	* 로드밸런스 정보를 조회
	* @method selectLbaasList
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public List<Map<String, Object>> selectLbaasList(Map<String, Object> mapParam) {
		return sqlSession.selectList("meteringBatchSQL.selectLbaasList", mapParam);
	}
	/**
	* 미터링 카운트를 조회  조회 
	* @method selectMeteringCount
	* @param mapParam
	* @return
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public int selectMeteringCount(Map<String, Object> mapParam) {
		return sqlSession.selectOne("meteringBatchSQL.selectMeteringCount", mapParam);
	}
	
	/**
	* 미터링 정보를 조회 
	* @method insertMeteringInfo
	* @param mapParam
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	public void insertMeteringInfo(Map<String, Object> mapParam) {
		sqlSession.insert("meteringBatchSQL.insertMeteringInfo", mapParam);
	}
	
}
