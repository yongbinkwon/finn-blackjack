package no.finn.blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `players gets the correct card when hitting + string representation of hand is correct`() {
        val player = Player.DEALER()
        listOf(Card("C", "A"), Card("H", "2"), Card("D", "4"))
            .forEach(player::hit)
        val handStringRepresentation = "dealer: CA, H2, D4"
        assertEquals(handStringRepresentation, player.toString())
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

}