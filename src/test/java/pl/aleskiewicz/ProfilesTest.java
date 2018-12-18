package pl.aleskiewicz;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Profiles;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProfilesTest {


    @Test
    public void notAandBShouldAcceptWhenBActive() throws Exception {
        // given
        Profiles notAAndB = Profiles.of("!a & b");
        AbstractEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("b");
        // when
        boolean acceptsProfiles = environment.acceptsProfiles(notAAndB);
        // then
        assertThat(acceptsProfiles).isTrue();
    }

    @Test
    public void bAndNotAShouldAcceptWhenBActive() throws Exception {
        // given
        Profiles bAndNotA = Profiles.of("b & !a");
        AbstractEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("b");
        // when
        boolean acceptsProfiles = environment.acceptsProfiles(bAndNotA);
        // then
        assertThat(acceptsProfiles).isTrue();
    }

    @Test
    public void bAndNotAShouldNotAcceptWhenAActive() throws Exception {
        // given
        Profiles bAndNotA = Profiles.of("b & !a");
        AbstractEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("a");
        // when
        boolean acceptsProfiles = environment.acceptsProfiles(bAndNotA);
        // then
        assertThat(acceptsProfiles).isFalse();
    }

    @Test
    public void notAandBShouldNotAcceptWhenAActive() throws Exception {
        // given
        Profiles notAAndB = Profiles.of("!a & b");
        AbstractEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("a");
        // when
        boolean acceptsProfiles = environment.acceptsProfiles(notAAndB);
        // then
        assertThat(acceptsProfiles).isFalse();
    }

    @Test
    public void bAndNotAShouldNotAcceptWhenABActive() throws Exception {
        // given
        Profiles bAndNotA = Profiles.of("b & !a");
        AbstractEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("a", "b");
        // when
        boolean acceptsProfiles = environment.acceptsProfiles(bAndNotA);
        // then
        assertThat(acceptsProfiles).isFalse();
    }

    @Test
    public void notAandBShouldNotAcceptWhenABActive() throws Exception {
        // given
        Profiles notAAndB = Profiles.of("!a & b");
        AbstractEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("a", "b");
        // when
        boolean acceptsProfiles = environment.acceptsProfiles(notAAndB);
        // then
        assertThat(acceptsProfiles).isFalse();
    }
}
