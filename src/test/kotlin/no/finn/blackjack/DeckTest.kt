package no.finn.blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DeckTest {

    @Test
    fun `Deck properly generates a deck of cards based on file`() {
        val deck = Deck("/TestDeck.txt")
        assertAll(
            { assertEquals(Card("C", "A"), deck.draw()) },
            { assertEquals(Card("D", "4"), deck.draw()) },
            { assertEquals(Card("H", "7"), deck.draw()) },
            { assertEquals(Card("S", "J"), deck.draw()) },
        )
    }

    @Test
    fun `Deck resets when cards run out`() {
        val deck = Deck("/TestDeck.txt")
        for (i in 1..2) {
            assertAll(
                { assertEquals(Card("C", "A"), deck.draw()) },
                { assertEquals(Card("D", "4"), deck.draw()) },
                { assertEquals(Card("H", "7"), deck.draw()) },
                { assertEquals(Card("S", "J"), deck.draw()) },
            )
        }
    }

    @Test
    //chance of this failing with a 4 card deck is less than 1*10^-6 given that shuffle is properly randomized
    //not sure if necessary
    fun `shuffle is random`() {
        val deck1 = Deck("/TestDeck.txt")
        val deck2 = Deck("/TestDeck.txt")
        var count = 0
        for (i in 1..100) {
            deck1.shuffle()
            deck2.shuffle()
            if (deck1.draw() == deck2.draw()) {
                count += 1
            }
        }
        assertFalse(count >= 50)
    }
}