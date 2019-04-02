package cn.mvtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling  //扫描定时任务开启
public class SneoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SneoaApplication.class, args);
	}
}
