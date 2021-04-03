package blackjack.domain.winning

import blackjack.domain.participants.Player

class BettingResult(val results: Map<Player, Double>) {

    fun getDealerProfit(): Int {
        val money = results.values
        val playersLoss = money.filter { it < 0 }.sum() * -1
        if (playersLoss == 0.0) playersLoss * -1
        return playersLoss.toInt()
    }
}