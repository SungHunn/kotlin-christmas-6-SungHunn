package christmas.model

data class Menu(
    private val name: String,
    private val price: Int
) {

    fun getName(): String = name
    fun getPrice(): Int = price

}