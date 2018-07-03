package com.hiddewieringa.elevator.domain.elevator.model

import com.hiddewieringa.elevator.domain.elevator.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventhandling.EventHandler
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ElevatorAggregate {

    @AggregateIdentifier
    private var id: ElevatorId? = null

    private var floor = 0L

    private constructor()

    @CommandHandler
    constructor(command: CreateElevator) {
        apply(ElevatorCreated(command.elevatorId))
    }

    @EventHandler
    fun on(event: ElevatorCreated) {
        this.id = event.elevatorId
    }

    @CommandHandler
    fun call(command: CallElevator) {
        apply(ElevatorCalled(command.elevatorId, command.floor))
    }

    @EventHandler
    fun elevatorCalled(event: ElevatorCalled) {
        this.floor = event.floor
    }

    @CommandHandler
    internal fun openDoors(openDoors: OpenDoors) {
        apply(ElevatorDoorsOpened(openDoors.elevatorId))
    }

    @CommandHandler
    internal fun closeDoors(closeDoors: CloseDoors) {
        apply(ElevatorDoorsClosed(closeDoors.elevatorId))
    }

}
