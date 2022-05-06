package no.finn.blackjack

internal class Card(
    private val value: String,
    private val suit: String
) {
    private val numericalValue = try {
        when(value) {
            "J" -> 10
            "Q" -> 10
            "K" -> 10
            "A" -> 11
            else -> value.toInt()
        }
    } catch (e: NumberFormatException) {
        0
    }

    operator fun plus(other: Int) = numericalValue + other

    override fun toString(): String = "$suit$value"
}