package com.hiddewieringa.elevator.domain.person

import com.hiddewieringa.elevator.domain.person.model.PersonId
import org.axonframework.commandhandling.TargetAggregateIdentifier

data class PersonArrives(@field:TargetAggregateIdentifier val personId: PersonId, val floor: Long, val requestedFloor: Long) {
    init {
        if (floor == requestedFloor) {
            throw IllegalArgumentException("Floor and requested floor ($floor) cannot be the same.")
        }
    }
}

data class EnterElevator(@field:TargetAggregateIdentifier val personId: PersonId)

data class LeaveElevator(@field:TargetAggregateIdentifier val personId: PersonId)
