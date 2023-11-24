package christmas.controller

import christmas.model.Menu
import christmas.model.MenuCategory
import christmas.service.Calculator
import christmas.service.Event
import christmas.util.Constants
import christmas.util.Constants.BENEFIT_DETAIL
import christmas.util.Constants.FREE_GIFT_PHRASES
import christmas.util.Constants.NOTHING
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val input: InputView,
    private val output: OutputView,
    private val event: Event
) {

    private var amount = 0
    private var freeGift = false

    fun present() {
        output.printStart()
        val date = input.readVisitDate()
        val order = MenuCategory.getMenuList(input.readOrder())

        output.printPreviewEvent(date)
        output.printOrder(order)

        amount = presentBeforeDiscount(order)
        freeGift = checkForFreeGift(amount)

        presentBenefitDetail(date, order)

        val benefitPrice = presentSumBenefits(date, order)
        output.printDiscountedTotalAmount(amount, benefitPrice, freeGift)
        output.printEventBadge(benefitPrice)
    }

    private fun presentBeforeDiscount(order: Map<Menu, Int>): Int {
        val amount = Calculator(event).calculateBeforeDiscount(order)
        output.printAmountBeforeDiscount(amount)

        return amount
    }

    private fun checkForFreeGift(amount: Int): Boolean {
        println("\n" + FREE_GIFT_PHRASES)

        if (event.freeGiftEvent(amount)) {
            output.printFreeGift()
            return true
        }
        output.printNothing()
        return false
    }

    private fun checkMinimumOrder(amount: Int): Boolean {
        return amount < 10000
    }

    private fun presentBenefitDetail(date: Int, order: Map<Menu, Int>) {
        if (checkMinimumOrder(amount)) {
            println("\n" + BENEFIT_DETAIL)
            output.printNothing()
        } else {
            output.printBenefitDetail(date, order, freeGift)
        }
    }

    private fun presentSumBenefits(date: Int, order: Map<Menu, Int>): Int {
        val benefits = Calculator(event).calculateBenefitPrice(date, order, freeGift)

        output.printSumBenefits(benefits)

        return benefits
    }


}