fun main() {
    val input = puzzle1

    val totalIncreases = input.reduceIndexed { index, acc, curr ->
        if(curr > input[index - 1]) {
                acc + 1
        } else {
            acc
        }
    }

    print(totalIncreases - input[0])
}