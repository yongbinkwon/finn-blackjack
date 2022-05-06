package no.finn.blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `check that totalScore() matches cards gained from hit()`() {
        val player = Player.SAM()
        listOf(Card("C", "A"), Card("H", "2"), Card("D", "7"))
            .forEach(player::hit)
        assertEquals(20, player.totalScore())
    }

    @Test
    fun `player stands correctly`() {
        val player = Player.DEALER()
        listOf(Card("C", "A"), Card("H", "2"), Card("D", "4"))
            .forEach(player::hit)
        assertAll(
            { assertTrue(player.stand(17)) },
            { assertTrue(player.stand(16)) },
            { assertFalse(player.stand(18)) },
        )
    }

    @Test
    //not really that necessary
    fun `string representation of player looks correct`() {
        val player = Player.DEALER()
        listOf(Card("C", "A"), Card("H", "2"), Card("D", "4"))
            .forEach(player::hit)
        val handStringRepresentation = "Dealer: CA, H2, D4"
        assertEquals(handStringRepresentation, player.toString())
    }
}