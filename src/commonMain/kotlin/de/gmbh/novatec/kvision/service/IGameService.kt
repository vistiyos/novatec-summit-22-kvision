package de.gmbh.novatec.kvision.service

import de.gmbh.novatec.kvision.model.Game
import io.kvision.annotations.*

@KVService
interface IGameService {
	@KVBinding(Method.GET, "loadGames")
	suspend fun loadGames(): List<Game>

	@KVBindingRoute("saveGame")
	suspend fun saveGame(game: Game)
}
