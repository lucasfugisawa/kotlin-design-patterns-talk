package com.fugisawa.dp3_proxy

// Problemas:
// 1. Controlar o acesso a um objeto -> segurança, controle, encapsulamento
// 2. Adiar a criação ou carga de objetos “caros” -> lazy loading
// 3. Acrescentar comportamento sem modificar o objeto real
// 4. Controlar chamadas remotas como se fossem locais

interface Image {
    fun display()
}

// "Objeto real", custoso de inicializar (exemplo clássico de livros)
class RealImage(private val filename: String) : Image {
    init {
        println("💾 Carregando imagem do disco: $filename")
    }
    override fun display() {
        println("Exibindo imagem: $filename")
    }
}

// Proxy tradicional (manual, com null checks)
class ImageProxy(private val filename: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        if (realImage == null) {
            println("⏳ Proxy: inicializando RealImage...")
            realImage = RealImage(filename)
        }
        realImage?.display()
    }
}

fun main() {
    val img: Image = ImageProxy("kotlin.png")
    println("Primeira chamada:")
    img.display()
    println("Segunda chamada:")
    img.display()
}
