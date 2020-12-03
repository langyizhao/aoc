const { readFileSync } = require('fs');

const input = readFileSync('input.txt', 'utf8').split('\n');

function countTrees(right, down) {
    let position = 0;
    let count = 0;
    
    for (let i = 0; i < input.length; i += down) {
        let line = input[i];
        let len = line.length;
        if (line[position % len] === '#') {
            count++;
        }
        position += right;
    }
    return count;
}

function part1() {
    console.log(countTrees(3, 1));
}

function part2() {
    result = 1;
    result *= countTrees(1, 1);
    result *= countTrees(3, 1);
    result *= countTrees(5, 1);
    result *= countTrees(7, 1);
    result *= countTrees(1, 2);
    console.log(result);
}

part1()
part2()