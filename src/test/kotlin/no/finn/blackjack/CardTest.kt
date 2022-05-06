package no.finn.blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class CardTest {

    @Test
    fun `check that Cards are added properly`() {
        //not using named arguments so test can catch it if arguments change places
        assertAll(
            { assertEquals(20, Card("C", "J") + 10)},
            { assertEquals(10, Card("D", "Q") + 0)},
            { assertEquals(11, Card("H", "K") + 1)},
            { assertEquals(61, Card("S", "A") + 50)},
            { assertEquals(12, Card("C", "5") + 7)}
        )
    }

    @Test
    fun `check that string representation of Cards are proper`() {
        assertAll(
            { assertEquals("CA", Card("C", "A").toString())},
            { assertEquals("D10", Card("D", "10").toString())}
        )
    }

    @Test
    fun `check that exception is thrown when a card has a non-valid value`() {
        assertAll(
            { assertThrows<NumberFormatException> { Card("C", "C") }},
            { assertThrows<NumberFormatException> { Card("C", "2.2") }},
            { assertThrows<NumberFormatException> { Card("C", "-1") }},
        )
    }

}