package no.finn.blackjack

import java.lang.IllegalArgumentException

internal class Deck(
    private val deckFilePath: String = "/BasicDeck.txt"
) {
    companion object {
        private val SERIES_OF_WHITESPACE_REGEX = Regex("(\\s+)")
    }

    private var offset = 0
    private var cards: List<Card> = generateDeckFromFile()

    //check for invalid strings (length wise)
    //bit hard to read maybe?
    private fun generateDeckFromFile() =
        Deck::class.java.getResource(deckFilePath)?.readText()?.replace(SERIES_OF_WHITESPACE_REGEX, "")
            ?.let { deckString ->
                listOf(*deckString.split(",").toTypedArray())
                    .map { Card(it.substring(0, 1), it.substring(1)) }
            } ?: throw IllegalArgumentException("Deck file path $deckFilePath does not contain a file")

    //should maybe shuffle deck when offset goes back to 0
    //should maybe throw an exception when deck runs out instead of playing it out
    fun draw() = cards[offset].also { offset = (offset + 1).mod(cards.size) }

    fun shuffle() {
        cards = cards.shuffled().also { offset = 0 }
    }
}