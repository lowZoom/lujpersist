package luj.persist.test;

import luj.test.LujTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = InjectTest.class)
@Configuration
@ComponentScan(basePackages = "luj.persist", lazyInit = true)
public abstract class InjectTest extends LujTest {

}
