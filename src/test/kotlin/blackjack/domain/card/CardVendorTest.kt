package blackjack.domain.card

import blackjack.domain.player.Player
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize

class CardVendorTest : FunSpec({
    test("CardVendor가 정상적으로 1장씩 카드를 draw한다.") {
        // Given
        val allCardsSize = Cards.ALL.size
        val cardVendor = CardVendor()

        // When
        val drawCards = (1..allCardsSize).map { cardVendor.drawCard() }

        // Then
        drawCards shouldHaveSize allCardsSize
        drawCards shouldContainAll Cards.ALL
    }

    test("CardVendor가 모든 카드를 draw한 뒤, 자동으로 새카드를 꺼내서 draw한다.") {
        // Given
        val allCardsSize = Cards.ALL.size
        val cardVendor = CardVendor()

        repeat(allCardsSize) {
            cardVendor.drawCard()
        }

        // When
        val drawCards = (1..allCardsSize).map { cardVendor.drawCard() }

        // Then
        drawCards shouldHaveSize allCardsSize
        drawCards shouldContainAll Cards.ALL
    }

    test("CardVendor가 Player에게 게임 시작시 2장의 카드를 draw한다") {
        // Given
        val cardVendor = CardVendor()

        // When
        val playerFirstTwoCards = cardVendor.drawPlayerFirstTwoCards()

        // Then
        playerFirstTwoCards shouldHaveSize Player.INIT_CARD_COUNT
        playerFirstTwoCards.toSet() shouldHaveSize Player.INIT_CARD_COUNT
    }
})