# https://adventofcode.com/2020/day/1

with open('input.txt') as f:
    nums = [int(i) for i in f.readlines()]

def part1():
    candidates = set()
    for n in nums:
        if n in candidates:
            print(n * (2020 - n))
        else:
            candidates.add(2020 - n)

def part2():
    candidates = {}
    for i, n in enumerate(nums):
        if n in candidates:
            print(n * candidates[n][0] * candidates[n][1])
        else:
            for j in range(i):
                prev = nums[j]
                candidates[2020 - prev - n] = (prev, n)

part1()
part2()