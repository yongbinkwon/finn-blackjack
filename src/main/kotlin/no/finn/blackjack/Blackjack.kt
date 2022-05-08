package no.finn.blackjack

internal class Blackjack(
    private val deck: Deck = Deck()
) {

    private fun startRound(sam: Player, dealer: Player): BlackjackResult? {
        sam.hit(draw())
        sam.hit(draw())

        dealer.hit(draw())
        dealer.hit(draw())

        //if both have 21 sam wins
        if (sam.twentyOne()) return BlackjackResult(sam, dealer)
        if (dealer.twentyOne()) return BlackjackResult(dealer, sam)

        //if both bust dealer wins
        if (sam.bust()) return BlackjackResult(dealer, sam)
        if (dealer.bust()) return BlackjackResult(sam, dealer)

        return null
    }

    fun playRoundOfBlackjack(shuffle: Boolean = false): BlackjackResult {
        if (shuffle) deck.shuffle()

        val sam = Player.SAM()
        val dealer = Player.DEALER()

        startRound(sam, dealer)?.let { result ->
            return result
        } ?: run {
            playOutRound(16, sam)
            if (sam.bust()) return BlackjackResult(dealer, sam)
            //could let it play out naturally but dealer won't take a tie so sam will always win with 21
            if (sam.twentyOne()) return BlackjackResult(sam, dealer)

            playOutRound(sam, dealer)
            if (dealer.bust()) return BlackjackResult(sam, dealer)
            else return BlackjackResult(dealer, sam)
        }
    }

    private fun playOutRound(standThreshold: Int, player: Player) {
        while (!player.stand(standThreshold)) {
            player.hit(draw())
        }
    }

    private fun playOutRound(opponent: Player, player: Player) {
        while (!player.stand(opponent)) {
            player.hit(draw())
        }
    }

    private fun draw() = deck.draw()
}

internal data class BlackjackResult(
    val winner: Player,
    val loser: Player,
) {
    fun outcome() =
        if (winner == Player.SAM()) {
            "WINNER: SAM\n" +
                    "$winner\n" +
                    "$loser"
        } else {
            "WINNER: DEALER\n" +
                    "$loser\n" +
                    "$winner"
        }


}