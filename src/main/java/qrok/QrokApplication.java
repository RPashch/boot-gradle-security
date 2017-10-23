package qrok;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class QrokApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(QrokApplication.class, args);//.registerShutdownHook();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(QrokApplication.class);
	}
	

    /*@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource ds() {
        return DataSourceBuilder.create().build();
    }*/
 
}
