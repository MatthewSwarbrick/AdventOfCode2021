import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HorizontalTests {
    @Test
    fun `Down commands are ignored`() {
        val input = listOf(
            "down 5"
        )

        val actual = input.getHorizontal()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `Multiple Down commands are ignored`() {
        val input = listOf(
            "down 5",
            "down 3",
            "down 2"
        )

        val actual = input.getHorizontal()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `Up commands are ignored`() {
        val input = listOf(
            "up 5"
        )

        val actual = input.getHorizontal()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `Multiple Up commands are ignored`() {
        val input = listOf(
            "up 5",
            "up 3",
            "up 2"
        )

        val actual = input.getHorizontal()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `Forward command is calculated correctly`() {
        val input = listOf(
            "forward 5"
        )

        val actual = input.getHorizontal()
        assertEquals(expected = 5, actual)
    }

    @Test
    fun `Multiple forward commands are calculated correctly`() {
        val input = listOf(
            "forward 5",
            "forward 3",
            "forward 2"
        )

        val actual = input.getHorizontal()
        assertEquals(expected = 10, actual)
    }
}