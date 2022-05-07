package no.finn.blackjack

internal class Blackjack(
    private val deck: Deck
) {

    private fun startRound(sam: Player, dealer: Player): Player? {
        sam.hit(draw())
        sam.hit(draw())

        dealer.hit(draw())
        dealer.hit(draw())

        //if both have 21 sam wins
        if (sam.twentyOne()) return sam
        if (dealer.twentyOne()) return dealer

        //if both bust dealer wins
        if (sam.bust()) return dealer
        if (dealer.bust()) return sam

        return null
    }

    fun playRoundOfBlackjack(shuffle: Boolean = false): Player {
        if (shuffle) deck.shuffle()

        val sam = Player.SAM()
        val dealer = Player.DEALER()

        startRound(sam, dealer)?.let { winner ->
            if (winner == Player.SAM()) printOutcome("SAM", sam, dealer)
            if (winner == Player.DEALER()) printOutcome("DEALER", sam, dealer)
            return winner
        } ?: run {
            //could also check for 21 from sam here but gonna let it play out naturally
            val samScore = playOutRound(16, sam) ?: run {
                printOutcome("DEALER", sam, dealer)
                return dealer
            }
            playOutRound(samScore, dealer)?.let {
                printOutcome("DEALER", sam, dealer)
                return dealer
            } ?: run {
                printOutcome("SAM", sam, dealer)
                return sam
            }
        }
    }

    private fun playOutRound(standThreshold: Int, player: Player): Int? {
        while (!player.stand(standThreshold)) {
            player.hit(draw())
        }
        if (player.bust()) return null
        return player.totalScore()
    }

    private fun printOutcome(winner: String, sam: Player, dealer: Player) = println(
        "WINNER: $winner\n" +
                "$sam\n" +
                "$dealer"
    )

    private fun draw() = deck.draw()
}