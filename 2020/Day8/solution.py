# https://adventofcode.com/2020/day/5

with open('input.txt') as f:
    lines = list(f.readlines())

def actions():
    return [parseAction(l) for l in lines]

def parseAction(l):
    act = l.split()
    return (act[0], int(act[1]))

def calc(actions):
    visited = set()
    currPos = 0
    sum = 0
    while currPos not in visited and currPos < len(actions):
        visited.add(currPos)
        (act, num) = actions[currPos]
        if act == "nop":
            currPos += 1
        elif act == "acc":
            sum += num
            currPos += 1
        else:
            currPos += num
    return (sum, currPos == len(actions))

def variate(actions):
    candidates = []
    for (idx, (act, num)) in enumerate(actions):
        if act == "nop":
            cp = list(actions)
            cp[idx] = ("jmp", num)
            candidates.append(cp)
        if act == "jmp":
            cp = list(actions)
            cp[idx] = ("nop", num)
            candidates.append(cp)
    return candidates


def part1():
    (sum, _) = calc(actions())
    print(sum)

def part2():
    variations = variate(actions())

    for v in variations:
        (sum, finished) = calc(v)
        if finished:
            print(sum)

part1()
part2()