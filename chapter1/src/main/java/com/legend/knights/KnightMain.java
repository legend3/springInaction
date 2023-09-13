package com.legend.knights;

import com.legend.knights.config.KnightConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KnightMain {

	public static void main(String[] args) throws Exception {

//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/knight.xml");
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/minstrel.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
		com.legend.knights.Knight knight = context.getBean(com.legend.knights.Knight.class);
		knight.embarkOnQuest();
		context.close();
	}

}
