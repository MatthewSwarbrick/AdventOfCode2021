import org.junit.jupiter.api.Test

class AdditionTests {
    @Test
    fun `Example 1 snail pairs are added correctly`() {
        val input = listOf(
            "[[[[4,3],4],4],[7,[[8,4],9]]]",
            "[1,1]"
        )

        val expected = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 1)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 2 snail pairs are added correctly`() {
        val input = listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]"
        )

        val expected = "[[[[1,1],[2,2]],[3,3]],[4,4]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 3)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 3 snail pairs are added correctly`() {
        val input = listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]",
            "[5,5]"
        )

        val expected = "[[[[3,0],[5,3]],[4,4]],[5,5]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 4)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 4 snail pairs are added correctly`() {
        val input = listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]",
            "[5,5]",
            "[6,6]"
        )

        val expected = "[[[[5,0],[7,4]],[5,5]],[6,6]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 5)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 5 snail pairs are added correctly`() {
        val input = listOf(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
            "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
            "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
            "[7,[5,[[3,8],[1,4]]]]",
            "[[2,[2,2]],[8,[8,1]]]",
            "[2,9]",
            "[1,[[[9,3],9],[[9,0],[0,7]]]]",
            "[[[5,[7,4]],7],1]",
            "[[[[4,2],2],6],[8,7]]"
        )

        val expected = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 9)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n${actual.last()}" }
    }

    @Test
    fun `Example 6 snail pairs are added correctly`() {
        val input = listOf(
            "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
            "[[[5,[2,8]],4],[5,[[9,9],0]]]",
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
            "[[[[5,4],[7,7]],8],[[8,3],8]]",
            "[[9,3],[[9,9],[6,[4,9]]]]",
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"
        )

        val expected = "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 9)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n${actual.last()}" }
    }

    @Test
    fun `Example 7 snail pairs are added correctly`() {
        val input = listOf(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"
        )

        val expected = "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 1)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n${actual.last()}" }
    }

    @Test
    fun `Example 8 snail pairs are added correctly`() {
        val input = listOf(
            "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]",
            "[7,[5,[[3,8],[1,4]]]]"
        )

        val expected = "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 1)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n${actual.last()}" }
    }
}