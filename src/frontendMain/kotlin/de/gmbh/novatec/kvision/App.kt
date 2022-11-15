package de.gmbh.novatec.kvision

import de.gmbh.novatec.kvision.model.Game
import io.kvision.*
import io.kvision.core.*
import io.kvision.data.DataContainer
import io.kvision.data.dataContainer
import io.kvision.form.formPanel
import io.kvision.form.text.Text
import io.kvision.html.*
import io.kvision.panel.*
import io.kvision.state.observableListOf
import io.kvision.utils.px
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(window.asCoroutineDispatcher())

class App: Application() {

	override fun start(state: Map<String, Any>) {

		val games = observableListOf<Game>()

		AppScope.launch {
			Model.loadGames().forEach { games.add(it) }

			val boxStyle = Style {
				border = Border(1.px, BorderStyle.SOLID, Color.hex(0xdddddd))
				borderRadius = 20.px
				textAlign = TextAlign.CENTER
				padding = 10.px
			}

			root("kvapp") {
				div {
					h1("Game of Year 2022")
				}

				dataContainer(games, { game, _, _ ->
					div {
						addCssStyle(boxStyle)
						span(game.name)
					}
				}, container = HPanel(spacing = 10, wrap = FlexWrap.WRAP))

				val formGame = formPanel {
					borderTop = Border(1.px, BorderStyle.DASHED, Color.hex(0xcccccc))
					marginTop = 10.px
					add(Game::name, Text(label = "Game name"))
				}

				formGame.add(Button("Save").onClick {
					games.add(formGame.getData())
					launch {
						Model.saveGame(formGame.getData())
						formGame.clearData()
					}
				})

			}
		}
	}
}

fun main() {
	startApplication(
		::App,
		module.hot,
		BootstrapModule,
		BootstrapCssModule,
		CoreModule
	)
}
