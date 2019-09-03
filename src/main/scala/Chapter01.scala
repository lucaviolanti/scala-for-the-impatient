object Chapter01 {
  // 1. In the Scala REPL, type `3.` followed by the Tab key. WHat methods can be applied?
  /*
    scala> 3.
    !=   -    ==    abs            doubleValue   formatted    isInstanceOf    isValidInt     min         shortValue       toChar        toLong          unary_-      →
    ##   ->   >     asInstanceOf   ensuring      getClass     isNaN           isValidLong    ne          signum           toDegrees     toOctalString   unary_~
    %    /    >=    byteValue      eq            hashCode     isNegInfinity   isValidShort   notify      synchronized     toDouble      toRadians       underlying
    &    <    >>    ceil           equals        intValue     isPosInfinity   isWhole        notifyAll   to               toFloat       toShort         until
    *    <<   >>>   compare        floatValue    isInfinite   isValidByte     longValue      round       toBinaryString   toHexString   toString        wait
    +    <=   ^     compareTo      floor         isInfinity   isValidChar     max            self        toByte           toInt         unary_+         |
   */

  // 2. In the Scala REPL, compute the square root of 3, and then square that value. By how much does the result differ from 3?
  // (Hint: The `res` variables are your friend)
  /*
    scala> math.sqrt(3)
    res0: Double = 1.7320508075688772

    scala> 3 - (res0 * res0)
    res1: Double = 4.440892098500626E-16
   */

  // 3. Are `res` variables `var` or `val`?
  /*
    scala> res0 = 2
    <console>:12: error: reassignment to val
           res0 = 2
                ^
   */

  // 4. Scala lets you multiply a string with a number—try out `"crazy" * 3` in the REPL.
  // What does this operation do? Where can you find it in Scaladoc?
  /*
    scala> "crazy" * 3
    res4: String = crazycrazycrazy
   */

  // 5. What does `10 max 2` mean? In which class is `max` defined?
  val res5 = 10 max 2
  /*
    // Returns `'''this'''` if `'''this''' > that` or `that` otherwise.
    def max(that: T): T = num.max(self, that)

    // From RichInt.scala /  ScalaNumberProxy.scala
   */

  // 6. Using `BigInt`, compute 2^1024
  val res6 = BigInt(2) pow 1024

  // 7. What do you need to import so that you can get a random prime as `probablePrime(100, Random)`, without any
  // qualifiers before `probablePrime` and `Random`?
  import BigInt.probablePrime
  import scala.util.Random
  val res7 = probablePrime(100, Random)

  // 8. One way to create random file or directory names is to produce a random BigInt and convert it to base 36,
  // yielding a string such as "qsnvbevtomcj38o06kul". Poke around Scaladoc to find a way of doing this in Scala.
  val res8 = BigInt(util.Random.nextLong()).toString(36)

  // 9. How do you get the first character of a string in Scala? The last character?
  val myString = "my string"
  val first = myString.head
  val last = myString.charAt(myString.length - 1)

  // 10. What do the take, drop, takeRight, and dropRight string functions do?
  // What advantage or disadvantage do they have over using substring?
  val take1 = myString.take(1)
  val drop1 = myString.drop(1)
  val takeRight3 = myString.takeRight(3)
  val dropRight3 = myString.dropRight(3)

  // They don't throw `IndexOutOfBoundsException`.
}