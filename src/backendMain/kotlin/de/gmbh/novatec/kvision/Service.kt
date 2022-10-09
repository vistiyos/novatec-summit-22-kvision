package de.gmbh.novatec.kvision

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class PingService : IPingService {

    override suspend fun ping(message: String): String {
        println(message)
        return "Hello world from server!"
    }
}
