/**
 * 목표: 실습1 심화
 * 조건
 * 1. 색상(RGB)을 추가로 가지게 변경
 *  단 색상은 이넘으로 관리해라
 *  단, 여러가지 색깔을 가질 수 있다. 추가 삭제 가능, 중복 X
 */

fun main(args: Array<String>) {
    val product = Product("상품")
    println(product)


    // 가격, 색상을 scope function으로 설정
    // 단, 재고는 2개
    product.apply {
        this.price = 10000
        addColor(Color.RED)
        this.amount = 2
    }
    println(product)

    // 물건 1건 구매를 scope function으로 실행
    product.let {
        it.buy(1)
    }
    println(product)

    // 물건 현재 가치(price*amount) println()으로 출력 - scope function 활요
    product.let {
        println("${it.name} 현재 가치 : " + it.price * it.amount)
    }

    // 물건 1건 구매를 scope function으로 실행하고, chaining하여 물건갯구가 0이하면 "재고없음" 출력하기
    with(product) {
        product.buy(1)
    }.also {
        if (product.amount <= 0) {
            println("재고없음")
        }
    }
    println(product)
}

enum class Color {
    RED, GREEN, BLUE
}

private data class Product (
    val name: String,
    val color: MutableSet<Color> = mutableSetOf<Color>(),
    var price: Int = 0,
    var amount: Int = 0
) {
    fun addColor(color: Color) {
        if (this.color.contains(color)) {
            println("이미 존재하는 색상입니다.")
        } else {
            this.color.add(color)
        }
    }

    fun deleteColor(color: Color) {
        if(this.color.contains(color)){
            this.color.remove(color)
        }else{
            println("해당 색상이 존재하지 않습니다.")
        }
    }

    fun buy(amount: Int = 1) {
        this.amount -= amount
    }

    override fun toString(): String {
        return """
            상품명 : $name
            컬러 : $color
            가격 : $price
            재고 : $amount
        """.trimIndent()
    }


}