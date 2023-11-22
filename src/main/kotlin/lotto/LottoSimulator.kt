package lotto

import lotto.domain.LottoPurchaseOrder
import lotto.domain.LottoResult
import lotto.domain.LottoTicket
import lotto.provider.LottoResultProvider
import lotto.provider.ticket.AutoTicketProvider
import lotto.view.InputView
import lotto.view.ResultView
import lotto.view.UserInputView

class LottoSimulator(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun simulate(): LottoResult {
        val purchaseOrder = LottoPurchaseOrder(
            budget = inputView.getBudget(),
            ticketPrice = LottoTicket.PRICE,
        )

        val lottoTickets = inputView.provideLottoTickets(purchaseOrder.ticketCount)
        val winningNumber = inputView.getWinningNumber()

        val result = LottoResultProvider.provideLottoResult(
            totalPrize = lottoTickets.getRankResult(winningNumber).totalPrize(),
            purchaseOrder = purchaseOrder,
        )

        resultView.printResult(result)
        return result
    }
}

fun main() {
    LottoSimulator(
        UserInputView(
            AutoTicketProvider
        ),
        ResultView()
    ).simulate()
}
