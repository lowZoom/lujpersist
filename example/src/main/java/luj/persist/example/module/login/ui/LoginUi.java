package luj.persist.example.module.login.ui;

import java.util.Scanner;
import luj.persist.example.module.login.control.LoginRegisterer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUi {

  public void show() {
    System.out.println("输入账号：");
    String account = readInput();
    _playerRegisterer.register(account);

  }

  private String readInput() {
    try (Scanner in = new Scanner(System.in)) {
      return in.nextLine();
    }
  }

  @Autowired
  private LoginRegisterer _playerRegisterer;
}
