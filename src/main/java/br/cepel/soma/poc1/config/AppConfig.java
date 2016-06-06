package br.cepel.soma.poc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/jpa-oracle.properties")
public class AppConfig {
	@Autowired
	Environment env;

	@Bean
	public TestBean testBean() {
		TestBean testBean = new TestBean();
		testBean.setDriverName(env.getProperty("spring.datasource.driver-class-name"));
		System.out.println(testBean);
		return testBean;
	}
}

class TestBean {
	private String driverName;

	public void setDriverName(String name) {
		this.driverName = name;
	}

	@Override
	public String toString() {
		return "TestBean [driverName=" + driverName + "]";
	}
}