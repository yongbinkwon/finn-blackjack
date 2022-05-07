package no.finn.blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `check that totalScore() matches cards gained from hit()`() {
        val player = Player.SAM()
        val deck = listOf(Card("C", "A"), Card("H", "2"), Card("D", "7"))
        deck.dropLast(1).forEach(player::hit)
        assertEquals(20, player.hit(deck.last()))
    }

    @Test
    fun `player stands correctly`() {
        val player = Player.DEALER()
        listOf(Card("C", "A"), Card("H", "2"), Card("D", "4"))
            .forEach(player::hit)
        assertAll(
            { assertTrue(player.stand(16)) },
            { assertTrue(player.stand(15)) },
            { assertFalse(player.stand(17)) },
        )
    }

    @Test
    //not really that necessary
    fun `string representation of player looks correct`() {
        val player = Player.DEALER()
        listOf(Card("C", "A"), Card("H", "2"), Card("D", "4"))
            .forEach(player::hit)
        val handStringRepresentation = "dealer: CA, H2, D4"
        assertEquals(handStringRepresentation, player.toString())
    }
}