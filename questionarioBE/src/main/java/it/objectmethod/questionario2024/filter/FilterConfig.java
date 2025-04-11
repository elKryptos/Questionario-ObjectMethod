package it.objectmethod.questionario2024.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to register AccessFilter as a Spring Bean.
 */
@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final AccessFilter accessFilter;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {

        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        // Set the filter instance
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(0);

        return registrationBean;
    }

    /**
     * Registers CustomFilter with the Spring context.
     *
     * @return FilterRegistrationBean for CustomFilter
     */
    @Bean
    public FilterRegistrationBean<AccessFilter> accessFilterRegistrationBean() {
        FilterRegistrationBean<AccessFilter> registrationBean = new FilterRegistrationBean<>();
        // Set the filter instance
        registrationBean.setFilter(accessFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
