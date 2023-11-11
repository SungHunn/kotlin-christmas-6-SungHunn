package christmas.util

object Validation {

    fun validateVisitDate(date : String){

        require(date.toIntOrNull() != null) {"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."}
        require(date.toInt() in 1..31) {"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."}
    }
}