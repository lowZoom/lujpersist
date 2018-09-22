package luj.persist.example.login.ui;

import java.util.Scanner;
import luj.persist.example.login.control.LoginRegisterer;
import luj.persist.example.player.control.PlayerExpAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUi {

  public void show() {
    System.out.println("输入账号：");
    String account = readInput();
    _playerRegisterer.register(account);

    for (int i = 0; i < 10000; i++) {
      _playerExpAdder.add(1);
    }
  }

  private String readInput() {
    try (Scanner in = new Scanner(System.in)) {
      return in.nextLine();
    }
  }

  @Autowired
  private LoginRegisterer _playerRegisterer;

  @Autowired
  private PlayerExpAdder _playerExpAdder;
}
