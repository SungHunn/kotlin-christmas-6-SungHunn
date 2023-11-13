package christmas.controller

import christmas.model.MenuCategory
import christmas.util.Constants.BENEFIT_DETAIL
import christmas.util.Constants.NOTHING
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val input: InputView,
    private val output: OutputView
) {

    fun presentExample() {
        output.printStart()
        val date = input.readVisitDate()
        val order = MenuCategory.getMenuList(input.readOrder())

        output.printPreviewEvent(date)
        output.printOrder(order)
        val amount = output.printAmountBeforeDiscount(order)
        val freeGift = output.printFreeGift(amount)

        if (amount < 10000) {
            println("\n" + BENEFIT_DETAIL)
            println(NOTHING)
        } else {
            output.printBenefitDetail(date, order, freeGift)
        }

        val benefitPrice = output.printSumBenefit(date, order, freeGift)
        output.printDiscountedTotalAmount(amount, benefitPrice, freeGift)
        output.printEventBadge(benefitPrice)
    }


}