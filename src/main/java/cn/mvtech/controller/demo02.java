package cn.mvtech.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:/param.properties","file:${user.dir}/config/param.properties"},ignoreResourceNotFound=true)
public class demo02 {
	/*@Autowired
    private StringRedisTemplate template;
    
	@Scheduled(cron = "${cronTaacCertDelayTaskJob}")
	private void TaacCertDelay() throws ParseException {
		System.out.println("--------开始-------");
//		template.convertAndSend("mytopic", "这是我发第1条的消息啊!!!!!!!!!!!!!");
		System.out.println("--------结束-------");
	}*/

	
	
}
