fun main() {
    var myList = mutableListOf(
        mutableListOf<Int>(9, 9 ,9),
        mutableListOf<Int>(9, 9 ,9),
        mutableListOf<Int>(9, 9 ,9)
    )
    println(printMatrix(myList))

    var notFinish = true
    var check = " "
    var xORo = true //для определения принадлежности хода
    while (notFinish) {
        var input = readln()!!
        if (checkCoordinate(myList, input) != "") {
            println(checkCoordinate(myList, input))
            continue
        } else {
            var xCoordinate = input[0].toString().toInt()
            var yCoordinate = input[2].toString().toInt()
            if (xORo) myList[xCoordinate - 1][yCoordinate - 1] = 1 else {
                myList[xCoordinate - 1][yCoordinate - 1] = 0
            }
            xORo = xORo.not() // меняем ход
        }
        println(printMatrix(myList))

        //проверка на победителя

        if (check(myList) == 1) {
            println("X wins")
            break
        } else
            if (check(myList) == 2) {
                println("O wins")
                break
            } else {
                //проверка на ничью и на Impossible
                if (checkDraw(myList)) {
                    println("Draw")
                    break
                }
            }


    }


}
//Красиво распечатываем матрицу
fun printMatrix(list: MutableList<MutableList<Int>>): String {
    var newList: MutableList<MutableList<String>> = mutableListOf(
        mutableListOf<String>(" ", " ", " "),
        mutableListOf<String>(" ", " ", " "),
        mutableListOf<String>(" ", " ", " ")
    )
    for (i in 0..2) {
        for (j in 0..2) {
            if (list[i][j] == 9) newList[i][j] = " " else
                if (list[i][j] == 1) newList[i][j] = "X" else
                    newList[i][j] = "O"
        }
    }
    return "---------\n" +
            "| ${newList[0][0]} ${newList[0][1]} ${newList[0][2]} |\n" +
            "| ${newList[1][0]} ${newList[1][1]} ${newList[1][2]} |\n" +
            "| ${newList[2][0]} ${newList[2][1]} ${newList[2][2]} |\n" +
            "---------"
}

// функция проверки введеных координат
fun checkCoordinate(list: MutableList<MutableList<Int>>, fstr : String) : String {
    var result ="_"

    if (fstr[0].isDigit() && fstr[1].toString() == " " && fstr[2].isDigit()) {

        var x = fstr[0].toString().toInt()
        var y = fstr[2].toString().toInt()
        if (x !in 1..3 || y !in 1..3 ) result = "Coordinates should be from 1 to 3!" else
            if (list[x - 1][y - 1] != 9) result = "This cell is occupied! Choose another one!"
            else {
                result = ""
            }
    } else {
        result = "You should enter numbers!"
    }
    return result
}
//Функция проверки  победителя
fun check(list: MutableList<MutableList<Int>>): Int {
    var count = 0
    if (list[0][0] + list[1][0] + list[2][0] == 3 || list[0][1] + list[1][1] + list[2][1] == 3 ||
        list[0][2] + list[1][2] + list[2][2] == 3 || list[0][0] + list[0][1] + list[0][2] == 3 ||
        list[1][0] + list[1][1] + list[1][2] == 3 || list[2][0] + list[2][1] + list[2][2] == 3 ||
        list[2][0] + list[1][1] + list[0][2] == 3
    ) count += 1
    if (list[0][0] + list[1][0] + list[2][0] == 0 || list[0][1] + list[1][1] + list[2][1] == 0 ||
        list[0][2] + list[1][2] + list[2][2] == 0 || list[0][0] + list[0][1] + list[0][2] == 0 ||
        list[1][0] + list[1][1] + list[1][2] == 0 || list[2][0] + list[2][1] + list[2][2] == 0 ||
        list[2][0] + list[1][1] + list[0][2] == 0
    ) count += 2

    return count

}
fun checkDraw(list : MutableList<MutableList<Int>>) : Boolean {
    var countNull = 0
    for (i in 0..2) {
        for (j in 0..2) {
            if (list[i][j] == 9) countNull += 1
        }
    }
    return check(list) == 3 || countNull == 0

}