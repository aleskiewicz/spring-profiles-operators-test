package pl.aleskiewicz;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Profile("!a & b")
@RequiredArgsConstructor
@Slf4j
public class ConditionalBeanNotAAndB {

    final Environment env;

    @PostConstruct
    public void init() {
        log.error("Created bean {} with profiles {}", this.getClass().getName(), env.getActiveProfiles());
    }
}
