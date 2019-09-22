object Chapter07 {
  // 1. Write an example program to demonstrate that
  // `package com.horstmann.impatient`
  // is not the same as
  // ```
  // package com
  // package horstmann
  // package impatient
  // ```
}
package com {

  object ComLevel {
    val a = 0
  }
  package horstmann {
    package impatient {

      object ImpatientLevel {
        println(ComLevel.a)
      }

    }

  }

}

package com.horstmann.impatient {

  object ImpatientLevel2 {
    // println(ComLevel.a)
    // it doesn't work
  }

}

// 2. Write a puzzler that baffles your Scala friends, using a package com that isn’t at the top level.
/* ??? */

// 3. Write a package `random` with functions `nextInt(): Int`, `nextDouble(): Double`, and `setSeed(seed: Int): Unit`
// To generate random numbers, use the linear congruential generator `next = (previous × a + b) mod 2^n`, where
// `a = 1664525`, `b = 1013904223`, `n = 32`, and the initial value of `previous` is seed.
package object random {
  private var seed: Int = 0

  def setSeed(seed: Int): Unit = {
    this.seed = seed
  }

  private val a = 1664525
  private val b = 1013904223
  private val n = 32

  private def next(): Int = {
    (seed * a + b) % math.pow(2, n).toInt
  }

  def nextInt(): Int = {
    seed = this.next()
    seed
  }

  def nextDouble(): Double = {
    seed = this.next()
    seed.toDouble
  }
}

object Chapter07Continued {
  // 4. Why do you think the Scala language designers provided the package object syntax instead of simply letting you
  // add functions and variables to a package?
  /* Google says it's a JVM limitation */

  // 5. What is the meaning of `private[com] def giveRaise(rate: Double)`? Is it useful?
  /* `giveRaise` is only accessible from inside the `com` package; it depends on what is inside `com`. */

  // 6. Write a program that copies all elements from a Java hash map into a Scala hash map. Use imports to rename both
  // classes.

  import java.util.{HashMap => JavaHashMap}

  import scala.collection.mutable.{HashMap => ScalaHashMap}

  def copier[A, B](from: JavaHashMap[A, B], to: ScalaHashMap[A, B]): Unit = {
    from.forEach(to.put)
  }

  // 7. In the preceding exercise, move all imports into the innermost scope possible.
  /* They are already. */

  // 8. What is the effect of
  // ```
  // import java._
  // import javax._
  // ```
  // Is this a good idea?
  /* Importing all java and javax packages. Not really, there will be naming conflicts. */

  // 9. Write a program that imports the `java.lang.System class`, reads the user name from the `user.name` system
  // property, reads a password from the `StdIn` object, and prints a message to the standard error stream if the
  // password is not "secret". Otherwise, print a greeting to the standard output stream. Do not use any other imports,
  // and do not use any qualified names (with dots).
  object program extends App {
      import java.lang.System._

      val name = getProperty("user.name")
      val userPassword: String = console.readLine
      if (!"secret".equals(userPassword)) {
        err.println("Wrong password!")
      } else {
        println(s"Hello, ${name}!")
      }
  }

  // 10. Apart from `StringBuilder`, what other members of `java.lang` does the `scala` package override?
  /* Loads */
}