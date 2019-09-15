package com.thebinaryheap.clientdebt;

import com.thebinaryheap.clientdebt.data.resources.Client;
import com.thebinaryheap.clientdebt.data.resources.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Configuration
  public class RestConfiguration {

    @Bean
    public FilterRegistrationBean corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
      config.addAllowedMethod(HttpMethod.DELETE.name());
      config.addAllowedMethod(HttpMethod.PUT.name());
      source.registerCorsConfiguration("/**", config);
      source.registerCorsConfiguration("/**/**", config);
      FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
      bean.setOrder(0);
      return bean;
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
      return new RepositoryRestConfigurer() {

        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
          config.exposeIdsFor(Transaction.class);
          config.exposeIdsFor(Client.class);
        }
      };
    }

  }
}
