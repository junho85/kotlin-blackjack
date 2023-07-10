package blackjack.domain

class Participants(private val dealer: Dealer, val players: List<Player>) {
    val participants: List<Participant> = listOf(dealer) + players

    fun isDealerBust(): Boolean {
        return dealer.cards.isBust()
    }

    fun calculateDealerResult(): DealerResult {
        players.forEach {
            if (it.isWin(dealer)) {
                dealer.dealerResult.lose += 1
                return@forEach
            }
            if (it.isLose(dealer)) {
                dealer.dealerResult.win += 1
                return@forEach
            }
            dealer.dealerResult.draw += 1
        }

        return dealer.dealerResult
    }

    fun getFinalResult(): FinalResult {
        val dealerResult = calculateDealerResult()
        val playerResults = players.associateWith { player -> player.calculateResult(dealer) }
        return FinalResult(dealerResult, playerResults)
    }
}
