package christmas.model

import java.awt.SystemColor.menu


enum class MenuCategory(val category: String, val menuList: List<Menu>) {
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

    companion object {
        fun checkItemInMenuCategory(menuName: String): Boolean {
            return when {
                APPETIZER.menuList.any { it.name == menuName } -> true
                MAIN.menuList.any { it.name == menuName } -> true
                DESSERT.menuList.any { it.name == menuName } -> true
                DRINK.menuList.any { it.name == menuName } -> true
                else -> false
            }
        }

        fun getMenuList(orderMenu: List<String>): Map<Menu, Int> {
            val resultMenu = mutableMapOf<Menu, Int>()

            for (order in orderMenu) {
                val (name, quantity) = order.split("-")
                val menuName = findMenu(name)

                if (menuName != null)
                    resultMenu[Menu(menuName.name, menuName.price)] = quantity.toInt()

            }
            return resultMenu
        }

        fun findMenu(name: String): Menu? {
            for (category in entries) {
                val menu = category.menuList.find { it.name == name }
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

    }

}


