package eu.ha3.x.exercise.domain

import nl.jqno.equalsverifier.EqualsVerifier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

/**
 * Mower Test.
 * Created on 2018-04-11
 *
 * @author Ha3
 */
public class SurfaceTest {
    @Test
    public fun `it should not create surfaces with a negative size`() {
        // Exercise and Verify
        assertAll(
            Executable {
                assertThrows(IllegalArgumentException::class.java) {
                    Surface(-1, 1)
                }
            },
            Executable {
                assertThrows(IllegalArgumentException::class.java) {
                    Surface(1, -1)
                }
            }
        )
    }

    @Test
    public fun `it should not create surfaces with size equal to zero`() {
        // Exercise and Verify
        assertAll(
            Executable {
                assertThrows(IllegalArgumentException::class.java) {
                    Surface(0, 1)
                }
            },
            Executable {
                assertThrows(IllegalArgumentException::class.java) {
                    Surface(1, 0)
                }
            }
        )
    }

    @Test
    public fun `it should create a surface of dimensions 1 by 1`() {
        // Exercise
        val SUT = Surface(1, 1)

        // Verify
        assertThat(SUT.xSize).isEqualTo(1)
        assertThat(SUT.ySize).isEqualTo(1)
    }

    @Test
    public fun `it should create a surface of dimensions 5 by 10`() {
        // Exercise
        val SUT = Surface(5, 10)

        // Verify
        assertThat(SUT.xSize).isEqualTo(5)
        assertThat(SUT.ySize).isEqualTo(10)
    }

    @Test
    public fun `it should create a surface of dimensions 6 by 11 using upper-right coordinate`() {
        // Exercise
        val SUT = Surface.withUpperRightCoordinate(5, 10)

        // Verify
        assertThat(SUT.xSize).isEqualTo(6)
        assertThat(SUT.ySize).isEqualTo(11)
    }

    @Test
    public fun `it should create a surface of dimensions 1 by 1 using upper-right coordinate`() {
        // Exercise
        val SUT = Surface.withUpperRightCoordinate(0, 0)

        // Verify
        assertThat(SUT.xSize).isEqualTo(1)
        assertThat(SUT.ySize).isEqualTo(1)
    }

    @Test
    public fun `it should verify data class`() {
        // Verify
        EqualsVerifier.forClass(Surface::class.java).verify()
    }
}