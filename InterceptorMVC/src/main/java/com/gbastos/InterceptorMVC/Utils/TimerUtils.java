package com.gbastos.InterceptorMVC.Utils;

import java.util.Random;

/**
 * The Class TimerUtils abstracts utilitarians into methods.
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
public final class TimerUtils {

  /**
   * Do no instantiate the Utils class that only have Static method(s).
   */
  private TimerUtils() {
    super();
  }

  /**
   * Sleeps the Thread for a random time (500 Milliseconds).
   */
  public static void doThreadSleep() {

    try {

      Random rand = new Random();

      Thread.sleep(rand.nextInt(500));

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
