@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import kotlinx.html.attributes.stringSetDecode
import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
        if (list.isEmpty()) 0.0 else list.sum() / list.size


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val x = mean(list)
    for (i in 0 until list.size) {
        list[i] -= x
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var result = 0.0
    if (b.isEmpty()) return 0.0
    else for (i in 0 until b.size) result += a[i] * b[i]
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) 0.0
    var result = 0.0
    for (i in 0 until p.size) result += p[i] * x.pow(i)
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size)
        list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val mList = mutableListOf<Int>()
    var a = 2
    var numeric = n
    while (a <= numeric) {
        if (numeric % a == 0) {
            mList.add(a)
            numeric /= a
        } else {
            a++
        }
    }
    return mList
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var string1 = ""
    var string2 = ""
    val more4 = n / 1000
    val less4 = n % 1000
    when {
        more4 / 100 == 1 -> string1 += "сто "
        more4 / 100 == 2 -> string1 += "двести "
        more4 / 100 == 3 -> string1 += "триста "
        more4 / 100 == 4 -> string1 += "четыреста "
        more4 / 100 == 5 -> string1 += "пятьсот "
        more4 / 100 == 6 -> string1 += "шестьсот "
        more4 / 100 == 7 -> string1 += "семьсот "
        more4 / 100 == 8 -> string1 += "восемьсот "
        more4 / 100 == 9 -> string1 += "девятьсот "
    }
    when {
        more4 % 100 == 10 -> string1 += "десять "
        more4 % 100 == 11 -> string1 += "одиннадцать "
        more4 % 100 == 12 -> string1 += "двенадцать "
        more4 % 100 == 13 -> string1 += "тринадцать "
        more4 % 100 == 14 -> string1 += "четырнадцать "
        more4 % 100 == 15 -> string1 += "пятнадцать "
        more4 % 100 == 16 -> string1 += "шестнадцать "
        more4 % 100 == 17 -> string1 += "семнадцать "
        more4 % 100 == 18 -> string1 += "восемнадцать "
        more4 % 100 == 19 -> string1 += "девятнадцать "
        more4 % 100 / 10 == 2 -> string1 += "двадцать "
        more4 % 100 / 10 == 3 -> string1 += "тридцать "
        more4 % 100 / 10 == 4 -> string1 += "сорок "
        more4 % 100 / 10 == 5 -> string1 += "пятьдесят "
        more4 % 100 / 10 == 6 -> string1 += "шестьдесят "
        more4 % 100 / 10 == 7 -> string1 += "семьдесят "
        more4 % 100 / 10 == 8 -> string1 += "восемьдесят "
        more4 % 100 / 10 == 9 -> string1 += "девяносто "
    }
    when {
        (more4 % 10 == 1) && (more4 % 100 != 11) -> string1 += "одна "
        (more4 % 10 == 2) && (more4 % 100 != 12) -> string1 += "две "
        (more4 % 10 == 3) && (more4 % 100 != 13) -> string1 += "три "
        (more4 % 10 == 4) && (more4 % 100 != 14) -> string1 += "четыре "
        (more4 % 10 == 5) && (more4 % 100 != 15) -> string1 += "пять "
        (more4 % 10 == 6) && (more4 % 100 != 16) -> string1 += "шесть "
        (more4 % 10 == 7) && (more4 % 100 != 17) -> string1 += "семь "
        (more4 % 10 == 8) && (more4 % 100 != 18) -> string1 += "восемь "
        (more4 % 10 == 9) && (more4 % 100 != 19) -> string1 += "девять "
    }

    if (more4 % 10 == 1) string1 += "тысяча "
    if ((more4 % 10 == 2) || (more4 % 10 == 3) || (more4 % 10 == 4)) string1 += "тысячи "
    else {
        if ((more4 % 10 != 1) and (more4 != 2) and (more4 != 3) and (more4 != 4) and (more4 > 0)) string1 += "тысяч "
    }
    when {
        less4 / 100 == 1 -> string2 += "сто "
        less4 / 100 == 2 -> string2 += "двести "
        less4 / 100 == 3 -> string2 += "триста "
        less4 / 100 == 4 -> string2 += "четыреста "
        less4 / 100 == 5 -> string2 += "пятьсот "
        less4 / 100 == 6 -> string2 += "шестьсот "
        less4 / 100 == 7 -> string2 += "семьсот "
        less4 / 100 == 8 -> string2 += "восьмьсот "
        less4 / 100 == 9 -> string2 += "девятьсот "
    }
    when {
        less4 % 100 == 10 -> string2 += "десять "
        less4 % 100 == 11 -> string2 += "одиннадцать "
        less4 % 100 == 12 -> string2 += "двенадцать "
        less4 % 100 == 13 -> string2 += "тринадцать "
        less4 % 100 == 14 -> string2 += "четырнадцать "
        less4 % 100 == 15 -> string2 += "пятнадцать "
        less4 % 100 == 16 -> string2 += "шестнадцать "
        less4 % 100 == 17 -> string2 += "семнадцать "
        less4 % 100 == 18 -> string2 += "восемнадцать "
        less4 % 100 == 19 -> string2 += "девятнадцать "
        less4 % 100 / 10 == 2 -> string2 += "двадцать "
        less4 % 100 / 10 == 3 -> string2 += "тридцать "
        less4 % 100 / 10 == 4 -> string2 += "сорок "
        less4 % 100 / 10 == 5 -> string2 += "пятьдесят "
        less4 % 100 / 10 == 6 -> string2 += "шестьдесят "
        less4 % 100 / 10 == 7 -> string2 += "семьдесят "
        less4 % 100 / 10 == 8 -> string2 += "восемьдесят "
        less4 % 100 / 10 == 9 -> string2 += "девяносто "
    }
    when {
        (less4 % 10 == 1) && (less4 % 100 != 11) -> string2 += "один "
        (less4 % 10 == 2) && (less4 % 100 != 12) -> string2 += "два "
        (less4 % 10 == 3) && (less4 % 100 != 13) -> string2 += "три "
        (less4 % 10 == 4) && (less4 % 100 != 14) -> string2 += "четыре "
        (less4 % 10 == 5) && (less4 % 100 != 15) -> string2 += "пять "
        (less4 % 10 == 6) && (less4 % 100 != 16) -> string2 += "шесть "
        (less4 % 10 == 7) && (less4 % 100 != 17) -> string2 += "семь "
        (less4 % 10 == 8) && (less4 % 100 != 18) -> string2 += "восемь "
        (less4 % 10 == 9) && (less4 % 100 != 19) -> string2 += "девять "
    }
    if (less4 > 0) {
        string2 = string2.substring(0, string2.length - 1)
    } else {
        string1 = string1.substring(0, string1.length - 1)
    }
    return string1 + string2
}






