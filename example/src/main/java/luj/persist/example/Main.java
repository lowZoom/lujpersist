package luj.persist.example;

import luj.ava.spring.Internal;
import luj.persist.example.core.boot.GameBoot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {
    "luj.persist",
    "luj.ava",
}, includeFilters = {
    @ComponentScan.Filter(Internal.class),
    //
})
public class Main {

  public static void main(String[] args) {
    new Main().start();
  }

  private void start() {
    try (AnnotationConfigApplicationContext appCtx =
        new AnnotationConfigApplicationContext(Main.class)) {
      GameBoot ui = appCtx.getBean(GameBoot.class);
      ui.boot();
    }
  }
}
