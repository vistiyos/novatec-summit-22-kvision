package de.gmbh.novatec.kvision

import de.gmbh.novatec.kvision.model.Game
import de.gmbh.novatec.kvision.service.IGameService
import io.kvision.remote.getService

object Model {

	private val gameService = getService<IGameService>()

	suspend fun loadGames() = gameService.loadGames()

	suspend fun saveGame(game: Game) {
		gameService.saveGame(game)
	}

}
