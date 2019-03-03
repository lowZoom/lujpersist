package luj.persist.example;

import luj.persist.example.core.boot.GameBoot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("luj.persist.example")
public class Main {

  public static void main(String[] args) {
    new Main().start();
  }

  private void start() {
    try (AnnotationConfigApplicationContext appCtx =
        new AnnotationConfigApplicationContext(Main.class)) {
      appCtx.getBean(GameBoot.class).boot();
    }
  }
}
