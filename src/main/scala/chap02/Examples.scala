package chap02

object Examples {
  val log = (a: Any) =>
    println(s"${Thread.currentThread.getName}: $a")

  def thread(body: => Unit): Thread = {
    val t = new Thread { override def run() = body }
    t.start()
    t
  }
}

import Examples._

object ThreadsMain extends App {
  val t: Thread = Thread.currentThread   
  val name = t.getName
  println(s"I am the thread $name") 
} 

object ThreadsCreation extends App { 
  class MyThread extends Thread { 
    override def run(): Unit = { 
      println("New thread running.") 
    } 
  } 
  val t = new MyThread 
  t.start() 
  t.join() 
  println("New thread joined.") 
} 

object ThreadsSleep extends App { 
  val t = thread { 
    Thread.sleep(1000) 
    log("New thread running.") 
    Thread.sleep(1000) 
    log("Still running.") 
    Thread.sleep(1000) 
    log("Completed.") 
  } 
  t.join() 
  log("New thread joined.") 
} 

object ThreadsNondeterminism extends App { 
  val t = thread { log("New thread running.") } 
  log("...") 
  log("...") 
  t.join() 
  log("New thread joined.") 
} 

object ThreadsCommunicate extends App { 
  var result: String = null 
  val t = thread { result = "\nTitle\n" + "=" * 5 } 
  t.join() 
  log(result) 
} 

object ThreadSharedStateAccessReordering extends App { 
  for (i <- 0 until 100000) { 
    @volatile var a = false 
    @volatile var b = false 
    @volatile var x = -1 
    @volatile var y = -1 
    val t1 = thread {
      a = true 
      y = if (b) 0 else 1 
    }

    val t2 = thread {
      b = true 
      x = if (a) 0 else 1 
    } 
    t1.join() 
    t2.join() 
    assert(!(x == 1 && y == 1), s"x = $x, y = $y") 
  } 
} 

