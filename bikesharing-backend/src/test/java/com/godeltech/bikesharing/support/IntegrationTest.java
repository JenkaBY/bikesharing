package com.godeltech.bikesharing.support;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.github.database.rider.junit5.api.DBRider;
import com.godeltech.bikesharing.config.RabbitMqTestConfig;
import com.godeltech.bikesharing.config.TestContainerConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED) //test results will not disappear
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(classes = {TestContainerConfig.class,
    RabbitMqTestConfig.class})
@DBRider

public @interface IntegrationTest {
}
