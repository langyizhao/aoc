// https://adventofcode.com/2020/day/2

val lines = io.Source.fromFile("input.txt").getLines().toList

val pattern = """(\d+)-(\d+) (\w): (\w+)""".r

def parse(l: String)(f: (Int, Int, Char, String) => Int): Int = {
    l match {
        case pattern(fst, snd, c, password) => f(fst.toInt, snd.toInt, c.head, password)
        case _ => 0
    }
}

def part1() = {
    val res = for (l <- lines) yield parse(l) {
        (min, max, c, password) => {
            val count = password.filter(_ == c).size
            if (count >= min && count <= max) 1
            else 0
        }
    }
    println(res.sum)
}

def part2() = {
    val res = for (l <- lines) yield parse(l) {
        (pos1, pos2, c, password) => {
            val bool1 = password.lift(pos1-1) match {
                case Some(`c`) => true
                case _ => false
            }
            val bool2 = password.lift(pos2-1) match {
                case Some(`c`) => true
                case _ => false
            }
            if (bool1 ^ bool2) 1
            else 0
        }
    }
    println(res.sum)
}

part1()
part2()