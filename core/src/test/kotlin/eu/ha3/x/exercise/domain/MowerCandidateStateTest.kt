package eu.ha3.x.exercise.domain

import nl.jqno.equalsverifier.EqualsVerifier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
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
        val SUT = Mower.CandidateState(-1, -2, Mower.Orientation.NORTH)

        // Verify
        assertThat(SUT.xPosition).isEqualTo(-1)
        assertThat(SUT.yPosition).isEqualTo(-2)
        assertThat(SUT.orientation).isEqualTo(Mower.Orientation.NORTH)
    }

    @Test
    public fun `it should derive a state`() {
        // Setup
        val SUT = Mower.CandidateState(1, 2, Mower.Orientation.SOUTH)

        // Exercise
        val result = SUT.asState()

        // Verify
        assertThat(result.xPosition).isEqualTo(1)
        assertThat(result.yPosition).isEqualTo(2)
        assertThat(result.orientation).isEqualTo(Mower.Orientation.SOUTH)
    }

    @Test
    public fun `it should fail to derive a state`() {
        // Setup
        val SUT = Mower.CandidateState(-1, 2, Mower.Orientation.SOUTH)

        // Exercise and Verify
        assertThrows(IllegalArgumentException::class.java) {
            SUT.asState()
        }
    }

    @Test
    public fun `it should be inside the surface`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertTrue(Mower.CandidateState(0, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertTrue(Mower.CandidateState(1, 1, Mower.Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { Assertions.assertTrue(Mower.CandidateState(9, 4, Mower.Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should be outside the surface`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertFalse(Mower.CandidateState(0, 1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(1, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(1, 1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(2, 2, Mower.Orientation.NORTH).isInside(Surface(2, 2))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(10, 4, Mower.Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(9, 5, Mower.Orientation.NORTH).isInside(Surface(10, 5))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(10, 5, Mower.Orientation.NORTH).isInside(Surface(10, 5))) }
        )
    }

    @Test
    public fun `it should be outside the surface for any negative positions`() {
        // Exercise and Verify
        Assertions.assertAll(
            Executable { Assertions.assertFalse(Mower.CandidateState(-1, 0, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(0, -1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) },
            Executable { Assertions.assertFalse(Mower.CandidateState(-1, -1, Mower.Orientation.NORTH).isInside(Surface(1, 1))) }
        )
    }

    @Test
    public fun `it should verify data class`() {
        // Verify
        EqualsVerifier.forClass(Mower.CandidateState::class.java).verify()
    }
}