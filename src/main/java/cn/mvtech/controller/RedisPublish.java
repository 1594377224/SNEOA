package cn.mvtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisPublish {
    /*@Autowired
    private StringRedisTemplate template;
    @RequestMapping("publish")
	public String publish(){
		
        template.convertAndSend("mytopic", "这是我发第1条的消息啊!!!!!!!!!!!!!");
        
		return "结束";
	}*/
}
