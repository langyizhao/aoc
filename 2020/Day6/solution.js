// https://adventofcode.com/2020/day/6

const { readFileSync } = require('fs');

const input = readFileSync('input.txt', 'utf8').split('\n\n');

function part1() {
    let sum = 0;
    for (entry of input) {
        let set = [...entry.trim()].filter(c => c !== '\n').reduce((acc, curr) => acc.add(curr), new Set());
        sum += set.size;
    }
    console.log(sum);
}

function part2() {
    let sum = 0;
    for (entry of input) {
        let group = entry.trim().split('\n');
        let res = group.reduce((acc, curr) => [...acc].filter(x => [...curr].includes(x)));
        console.log(res);
        sum += new Set(res).size;
    }
    console.log(sum);
}

part1()
part2()