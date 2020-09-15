package guru.springframework.sfgdi.configuration;

import guru.springframework.sfgdi.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class GreetingServiceConfig {

    @Bean("i18nService")
    @Profile("EN")
    I18nEnglishGreetingService getEnglishGreeting() {
        return new I18nEnglishGreetingService();
    }

    @Bean("i18nService")
    @Profile({"ES", "default"})
    I18NSpanishService getSpanishGreeting() {
        return new I18NSpanishService();
    }

    @Bean
    @Primary
    PrimaryGreetingService getPrimaryGreeting() {
        return new PrimaryGreetingService();
    }

    @Bean("propertyInjectedGreetingService")
    PropertyInjectedGreetingService getPropertyInjecedGreeting() {
        return new PropertyInjectedGreetingService();
    }

    @Bean("setterInjectedGreetingService")
    SetterInjectedGreetingService getSetterInjectedGreeting() {
        return new SetterInjectedGreetingService();
    }
}
