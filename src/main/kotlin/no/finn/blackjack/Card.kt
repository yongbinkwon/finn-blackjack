package no.finn.blackjack

internal class Card(
    private val suit: String,
    private val value: String
) {
    private val numericalValue = try {
        when (value) {
            "J" -> 10
            "Q" -> 10
            "K" -> 10
            "A" -> 11
            else -> value.toInt().let { if (it > 0) it else throw NumberFormatException() }
        }
    } catch (e: NumberFormatException) {
        throw NumberFormatException("Value of card $value is not a valid one")
    }

    operator fun plus(other: Int) = numericalValue + other

    override fun toString(): String = "$suit$value"

    override fun equals(other: Any?): Boolean =
        other is Card
                && other.suit == suit
                && other.value == value
}