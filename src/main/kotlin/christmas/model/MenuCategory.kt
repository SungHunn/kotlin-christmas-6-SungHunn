package christmas.model


import christmas.util.Constants.FREE_GIFT



enum class MenuCategory(private val menuList: List<Menu>) {
    APPETIZER(
        listOf(
            Menu("양송이수프", 6000),
            Menu("타파스", 5500),
            Menu("시저샐러드", 8000)
        )
    ),
    MAIN(
        listOf(
            Menu("티본스테이크", 55000),
            Menu("바비큐립", 54000),
            Menu("해산물파스타", 35000),
            Menu("크리스마스파스타", 25000)
        )
    ),
    DESSERT(
        listOf(
            Menu("초코케이크", 15000),
            Menu("아이스크림", 5000)
        )
    ),
    DRINK(
        listOf(
            Menu("제로콜라", 3000),
            Menu("레드와인", 60000),
            Menu("샴페인", 25000)
        )
    );

    companion object {
        fun getMenuList(orderMenu: List<String>): Map<Menu, Int> {
            val resultMenu = mutableMapOf<Menu, Int>()

            for (order in orderMenu) {
                val (name, quantity) = order.split("-")
                val menuName = findMenu(name)

                if (menuName != null)
                    resultMenu[Menu(menuName.getName(), menuName.getPrice())] = quantity.toInt()

            }
            return resultMenu
        }

        fun findMenu(name: String): Menu? {
            for (category in entries) {
                val menu = category.menuList.find { it.getName() == name }
                if (menu != null) return menu
            }
            return null
        }

        fun findCategory(category: MenuCategory, orderMenu: Map<Menu, Int>): Int {
            var count = 0
            val menuList = category.menuList
            orderMenu.forEach { menu ->
                if (menuList.contains(menu.key)) count += menu.value
            }
            return count
        }

        fun findFreeGiftPrice(): Int {
            var price = 0
            DRINK.menuList.forEach { drink ->
                if (drink.getName() == FREE_GIFT)
                    price = drink.getPrice()
            }
            return price
        }

        fun checkOnlyDrink(menuName: String): Boolean {
            DRINK.menuList.forEach { drink ->
                if (drink.getName() == menuName)
                    return true
            }
            return false
        }
    }

}


