package eu.ha3.x.exercise.runner

import eu.ha3.x.exercise.domain.Mower
import eu.ha3.x.exercise.domain.OnMowingExecutedListener
import eu.ha3.x.exercise.domain.Program

class ProgramRunner(private val onMowingExecutedListener: OnMowingExecutedListener) {
    fun execute(program: Program) {
        for (mowingInstruction in program.mowingInstructions) {
            val mower = Mower(program.surface, mowingInstruction.initialState).executeAll(mowingInstruction.commands)
            onMowingExecutedListener(mower.getCurrentState())
        }
    }
}