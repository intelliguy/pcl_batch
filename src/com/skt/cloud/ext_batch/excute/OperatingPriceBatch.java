package com.skt.cloud.ext_batch.excute;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skt.cloud.ext_batch.svc.OperatingPriceBatchSvc;

/**
 * 운영비 배치
 * @name OperatingPriceBatch
 * @author SPTEK
 * @date 2015. 10. 29.
 */
@Component(value="OperatingPriceBatch")
public class OperatingPriceBatch {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OperatingPriceBatchSvc operatingPriceBatchSvc;
	
	/**
	* 운영비 배치
	* @throws InterruptedException 
	* @method operatingPriceBatchJob
	* @history <pre> 
	* Date / Author / Description
	* 2015. 10. 29. / SPTEK / 변준일
	* </pre>
	*/
	public void operatingPriceBatchJob() throws InterruptedException{
		logger.info("============================ operatingPriceBatchJob Start ============================");
		
		Calendar now = Calendar.getInstance();
//		SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyyMMddkk");
		SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyyMMddHH");
		String today = startDateFormat.format(now.getTime());
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("date", today);
		
		//운영비 단가 마스터 테이블에서 조건(날짜,디폴트)에 맞는 키를 구한다.
		int priceIdx = operatingPriceBatchSvc.selectPriceMaster(mapParam);
		mapParam.put("price_idx", priceIdx);
		//해당키에 맞는 단가 정보를 구한다.
		List<Map<String, Object>> priceList = operatingPriceBatchSvc.selectPriceList(mapParam);
		Map<String, Integer> priceMap = new HashMap<String, Integer>();
		for(Map<String, Object> priceInfo : priceList) {
			priceMap.put((String)priceInfo.get("price_code_id"), (int)priceInfo.get("price"));
		}
		//프로젝트 할인요율정보를 구한다.
		List<Map<String, Object>> projectRateList = operatingPriceBatchSvc.selectProjectRateList();
		Map<String, Integer> projectRateMap = new HashMap<String, Integer>();
		for(Map<String, Object> projectRateInfo : projectRateList) {
			projectRateMap.put((String)projectRateInfo.get("project_id"), (int)projectRateInfo.get("rate"));
		}
		//OS, Middleware 단가정보를 구한다.
		List<Map<String, Object>> serverConsistList = operatingPriceBatchSvc.selectServerConsistInfo();
		Map<Integer, Integer> serverOsPriceMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> serverMwPriceMap = new HashMap<Integer, Integer>();
		for(Map<String, Object> serverConsistInfo : serverConsistList) {
			serverOsPriceMap.put(((BigInteger)serverConsistInfo.get("server_consist_idx")).intValue(), Integer.parseInt(String.valueOf(serverConsistInfo.get("server_os_op_price"))));
			serverMwPriceMap.put(((BigInteger)serverConsistInfo.get("server_consist_idx")).intValue(), Integer.parseInt(String.valueOf(serverConsistInfo.get("server_mw_op_price"))));
		}
		
		//해당날짜의 미터링 정보를 구한다.
		List<Map<String, Object>> meteringList = operatingPriceBatchSvc.selectMeteringList(mapParam);
		if(meteringList.size()==0) {
			logger.info("metering is not exists.");
		}
		
		int duplication = 0;
		int success = 0;
		
		for(Map<String, Object> meteringInfo : meteringList) {
			
			Map<String, Object> billingInfo = new HashMap<String, Object>();
			String project_id = (String)meteringInfo.get("project_id");
			billingInfo.put("project_id", project_id);
			
			// Add by sgkim0117-20160726
			int basic =  Integer.parseInt(String.valueOf(meteringInfo.get("basic"))) * priceMap.get("19");		// 기본 단가 추가
			
			int v_cpu_op = Integer.parseInt(String.valueOf(meteringInfo.get("v_cpu_op"))) * priceMap.get("1");
			int v_memory_op = Integer.parseInt(String.valueOf(meteringInfo.get("v_memory_op"))) * priceMap.get("2");
			int network_op = Integer.parseInt(String.valueOf(meteringInfo.get("network_op"))) * priceMap.get("3");
			int v_cpu_dev = Integer.parseInt(String.valueOf(meteringInfo.get("v_cpu_dev"))) * priceMap.get("4");
			int v_memory_dev = Integer.parseInt(String.valueOf(meteringInfo.get("v_memory_dev"))) * priceMap.get("5");
			int network_dev = Integer.parseInt(String.valueOf(meteringInfo.get("network_dev"))) * priceMap.get("6");
			int ip = Integer.parseInt(String.valueOf(meteringInfo.get("ip"))) * priceMap.get("13");
			int security_tool = Integer.parseInt(String.valueOf(meteringInfo.get("security_tool"))) * priceMap.get("14");
			int image = Integer.parseInt(String.valueOf(meteringInfo.get("image"))) * priceMap.get("15");
			int root = Integer.parseInt(String.valueOf(meteringInfo.get("root"))) * priceMap.get("7");
			int app = 0;
			if(priceMap.get("18")!=null) {
				app = Integer.parseInt(String.valueOf(meteringInfo.get("app"))) * priceMap.get("18");
			}
			int ssd = Integer.parseInt(String.valueOf(meteringInfo.get("ssd"))) * priceMap.get("8");
			int hdd = Integer.parseInt(String.valueOf(meteringInfo.get("hdd"))) * priceMap.get("9");
			int lbaas_bas = Integer.parseInt(String.valueOf(meteringInfo.get("lbaas_bas"))) * priceMap.get("10");
			int lbaas_bas_net = Integer.parseInt(String.valueOf(meteringInfo.get("lbaas_bas_net"))) * priceMap.get("16");
			int lbaas_spc = Integer.parseInt(String.valueOf(meteringInfo.get("lbaas_spc"))) * priceMap.get("11");
			int lbaas_spc_net = Integer.parseInt(String.valueOf(meteringInfo.get("lbaas_spc_net"))) * priceMap.get("17");
			int ta_manage_server_count = Integer.parseInt(String.valueOf(meteringInfo.get("ta_manage_server_count"))) * priceMap.get("12");
			int os = 0;
			int middleware = 0;
			List<Integer> serverConsistIdxList = operatingPriceBatchSvc.selectServerConsistIdxList(project_id);
			for(Integer serverConsistIdx : serverConsistIdxList) {
				os += serverOsPriceMap.get(serverConsistIdx);
				middleware += serverMwPriceMap.get(serverConsistIdx);
			}
			
			//할인요율 계산
			int projectRate = projectRateMap.get(project_id);
			if(projectRate>0) {
				basic = basic - (basic * projectRate / 100);
				v_cpu_op = v_cpu_op - (v_cpu_op * projectRate / 100);
				v_memory_op = v_memory_op - (v_memory_op * projectRate / 100);
				network_op = network_op - (network_op * projectRate / 100);
				v_cpu_dev = v_cpu_dev - (v_cpu_dev * projectRate / 100);
				v_memory_dev = v_memory_dev - (v_memory_dev * projectRate / 100);
				network_dev = network_dev - (network_dev * projectRate / 100);
				ip = ip - (ip * projectRate / 100);
				security_tool = security_tool - (security_tool * projectRate / 100);
				image = image - (image * projectRate / 100);
				root = root - (root * projectRate / 100);
				app = app - (app * projectRate / 100);
				ssd = ssd - (ssd * projectRate / 100);
				hdd = hdd - (hdd * projectRate / 100);
				lbaas_bas = lbaas_bas - (lbaas_bas * projectRate / 100);
				lbaas_bas_net = lbaas_bas_net - (lbaas_bas_net * projectRate / 100);
				lbaas_spc = lbaas_spc - (lbaas_spc * projectRate / 100);
				lbaas_spc_net = lbaas_spc_net - (lbaas_spc_net * projectRate / 100);
				ta_manage_server_count = ta_manage_server_count - (ta_manage_server_count * projectRate / 100);
				os = os - (os * projectRate / 100);
				middleware = middleware - (middleware * projectRate / 100);
			}
			
			billingInfo.put("basic", basic);
			billingInfo.put("v_cpu_op", v_cpu_op);
			billingInfo.put("v_memory_op", v_memory_op);
			billingInfo.put("network_op", network_op);
			billingInfo.put("v_cpu_dev", v_cpu_dev);
			billingInfo.put("v_memory_dev", v_memory_dev);
			billingInfo.put("network_dev", network_dev);
			billingInfo.put("ip", ip);
			billingInfo.put("security_tool", security_tool);
			billingInfo.put("image", image);
			billingInfo.put("root", root);
			billingInfo.put("app", app);
			billingInfo.put("ssd", ssd);
			billingInfo.put("hdd", hdd);
			billingInfo.put("lbaas_bas", lbaas_bas);
			billingInfo.put("lbaas_bas_net", lbaas_bas_net);
			billingInfo.put("lbaas_spc", lbaas_spc);
			billingInfo.put("lbaas_spc_net", lbaas_spc_net);
			billingInfo.put("ta_manage_server_count", ta_manage_server_count);
			billingInfo.put("os", os);
			billingInfo.put("middleware", middleware);
			billingInfo.put("price_idx", priceIdx);
			billingInfo.put("rate", projectRate);
			
			//DATE
			billingInfo.put("date", today);
			
			//REG_USER
			billingInfo.put("reg_user", "BATCH");
			
			logger.info("billingInfo : " + billingInfo.toString());
			
			if(operatingPriceBatchSvc.selectBillingCount(billingInfo)==0) {
				operatingPriceBatchSvc.insertBillingInfo(billingInfo);
				success++;
			}else{
				duplication++;
			}
			
		}	// for()
		
		logger.info("Processed date : " + today);
		logger.info("Total metering list : " + meteringList.size());
		logger.info("Duplication : " + duplication);
		logger.info("New inserted : " + success);
		logger.info("============================ operatingPriceBatchJob End =============================\n\n");
	}
	
}
