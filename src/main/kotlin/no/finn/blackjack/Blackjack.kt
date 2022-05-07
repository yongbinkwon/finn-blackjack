package no.finn.blackjack

internal class Blackjack(
    private val deck: Deck
) {

    private fun startRound(): Player? {
        return Player.SAM()
    }

    fun playRoundOfBlackjack(): Player {
        return Player.SAM()
    }
}