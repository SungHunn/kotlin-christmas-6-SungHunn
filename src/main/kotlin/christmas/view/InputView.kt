package christmas.view

import christmas.util.Constants.REQUEST_VISIT_DATE
import camp.nextstep.edu.missionutils.Console
import christmas.util.Validation.validateVisitDate

class InputView {

    fun readVisitDate(): String {
        println(REQUEST_VISIT_DATE)

        return checkVisitDate()
    }

    private fun checkVisitDate() : String{

        return try {
            val input = Console.readLine()
            validateVisitDate(input)
            input
        } catch (e: IllegalArgumentException){
            println(e)
            checkVisitDate()
        }
    }
}