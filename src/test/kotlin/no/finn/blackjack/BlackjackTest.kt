package no.finn.blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

//just testing winners and not scores, because don't want to expose scores just for test purposes
internal class BlackjackTest {

    private fun Player.hitMultiple(n: Int, deck: Deck) {
        for (i in 1..n) {
            hit(deck.draw())
        }
    }

    private fun initializeRound(sam: Player, dealer: Player, deck: Deck) {
        sam.hit(deck.draw())
        dealer.hit(deck.draw())
        sam.hit(deck.draw())
        dealer.hit(deck.draw())
    }

    @Test
    fun `if sam gets a blackjack they win instantly`() {
        val deckFilepath = "/BlackjackTest/SamBlackjack.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.winner) },
            { assertEquals(expectedDealer, results.loser) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `if dealer gets a blackjack they win instantly`() {
        val deckFilepath = "/BlackjackTest/DealerBlackjack.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.loser) },
            { assertEquals(expectedDealer, results.winner) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `if both players have blackjack sam wins instantly`() {
        val deckFilepath = "/BlackjackTest/BothBlackjack.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.winner) },
            { assertEquals(expectedDealer, results.loser) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `if dealer gets a blackjack and sam has greater or equals 17, dealer wins instantly`() {
        val deckFilepath = "/BlackjackTest/DealerBlackjackSamStands.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.loser) },
            { assertEquals(expectedDealer, results.winner) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `if sam busts dealer wins without any further moves`() {
        val deckFilepath = "/BlackjackTest/SamBusts.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)
        expectedSam.hitMultiple(1, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.loser) },
            { assertEquals(expectedDealer, results.winner) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `if dealer busts sam wins`() {
        val deckFilepath = "/BlackjackTest/DealerBusts.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)
        expectedSam.hitMultiple(1, expectedDeck)
        expectedDealer.hitMultiple(3, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.winner) },
            { assertEquals(expectedDealer, results.loser) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `if both players bust on starting hand dealer wins`() {
        val deckFilepath = "/BlackjackTest/BothBustStartingHand.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.loser) },
            { assertEquals(expectedDealer, results.winner) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `sam correctly stands at 17 and dealer stands when their score is higher than sam's`() {
        val deckFilepath = "/BlackjackTest/SamStandAt17DealerDoesNot.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)
        expectedSam.hitMultiple(1, expectedDeck)
        expectedDealer.hitMultiple(2, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.loser) },
            { assertEquals(expectedDealer, results.winner) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `sam can stand just based on starting hand`() {
        val deckFilepath = "/BlackjackTest/SamStandStartingHand.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)
        expectedDealer.hitMultiple(1, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.winner) },
            { assertEquals(expectedDealer, results.loser) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `sam wins without further moves if they get 21`() {
        val deckFilepath = "/BlackjackTest/Both21.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)
        expectedSam.hitMultiple(2, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.winner) },
            { assertEquals(expectedDealer, results.loser) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

    @Test
    fun `sam stand on starting hand even if dealer has a higher score`() {
        val deckFilepath = "/BlackjackTest/SamLoseStartingHandNoBustOrBlackjack.txt"

        val actualDeck = Deck(deckFilepath)
        val blackJack = Blackjack(actualDeck)
        val results = blackJack.playRoundOfBlackjack()

        val expectedDeck = Deck(deckFilepath)
        val expectedSam = Player.SAM()
        val expectedDealer = Player.DEALER()

        initializeRound(expectedSam, expectedDealer, expectedDeck)

        assertAll(
            { assertEquals(expectedSam, results.loser) },
            { assertEquals(expectedDealer, results.winner) },
            { assertEquals(expectedDeck, actualDeck)}
        )
    }

}