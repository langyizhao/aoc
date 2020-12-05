# https://adventofcode.com/2020/day/5

with open('input.txt') as f:
    lines = list(f.readlines())

def getIDs():
    ids = []
    for l in lines:
        binStr = ['1' if c == 'B' or c == 'R' else '0' for c in l if c in ['B', 'F', 'R', 'L']]
        id = int("".join(binStr), 2)
        ids.append(id)
    return ids

def part1():
    print(max(getIDs()))

def part2():
    sortedIds = sorted(getIDs())
    last = 0
    for id in sortedIds:
        if last == 0 or last == id - 1:
            last = id
        else:
            print(id - 1)
            break

part1()
part2()