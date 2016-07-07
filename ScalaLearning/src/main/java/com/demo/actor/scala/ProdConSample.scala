package com.demo.actor.scala

/**
  * Created by a549238 on 1/16/14.
  */
object ProdConSample {

  def main(args: Array[String]): Unit = {
    val drop = new Drop()
    new Thread(new Producer(drop)).start();
    new Thread(new Consumer(drop)).start();
  }

  class Producer(drop: Drop) extends Runnable {
    val importantInfo: Array[String] = Array(
      "Mares eat oats",
      "Does eat oats",
      "Little lambs eat ivy",
      "A kid will eat ivy too"
    )


    override def run(): Unit = {
      importantInfo.foreach((p) => drop.put(p))
      drop.put("DONE")
    }
  }

  class Consumer(drop: Drop) extends Runnable {
    def run(): Unit = {
      var message = drop.take()
      while (message != "Done") {
        System.out.format("message receive:%s %n", message)
        message = drop.take()
      }
    }
  }

  class Drop {
    var msg: String = ""
    var empty: Boolean = true
    var lock: AnyRef = new AnyRef();

    def put(message: String): Unit = {
      lock.synchronized(
        {
          await(empty == true)
          empty = false
          msg = message
          lock.notifyAll()
        }
      )
    }

    private def await(cond: => Boolean) =
      while (!cond) {
        lock.wait()
      }

    def take(): String = {
      lock.synchronized(
        {
          await(empty == false)
          empty = true
          lock.notifyAll()
          return msg
        }
      )
    }
  }

}
