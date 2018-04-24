package eu.ha3.x.exercise.domain

import eu.ha3.x.exercise.domain.Mower.Orientation
import eu.ha3.x.exercise.domain.Mower.State
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
        val SUT = State(-1, -2, Orientation.NORTH)

        // Verify
        assertThat(SUT.xPosition).isEqualTo(-1)
        assertThat(SUT.yPosition).isEqualTo(-2)
        assertThat(SUT.orientation).isEqualTo(Orientation.NORTH)
    }

    @Test
    public fun `it should derive a state`() {
        // Exercise
        val SUT = State(1, 2, Orientation.SOUTH)

        // Verify
        assertThat(SUT.xPosition).isEqualTo(1)
        assertThat(SUT.yPosition).isEqualTo(2)
        assertThat(SUT.orientation).isEqualTo(Orientation.SOUTH)
    }

    @Test
    public fun `it should be inside the surface`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertTrue(State(0, 0, Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertTrue(State(1, 1, Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { Assertions.assertTrue(State(9, 4, Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should be outside the surface`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertFalse(State(0, 1, Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(State(1, 0, Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(State(1, 1, Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(State(2, 2, Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { Assertions.assertFalse(State(10, 4, Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { Assertions.assertFalse(State(9, 5, Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { Assertions.assertFalse(State(10, 5, Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should be outside the surface for any negative positions`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertFalse(State(-1, 0, Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(State(0, -1, Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(State(-1, -1, Orientation.NORTH).isInside(Surface(1, 1))) }
        )
    }

    @Test
    public fun `it should verify data class`() {
        // Verify
        EqualsVerifier.forClass(State::class.java).verify()
    }
}