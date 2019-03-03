package com.hiddewieringa.elevator.domain.person.model

import com.hiddewieringa.elevator.domain.elevator.model.ElevatorId
import com.hiddewieringa.elevator.domain.person.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.EventHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class PersonAggregate {

    @AggregateIdentifier
    private lateinit var id: PersonId

    private var floor = 0L;
    private var requestedFloor = 0L;
    private var inElevator: ElevatorId? = null;

    private constructor()

    @CommandHandler
    constructor(command: PersonArrives) {
        apply(PersonArrived(command.personId, command.floor, command.requestedFloor))
    }

    @EventHandler
    fun handle(event: PersonArrived) {
        this.id = event.personId
        this.floor = event.floor
        this.requestedFloor = event.requestedFloor
    }

    @CommandHandler
    fun handle(command: EnterElevator) {
        if (inElevator != null) {
            throw IllegalArgumentException("Already in elevator")
        }
        apply(PersonEnteredElevator(command.personId, command.elevatorId))
    }

    @EventHandler
    fun handle(event: PersonEnteredElevator) {
        inElevator = event.elevatorId
    }

    @CommandHandler
    fun handle(command: LeaveElevator) {
        if (inElevator == null) {
            throw IllegalArgumentException("Not in elevator")
        }
        apply(PersonLeftElevator(command.personId, command.elevatorId))
    }

    @EventHandler
    fun handle(event: PersonLeftElevator) {
        inElevator = null
    }
}
