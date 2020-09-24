package guru.springframework.sfgdi.configuration;

import guru.springframework.sfgdi.beans.FakeDataSource;
import guru.springframework.sfgdi.beans.FakeJmsSourceData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
//@PropertySource("classpath:datasource.properties")
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {
    @Value("${guru.username}")
    private String userName;

    @Value("${guru.password}")
    private String password;

    @Value("${guru.dburl}")
    private String url;

    @Value("${guru.jms.username}")
    private String jmsUser;

    @Value("${guru.jms.password}")
    private String jmsPassword;

    @Value("${guru.jms.url}")
    private String jmsUrl;

    @Value("${spring.profiles.active}")
    private List<String> profile;

    private Environment environment;

    public PropertyConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public FakeDataSource datasource() {
        FakeDataSource fakeDataSource = new FakeDataSource();
        System.out.println("Bean user "+userName);
        fakeDataSource.setUser(userName);
        System.out.println("Property User => " + userName);
        System.out.println("Environment User => " + environment.getProperty("DEFAULT_USER"));
        fakeDataSource.setUser(environment.getProperty("DEFAULT_USER")); // Override
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }

    @Bean
    public FakeJmsSourceData jmsSourceData() {
        FakeJmsSourceData sourceData = new FakeJmsSourceData();
        sourceData.setUser(jmsUser);
        sourceData.setPassword(jmsPassword);
        sourceData.setUrl(jmsUrl);
        return sourceData;
    }

    @Bean
    MailConfig mailConfig() {
        return new MailConfig();
    }

   /* @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/
}
