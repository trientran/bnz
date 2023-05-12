package dev.mrtroy.schools

import dev.mrtroy.core.nextString
import dev.mrtroy.core.random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration

class FakeSchoolRepo : SchoolRepo {

    var fetchSchoolsDelay = Duration.ZERO

    var schools: List<School> = emptyList()

    var exception: Exception? = null

    override suspend fun schools(limit: Int): Flow<List<School>> {
        delay(fetchSchoolsDelay)
        exception?.let { throw it }
        schools = random.nextSchools()
        return flow { emit(schools) }
    }
}

fun Random.nextSchools() = List(3) {
    School(
        schoolId = random.nextInt(),
        schoolName = random.nextString(),
        phoneNumber = random.nextLong().toString(),
        emailAddress = random.nextString().plus("@").plus("email.com")
    )
}
