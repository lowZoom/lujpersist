package luj.persist.internal.session;

import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
    "luj.persist.internal",
    "luj.ava",
}, includeFilters = {
    @ComponentScan.Filter(Internal.class),
})
final class InjectConf {
  // NOOP
}
