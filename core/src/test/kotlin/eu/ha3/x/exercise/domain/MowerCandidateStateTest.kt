package eu.ha3.x.exercise.domain

import nl.jqno.equalsverifier.EqualsVerifier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

/**
 * (Default template)
 * Created on 2018-04-12
 *
 * @author Ha3
 */
public class MowerCandidateStateTest {
    @Test
    public fun `it should create any candidate state`() {
        // Exercise
        val SUT = Mower.State(-1, -2, Mower.Orientation.NORTH)

        // Verify
        assertThat(SUT.xPosition).isEqualTo(-1)
        assertThat(SUT.yPosition).isEqualTo(-2)
        assertThat(SUT.orientation).isEqualTo(Mower.Orientation.NORTH)
    }

    @Test
    public fun `it should derive a state`() {
        // Exercise
        val SUT = Mower.State(1, 2, Mower.Orientation.SOUTH)

        // Verify
        assertThat(SUT.xPosition).isEqualTo(1)
        assertThat(SUT.yPosition).isEqualTo(2)
        assertThat(SUT.orientation).isEqualTo(Mower.Orientation.SOUTH)
    }

    @Test
    public fun `it should be inside the surface`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertTrue(Mower.State(0, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertTrue(Mower.State(1, 1, Mower.Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { Assertions.assertTrue(Mower.State(9, 4, Mower.Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should be outside the surface`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertFalse(Mower.State(0, 1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.State(1, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.State(1, 1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.State(2, 2, Mower.Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { Assertions.assertFalse(Mower.State(10, 4, Mower.Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { Assertions.assertFalse(Mower.State(9, 5, Mower.Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { Assertions.assertFalse(Mower.State(10, 5, Mower.Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should be outside the surface for any negative positions`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertFalse(Mower.State(-1, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.State(0, -1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.State(-1, -1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) }
        )
    }

    @Test
    public fun `it should verify data class`() {
        // Verify
        EqualsVerifier.forClass(Mower.State::class.java).verify()
    }
}