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
    private var totalScore = 0

    /*
    //clean but a little inefficient
    fun totalScore() = hand.fold(0) { total, current ->
        current + total
    }
     */

    fun stand(threshold: Int = 16) = totalScore > threshold

    //in the case of multiple players this would be list of players and max of totalScore
    fun stand(opponent: Player) = totalScore > opponent.totalScore

    fun bust() = totalScore > 21

    fun twentyOne() = totalScore == 21

    fun hit(card: Card) = hand.add(card).let {
        totalScore = card + totalScore
        totalScore
    }

    override fun toString(): String = "$name: ${hand.joinToString(separator = ", ")}"

    override fun equals(other: Any?) =
        other is Player &&
                other.name == name

}