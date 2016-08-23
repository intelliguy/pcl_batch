package com.skt.cloud.ext_batch.excute;

import org.springframework.context.support.ClassPathXmlApplicationContext;

	
/**
* 설정파일 로드 및 실행
* @name PrivateBatchMain
* @author joHyeonJeong
* @date 2016. 1. 15.
*/
public class PrivateBatchMain {

	/**
	* 설정파일 로드
	* @method main
	* @param args
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / 최초작성
	* </pre>
	*/
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		 new ClassPathXmlApplicationContext(new String[]{"private-data.xml", "private-schedule.xml"  });

	}

}
