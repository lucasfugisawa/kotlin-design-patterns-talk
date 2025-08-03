package com.fugisawa.dp2_builder

class Car private constructor(
    val make: String,
    val model: String,
    val year: Int?,
) {
    // Builder aninhado, padrão clássico
    class Builder {
        private var make: String = ""
        private var model: String = ""
        private var year: Int? = null

        fun withMake(make: String) = apply { this.make = make }
        fun withModel(model: String) = apply { this.model = model }
        fun withYear(year: Int) = apply { this.year = year }

        // Validação centralizada no momento da construção
        fun build(): Car {
            require(make.isNotBlank()) { "Make cannot be empty" }
            require(model.isNotBlank()) { "Model cannot be empty" }
            return Car(make, model, year)
        }
    }
}

fun main() {
    val car1 = Car.Builder()
        .withMake("Honda")
        .withModel("Civic")
        .withYear(2020)
        .build()

    val car2 = Car.Builder()
        .withMake("Audi")
        .withModel("RS8")
        .build()
}

//<editor-fold desc="💡 Car Type-Safe DSL">
//@DslMarker
//annotation class CarDsl
//
//@CarDsl
//class CarBuilder {
//    var make: String = ""
//    var model: String = ""
//    var year: Int? = null
//
//    fun build(): Car {
//        require(make.isNotBlank()) { "Make cannot be empty" }
//        require(model.isNotBlank()) { "Model cannot be empty" }
//        return Car(make, model, year)
//    }
//}
//
//fun car(block: CarBuilder.() -> Unit): Car = CarBuilder().apply(block).build()
//</editor-fold>
