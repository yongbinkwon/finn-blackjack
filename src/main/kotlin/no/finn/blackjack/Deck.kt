package no.finn.blackjack

internal class Deck(
    private val deckFilePath: String = "/BasicDeck.txt"
) {
    private val cards: MutableList<Card> = generateDeckFromFile()

    private fun generateDeckFromFile() = mutableListOf<Card>()

    fun draw() = Card()

    fun shuffle() {

    }
}