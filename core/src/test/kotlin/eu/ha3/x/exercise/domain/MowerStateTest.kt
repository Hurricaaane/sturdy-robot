package eu.ha3.x.exercise.domain

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

/**
 * (Default template)
 * Created on 2018-04-12
 *
 * @author Ha3
 */
public class MowerStateTest {
    @Test
    public fun `it should not create states with a negative position`() {
        // Exercise
        Assertions.assertAll(
            Executable {
                Assertions.assertThrows(IllegalArgumentException::class.java) {
                    Mower.State(-1, 1, Mower.Orientation.NORTH)
                }
            },
            Executable {
                Assertions.assertThrows(IllegalArgumentException::class.java) {
                    Mower.State(1, -1, Mower.Orientation.NORTH)
                }
            }
        )
    }

    @Test
    public fun `it should create a state with position 0,0`() {
        // Exercise
        val SUT = Mower.State(0, 0, Mower.Orientation.NORTH)

        // Verify
        org.assertj.core.api.Assertions.assertThat(SUT.xPosition).isEqualTo(0)
        org.assertj.core.api.Assertions.assertThat(SUT.yPosition).isEqualTo(0)
        org.assertj.core.api.Assertions.assertThat(SUT.orientation).isEqualTo(Mower.Orientation.NORTH)

    }

    @Test
    public fun `it should create a surface with position 5,10 and orientation south`() {
        // Exercise
        val SUT = Mower.State(5, 10, Mower.Orientation.SOUTH)

        // Verify
        org.assertj.core.api.Assertions.assertThat(SUT.xPosition).isEqualTo(5)
        org.assertj.core.api.Assertions.assertThat(SUT.yPosition).isEqualTo(10)
        org.assertj.core.api.Assertions.assertThat(SUT.orientation).isEqualTo(Mower.Orientation.SOUTH)
    }

    @Test
    public fun `it should be inside the surface`() {
        // Exercise and Verify
        assertAll(
            Executable { assertTrue(Mower.State(0, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { assertTrue(Mower.State(1, 1, Mower.Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { assertTrue(Mower.State(9, 4, Mower.Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should be outside the surface`() {
        // Exercise and Verify
        assertAll(
            Executable { assertFalse(Mower.State(0, 1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { assertFalse(Mower.State(1, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { assertFalse(Mower.State(1, 1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { assertFalse(Mower.State(2, 2, Mower.Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { assertFalse(Mower.State(10, 4, Mower.Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { assertFalse(Mower.State(9, 5, Mower.Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { assertFalse(Mower.State(10, 5, Mower.Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should verify data class`() {
        // Verify
        EqualsVerifier.forClass(Mower.State::class.java).verify()
    }
}