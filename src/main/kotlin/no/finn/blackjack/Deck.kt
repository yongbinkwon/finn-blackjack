package no.finn.blackjack

import java.lang.IllegalArgumentException

internal class Deck(
    private val deckFilePath: String = "/BasicDeck.txt"
) {
    private var offset = 0
    private var cards: List<Card> = generateDeckFromFile()

    //check for invalid strings (length wise)
    //bit hard to read maybe?
    private fun generateDeckFromFile() =
        Deck::class.java.getResource(deckFilePath)?.readText()?.let { deckString ->
            listOf(*deckString.split(", ").toTypedArray())
                .map { Card(it.substring(0, 1), it.substring(1)) }
        } ?: throw IllegalArgumentException("Deck file path $deckFilePath does not contain a file")

    fun draw() = cards[offset].also { offset = (offset+1).mod(cards.size) }

    fun shuffle() {
        cards = cards.shuffled().also { offset = 0 }
    }
}