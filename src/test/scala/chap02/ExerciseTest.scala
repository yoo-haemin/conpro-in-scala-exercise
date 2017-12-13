package chap02

import utest._

class HelloSpec extends TestSuite {
  
  val tests = Tests {
    * - {
      assert(true)
    }
  }

}
