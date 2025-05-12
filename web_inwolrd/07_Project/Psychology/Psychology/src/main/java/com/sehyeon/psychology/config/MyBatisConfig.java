package com.sehyeon.psychology.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;

import lombok.RequiredArgsConstructor;

@Configuration  // Config(설정) 관련 클래스
@RequiredArgsConstructor
@MapperScan(basePackages = "com.sehyeon.psychology.mapper")  // Mapper를 스캔할 패키지
public class MyBatisConfig {
	
	// 커넥션 풀 및 MyBatis에 필요한 요소들을 메모리에 할당하고 관리
	// XML과 java 연동에 필요한 경로 관리
	private final ApplicationContext applicationContext;
	
	// 데이터베이스 연결 정보 추가 (jdbcUrl, username, password)
    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    // HikariCP 설정
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public HikariConfig hikariConfig() { return new HikariConfig(); }

    // DataSource 설정 (jdbcUrl 추가)
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(jdbcUrl)  							// jdbcUrl을 명확히 설정
                .username(username)  					// username 설정
                .password(password)  					// password 설정
                .driverClassName(driverClassName)  		// driverClass 설정
                .build();
    }

    // SQL Session Factory 설정
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(dataSource());

        // XML Mapper 경로 설정
        sfb.setMapperLocations(applicationContext.getResources("classpath*:/mapper/*.xml"));

        // MyBatis 설정 파일을 추가하는 경우
        sfb.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml")); 

        try {
            SqlSessionFactory factory = sfb.getObject();
            factory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return factory;
        } catch(Exception e) { 
            e.printStackTrace();
        }
        return null;
    }
}