package no.finn.blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BlackjackTest {

    @Test
    fun `if sam gets a blackjack they win`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/SamBlackjack.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.SAM(), winner) },
            { assertEquals(21, winner.totalScore()) }
        )
    }

    @Test
    fun `if dealer gets a blackjack they win`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/DealerBlackjack.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.DEALER(), winner) },
            { assertEquals(21, winner.totalScore()) }
        )
    }

    @Test
    fun `if both players have blackjack sam wins`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/BothBlackjack.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.SAM(), winner) },
            { assertEquals(21, winner.totalScore()) }
        )
    }

    @Test
    fun `if dealer gets a blackjack and sam has greater or equals 17, dealer wins`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/DealerBlackjackSamStands.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.DEALER(), winner) },
            { assertEquals(21, winner.totalScore()) }
        )
    }

    @Test
    fun `if sam busts dealer wins without any further moves`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/SamBusts.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.DEALER(), winner) },
            { assertEquals(4, winner.totalScore()) }
        )
    }

    @Test
    fun `if dealer busts sam win without further moves`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/DealerBusts.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.SAM(), winner) },
            { assertEquals(18, winner.totalScore()) }
        )
    }

    @Test
    fun `if both players busts on starting hand dealer wins`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/BothBustStartingHand.txt"))
        assertEquals(Player.DEALER(), blackJack.playRoundOfBlackjack())
    }

    @Test
    fun `sam correctly stands at 17 and dealer stands when total is higher`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/SamStandAt17DealerDoesNot.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.DEALER(), winner) },
            { assertEquals(19, winner.totalScore()) }
        )
    }

    @Test
    fun `sam can stand just based on starting hand`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/SamStandStartingHand.txt"))
        val winner = blackJack.playRoundOfBlackjack()
        assertAll(
            { assertEquals(Player.SAM(), winner) },
            { assertEquals(17, winner.totalScore()) }
        )
    }

}