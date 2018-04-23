package eu.ha3.x.exercise.domain

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

/**
 * (Default template)
 * Created on 2018-04-23
 *
 * @author Ha3
 */
public class ProgramMowingInstructionTest {
    @Test
    public fun `it should verify data class`() {
        // Verify
        EqualsVerifier.forClass(Program.MowingInstruction::class.java).verify()
    }
}