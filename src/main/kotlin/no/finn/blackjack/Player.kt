package no.finn.blackjack

internal class Player private constructor(
    private val name: String
) {
    companion object {
        //make them functions instead so there won't be a need to empty hands every time
        fun SAM() = Player("Sam")
        fun DEALER() = Player("Dealer")
    }

    private val hand: MutableList<Card> = mutableListOf()

    fun totalScore() = hand.fold(0) { total, current ->
        current + total
    }

    fun stand(threshold: Int = 17) = totalScore() >= threshold

    fun hit(card: Card) = hand.add(card)

    override fun toString(): String = "$name: ${hand.joinToString(separator = ", ")}"

}