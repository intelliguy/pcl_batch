package com.skt.cloud.ext_batch.excute;

import org.springframework.context.support.ClassPathXmlApplicationContext;

	
/**
* �������� �ε� �� ����
* @name PrivateBatchMain
* @author joHyeonJeong
* @date 2016. 1. 15.
*/
public class PrivateBatchMain {

	/**
	* �������� �ε�
	* @method main
	* @param args
	* @history <pre> 
	* Date / Author / Description
	* 2016. 1. 15. / joHyeonJeong / �����ۼ�
	* </pre>
	*/
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		 new ClassPathXmlApplicationContext(new String[]{"private-data.xml", "private-schedule.xml"  });

	}

}
