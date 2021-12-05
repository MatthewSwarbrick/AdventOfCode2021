import arrow.core.Tuple4
import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class LogicTests {
    @Test
    fun `Input line can be converted to coord points`() {
        val (x1, y1, x2, y2) = "1,2 -> 3,4".toCoords()

        assertEquals(1, x1)
        assertEquals(2, y1)
        assertEquals(3, x2)
        assertEquals(4, y2)
    }

    @Test
    fun `coords are converted to line points`() {
        val verticalLineCoords = Tuple4(1, 4, 1, 8)
        val horizontalLineCoords = Tuple4(4, 2, 1, 2)
        val diagonalLineCoords = Tuple4(1, 2, 3, 4)

        val verticalLine = verticalLineCoords.toLinePoints()
        val horizontalLine = horizontalLineCoords.toLinePoints()
        val diagonalLine = diagonalLineCoords.toLinePoints()

        assertEquals(verticalLine.size, 5)
        assertContains(verticalLine, Pair(1, 4))
        assertContains(verticalLine, Pair(1, 5))
        assertContains(verticalLine, Pair(1, 6))
        assertContains(verticalLine, Pair(1, 7))
        assertContains(verticalLine, Pair(1, 8))

        assertEquals(horizontalLine.size, 4)
        assertContains(horizontalLine, Pair(1, 2))
        assertContains(horizontalLine, Pair(2, 2))
        assertContains(horizontalLine, Pair(3, 2))
        assertContains(horizontalLine, Pair(4, 2))

        assertEquals(diagonalLine.size, 0)
    }

    @Test
    fun `diagonal coords are converted to line points`() {
        val diagonalLineCoords = Tuple4(1, 1, 3, 3)
        val diagonalLine = diagonalLineCoords.toLinePoints(ignoreDiagonal = false)

        val diagonalLineCoords2 = Tuple4(3, 3, 1, 1)
        val diagonalLine2 = diagonalLineCoords2.toLinePoints(ignoreDiagonal = false)

        assertEquals(3, diagonalLine.size)
        assertContains(diagonalLine, Pair(1, 1))
        assertContains(diagonalLine, Pair(2, 2))
        assertContains(diagonalLine, Pair(3, 3))

        assertEquals(3, diagonalLine2.size)
        assertContains(diagonalLine2, Pair(1, 1))
        assertContains(diagonalLine2, Pair(2, 2))
        assertContains(diagonalLine2, Pair(3, 3))
    }
}