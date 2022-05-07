package no.finn.blackjack

internal class Player private constructor(
    private val name: String
) {
    companion object {
        //make them functions instead so there won't be a need to empty hands every time
        fun SAM() = Player("sam")
        fun DEALER() = Player("dealer")
    }

    private val hand: MutableList<Card> = mutableListOf()

    //clean but a little inefficient
    fun totalScore() = hand.fold(0) { total, current ->
        current + total
    }

    fun stand(threshold: Int = 16) = totalScore() > threshold

    fun bust() = totalScore() > 21

    fun twentyOne() = totalScore() == 21

    fun hit(card: Card) = hand.add(card)

    override fun toString(): String = "$name: ${hand.joinToString(separator = ", ")}"

    override fun equals(other: Any?) =
        other is Player &&
                other.name == name

}