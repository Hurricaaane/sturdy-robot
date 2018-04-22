package eu.ha3.x.exercise.domain;

import eu.ha3.x.exercise.domain.Mower.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * (Default template)
 * Created on 2018-04-22
 *
 * @author Ha3
 */
public class AcceptanceMowerTest {
    @Test
    public fun `acceptance case 1`() {
        // Given
        val initialMower = Mower(Surface.withUpperRightCoordinate(5, 5), State(1, 2, Orientation.NORTH))

        // When
        val finalMower = initialMower.executeAll(listOf(Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD, Command.FORWARD))

        // Then
        assertThat(finalMower.getCurrentState()).isEqualTo(State(1, 3, Orientation.NORTH))
    }

    @Test
    public fun `acceptance case 2`() {
        // Given
        val initialMower = Mower(Surface.withUpperRightCoordinate(5, 5), State(3, 3, Orientation.EAST))

        // When
        val finalMower = initialMower.executeAll(listOf(Command.FORWARD, Command.FORWARD, Command.RIGHT, Command.FORWARD, Command.FORWARD, Command.RIGHT, Command.FORWARD, Command.RIGHT, Command.RIGHT, Command.FORWARD))

        // Then
        assertThat(finalMower.getCurrentState()).isEqualTo(State(5, 1, Orientation.EAST))
    }
}
