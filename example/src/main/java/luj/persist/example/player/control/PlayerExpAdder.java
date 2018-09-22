package luj.persist.example.player.control;

import org.omg.CORBA.NO_IMPLEMENT;
import org.springframework.stereotype.Service;

@Service
public class PlayerExpAdder {

  public void add(long addExp) {
    throw new NO_IMPLEMENT("add尚未实现");
  }
}
