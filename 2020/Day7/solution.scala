// https://adventofcode.com/2020/day/7

val lines = io.Source.fromFile("input.txt").getLines().toList

val pattern = """(no|\d+) (\w+(?: \w+)) bags?""".r

def parse(l: String): Seq[(Int, String, String)] = {
    val sects = l.split(" bags contain ");
    val container = sects(0);
    val contained = sects(1).stripSuffix(".").split(", ").toSeq;
    val res = for (c <- contained) yield {
        c match {
            case pattern(num, color) => {
                if (num != "no") {
                    Some((num.toInt, color, container))
                } else {
                    Some((0, color, container))
                }
            }
            case _ => None
        }
    }
    res.flatten
}

def part1() = {
    val dependencyMap = scala.collection.mutable.Map[String, Set[String]]();
    for (l <- lines) {
        for (pair <- parse(l).map(a => (a._2, a._3))) {
            dependencyMap.put(pair._1, dependencyMap.getOrElse(pair._1, Set()) + pair._2)
        }
    }
    val visited = scala.collection.mutable.Buffer[String]()
    val queue = scala.collection.mutable.Queue[String]()
    queue ++= dependencyMap("shiny gold")
    while (!queue.isEmpty) {
        val e = queue.dequeue()
        if (!visited.contains(e)) {
            visited += e
            queue ++= dependencyMap.getOrElse(e, Set())
        }
    }
    println(visited.size)
}

def part2() = {
    val containmentMap = scala.collection.mutable.Map[String, Set[(String, Int)]]();
    for (l <- lines) {
        for (tuple <- parse(l)) {
            containmentMap.put(tuple._3, containmentMap.getOrElse(tuple._3, Set()) + ((tuple._2, tuple._1)))
        }
    }
    var visited = Seq[(String, Int)]()
    val queue = scala.collection.mutable.Queue[(String, Int)]()
    queue ++= containmentMap("shiny gold")
    while (!queue.isEmpty) {
        val (e, cnt) = queue.dequeue()
        visited = visited :+ (e, cnt)
        val mustHave = containmentMap.getOrElse(e, Set())
        queue ++= mustHave.map(a => (a._1, a._2 * cnt))
    }
    println(visited.map(_._2).sum)
}

part1()
part2()