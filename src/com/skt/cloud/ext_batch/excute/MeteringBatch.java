package com.skt.cloud.ext_batch.excute;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skt.cloud.ext_batch.svc.MeteringBatchSvc;

/**
 * 미터링 배치
 * @name MeteringBatch
 * @author SPTEK
 * @date 2015. 10. 29.
 */
@Component(value="MeteringBatch")
public class MeteringBatch {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MeteringBatchSvc meteringBatchSvc;
	
	/**
	* 미터링 배치
	* @throws InterruptedException 
	* @method meteringBatchJob
	* @history <pre> 
	* Date / Author / Description
	* 2015. 10. 29. / SPTEK /최초작성
	* </pre>
	*/
	public void meteringBatchJob() throws InterruptedException{
		logger.info("============================ meteringBatchJob Start ============================");
		
		Calendar now = Calendar.getInstance();
//		SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyyMMddkk");
		SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyyMMddHH");
		String today = startDateFormat.format(now.getTime());
		
		List<Map<String, Object>> projectList = meteringBatchSvc.selectProjectList();
		if(projectList.size()==0) {
			logger.info("project is not exists.");
		}
		
		int duplication = 0;
		int success = 0;
		
		for(Map<String, Object> projectInfo : projectList) {
			
			Map<String, Object> meteringInfo = new HashMap<String, Object>();
			meteringInfo.put("project_id", projectInfo.get("project_id"));
			
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("project_id", projectInfo.get("project_id"));

			int basic = 0;
			int v_cpu_op = 0;
			int v_memory_op = 0;
			int network_op = 0;
			int v_cpu_dev = 0;
			int v_memory_dev = 0;
			int network_dev = 0;
			int ip = 0;
			int security_tool = 0;
			int image = 0;
			int middleware = 0;
			int root = 0;
			int app = 0;
			int ssd = 0;
			int hdd = 0;
			
			// 서버별 자원의 Count를 조회
			List<Map<String, Object>> serverList = meteringBatchSvc.selectServerList(mapParam);
			for(Map<String, Object> serverInfo : serverList) {
				
				// 서버별 기본 단가 Count
				basic = serverList.size();
				
				// 서버별 자원의 Count를 조회 시작 
				//QA서버 
				if("1".equals(serverInfo.get("op_type"))) {
					v_cpu_op += (int)serverInfo.get("v_cpu");				//CPU
					v_memory_op += (int)serverInfo.get("v_memory");		//MEMORY
					
				//개발서버
				}else if("2".equals(serverInfo.get("op_type"))) {
					v_cpu_dev += (int)serverInfo.get("v_cpu");				//CPU
					v_memory_dev += (int)serverInfo.get("v_memory");	//MEMORY
				}
				
				//공인IP
				if("1".equals(serverInfo.get("ip_kind"))) {
					ip++;
				}
				
				//보안툴 라이선스
				if("Y".equals(serverInfo.get("security_add_yn"))) {
					security_tool++;
				}
				// 서버별 자원의 Count를 조회 끝
				
				//스냅샷
				mapParam.put("kind", "1");
				mapParam.put("device_id", serverInfo.get("server_id"));
				image += meteringBatchSvc.selectSnapshotCount(mapParam);
				
				//Middleware
				mapParam.put("server_consist_idx", serverInfo.get("server_consist_idx"));
				middleware += meteringBatchSvc.selectMiddlewareCount(mapParam);
				
				mapParam.put("server_id", serverInfo.get("server_id"));
				List<Map<String, Object>> volumeList = meteringBatchSvc.selectVolumeList(mapParam);
				for(Map<String, Object> volumeInfo : volumeList) {
					if("1".equals(volumeInfo.get("volume_kind"))) {	//ROOT
						root++;
					}else if("2".equals(volumeInfo.get("volume_kind"))) {	//APP
						if("SSD".equals(volumeInfo.get("volume_type"))) {
							ssd++;
						}else if("HDD".equals(volumeInfo.get("volume_type"))) {
							hdd++;
						}
					}else if("3".equals(volumeInfo.get("volume_kind"))) {	//APP
						app++;
					}
				}
				
			}		// for()
			
			meteringInfo.put("basic", basic);
			meteringInfo.put("v_cpu_op", v_cpu_op);
			meteringInfo.put("v_memory_op", v_memory_op);
			meteringInfo.put("network_op", network_op);
			meteringInfo.put("v_cpu_dev", v_cpu_dev);
			meteringInfo.put("v_memory_dev", v_memory_dev);
			meteringInfo.put("network_dev", network_dev);
			meteringInfo.put("ip", ip);
			meteringInfo.put("security_tool", security_tool);
			meteringInfo.put("image", image);
			meteringInfo.put("root", root);
			meteringInfo.put("app", app);
			meteringInfo.put("ssd", ssd);
			meteringInfo.put("hdd", hdd);
			
			int lbaas_bas = 0;
			int lbaas_bas_net = 0;
			int lbaas_spc = 0;
			int lbaas_spc_net = 0;
			
			//로드밸런서
			List<Map<String, Object>> lbaasList = meteringBatchSvc.selectLbaasList(mapParam);
			for(Map<String, Object> lbaasInfo : lbaasList) {
				if("1".equals(lbaasInfo.get("pool_type"))) {	//�씪諛섑삎
					lbaas_bas++;
				}else if("2".equals(lbaasInfo.get("pool_type"))) {	//怨좉툒�삎
					lbaas_spc++;
				}
			}
			
			meteringInfo.put("lbaas_bas", lbaas_bas);
			meteringInfo.put("lbaas_bas_net", lbaas_bas_net);
			meteringInfo.put("lbaas_spc", lbaas_spc);
			meteringInfo.put("lbaas_spc_net", lbaas_spc_net);
			
			//TA service
			String manage_type = (String) projectInfo.get("manage_type");
			if("2".equals(manage_type)) {
				meteringInfo.put("ta_manage_server_count", serverList.size());
			}else{
				meteringInfo.put("ta_manage_server_count", 0);
			}
			
			//OS
			meteringInfo.put("os", serverList.size());
			
			//MIDDLEWARE
			meteringInfo.put("middleware", middleware);
			
			//DATE
			meteringInfo.put("date", today);
			
			//REG_USER
			meteringInfo.put("reg_user", "BATCH");
			
			logger.info("meteringInfo : " + meteringInfo.toString());
			
			if(meteringBatchSvc.selectMeteringCount(meteringInfo)==0) {
				meteringBatchSvc.insertMeteringInfo(meteringInfo);
				success++;
			}else{
				duplication++;
			}
			
		}
		
		logger.info("Processed date : " + today);
		logger.info("Total project : " + projectList.size());
		logger.info("Duplication : " + duplication);
		logger.info("New inserted : " + success);
		logger.info("============================ meteringBatchJob End =============================\n\n");
	}
	
}
