package com.hiddewieringa.elevator.projection.entity.person

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface PersonRepository : PagingAndSortingRepository<Person, Long> {

    fun findByUuid(uuid: UUID): Optional<Person>
}
