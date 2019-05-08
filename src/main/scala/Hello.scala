object Hello extends App {
  val ans = (1 to 100).foreach {
    case n if n % 15 == 0 => println("FizzBuzz")
    case n if n % 3 == 0 => println("Fizz")
    case n if n % 5 == 0 => println("Buzz")
    case i => println(i)
  }
//  println(ans)
  println("Hello!")
}