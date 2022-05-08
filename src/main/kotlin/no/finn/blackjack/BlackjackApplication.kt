package no.finn.blackjack

fun main() {
    println("Welcome to this game of blackjack! You can quit at any time by entering 'q' or 'quit'")
    while(true) {
        println("Enter the filename of the text file you want to use as a deck (inside the resources folder) " +
                "or enter nothing if you want to use a predefined deck of 52 unique cards")
        val deckName = readInput() ?: break
        while (true) {
            try {
                val blackjack = if (deckName.isEmpty()) Blackjack() else Blackjack(Deck(addTrailingSlash(deckName)))
                val result = blackjack.playRoundOfBlackjack(true)
                println("${result.outcome()}\n")
                println("play another round? y/n")
                var playAnother = readInput() ?: break
                while (playAnother !in YES_INPUTS && playAnother !in NO_INPUTS) {
                    println("$playAnother is not a valid input, please type in 'y'/'yes' or 'n'/'no'")
                    playAnother = readInput() ?: break
                }
                if (playAnother in NO_INPUTS) break
            } catch (e: IllegalArgumentException) {
                println(e.message)
                break
            }
        }
    }
}

private val YES_INPUTS = listOf("y", "yes")
private val NO_INPUTS = listOf("n", "no")
private val QUIT_INPUTS = listOf("quit", "q")
private val SERIES_OF_WHITESPACE_REGEX = Regex("(\\s+)")

private fun addTrailingSlash(filePath: String) = if (filePath.first() == '/') filePath else "/$filePath"

private fun readInput() = readLine().let {
    if (it in QUIT_INPUTS) null else it?.replace(SERIES_OF_WHITESPACE_REGEX, "")?.lowercase()
}