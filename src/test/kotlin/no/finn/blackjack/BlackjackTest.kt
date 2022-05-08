package no.finn.blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

//just testing winners and not scores, because don't want to expose scores just for test purposes
internal class BlackjackTest {

    private fun Player.hitMultiple(n: Int, deck: Deck) {
        for (i in 1..n) {
            hit(deck.draw())
        }
    }

    @Test
    fun `if sam gets a blackjack they win`() {
        val actualDeck = Deck("/BlackjackTest/SamBlackjack.txt")
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck("/BlackjackTest/SamBlackjack.txt")
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        expectedSam.hitMultiple(2, expectedDeck)

        assertEquals(Player.SAM(), results.winner)
    }

    @Test
    fun `if dealer gets a blackjack they win`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/DealerBlackjack.txt"))
        val results = blackJack.playRoundOfBlackjack()
        assertEquals(Player.DEALER(), results.winner)
    }

    @Test
    fun `if both players have blackjack sam wins`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/BothBlackjack.txt"))
        val result = blackJack.playRoundOfBlackjack()
        assertEquals(Player.SAM(), result.winner)
    }

    @Test
    fun `if dealer gets a blackjack and sam has greater or equals 17, dealer wins`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/DealerBlackjackSamStands.txt"))
        val results = blackJack.playRoundOfBlackjack()
        assertEquals(Player.DEALER(), results.winner)
    }

    @Test
    fun `if sam busts dealer wins without any further moves`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/SamBusts.txt"))
        val result = blackJack.playRoundOfBlackjack()
        assertEquals(Player.DEALER(), result.winner)
    }

    @Test
    fun `if dealer busts sam wins`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/DealerBusts.txt"))
        val results = blackJack.playRoundOfBlackjack()
        assertEquals(Player.SAM(), results.winner)
    }

    @Test
    fun `if both players busts on starting hand dealer wins`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/BothBustStartingHand.txt"))
        val results = blackJack.playRoundOfBlackjack()
        assertEquals(Player.DEALER(), results.winner)
    }

    @Test
    fun `sam correctly stands at 17 and dealer stands when total is higher`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/SamStandAt17DealerDoesNot.txt"))
        val results = blackJack.playRoundOfBlackjack()
        assertEquals(Player.DEALER(), results.winner)
    }

    @Test
    fun `sam can stand just based on starting hand`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/SamStandStartingHand.txt"))
        val results = blackJack.playRoundOfBlackjack()
        assertEquals(Player.SAM(), results.winner)
    }

    @Test
    fun `sam wins without further moves if they get 21`() {
        val blackJack = Blackjack(Deck("/BlackjackTest/Both21.txt"))
        val results = blackJack.playRoundOfBlackjack()
        assertEquals(Player.SAM(), results.winner)
    }

}