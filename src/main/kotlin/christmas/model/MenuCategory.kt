package christmas.model


enum class MenuCategory(val category: String, val menu: List<Menu>) {
    APPETIZER(
        "에피타이저", listOf(
            Menu("양송이수프", 6000),
            Menu("타파스", 5500),
            Menu("시저샐러드", 8000)
        )
    ),
    MAIN(
        "메인", listOf(
            Menu("티본스테이크", 55000),
            Menu("바비큐립", 54000),
            Menu("해산물파스타", 35000),
            Menu("크리스마스파스타", 25000)
        )
    ),
    DESSERT(
        "디저트",
        listOf(
            Menu("초코케이크", 15000),
            Menu("아이스크림", 5000)
        )
    ),
    DRINK(
        "음료",
        listOf(
            Menu("제로콜라", 3000),
            Menu("레드와인", 60000),
            Menu("샴페인", 25000)
        )
    );

    companion object{
        fun checkItemInMenuCategory(menuName : String): Boolean{
            return when{
                APPETIZER.menu.any { it.name == menuName } -> true
                MAIN.menu.any { it.name == menuName } -> true
                DESSERT.menu.any { it.name == menuName } -> true
                DRINK.menu.any { it.name == menuName } -> true
                else -> false
            }
        }

    }

}


