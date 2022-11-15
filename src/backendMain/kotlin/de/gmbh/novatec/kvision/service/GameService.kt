package de.gmbh.novatec.kvision.service

import de.gmbh.novatec.kvision.model.Game

actual class GameService: IGameService {

	companion object {
		val games = mutableListOf(
			Game("God of War: Ragnarok"),
			Game("Elder Ring"),
			Game("Horizon Forbidden West"),
			Game("A Plague Tale: Requiem"),
			Game("Stray"),
			Game("Xenoblade Chronicles 3")
		)
	}

	override suspend fun loadGames(): List<Game> = games
	override suspend fun saveGame(game: Game) {
		games.add(game)
	}
}
