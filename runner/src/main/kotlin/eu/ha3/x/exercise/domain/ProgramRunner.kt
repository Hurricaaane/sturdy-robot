package eu.ha3.x.exercise.domain

/**
 * (Default template)
 * Created on 2018-04-23
 *
 * @author Ha3
 */
typealias OnMowingExecutedListener = (Mower.State) -> Unit

class ProgramRunner(private val onMowingExecutedListener: OnMowingExecutedListener) {

    fun execute(program: Program) {
        for (mowingInstruction in program.mowingInstructions) {
            val mower = Mower(program.surface, mowingInstruction.state).executeAll(mowingInstruction.commands)
            onMowingExecutedListener(mower.getCurrentState())
        }
    }
}