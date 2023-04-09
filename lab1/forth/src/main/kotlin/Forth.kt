class Forth {
    private var numbersStack = mutableListOf<Int>()
    private var operatorsStack = mutableListOf<String>()

    fun evaluate(vararg line: String): List<Int> {
        val numbersList = listOf('0','1','2','3','4','5','6','7','8','9')
        val operatorsList = listOf("+", "-", "*", "/")
        for (current in line){
            val addToStack = current.split("\\s".toRegex()).toList()
            for (element in addToStack){
                var isNum = true
                for (c in element){
                    if (c !in numbersList){
                        isNum = false
                    }
                }
                if (isNum) numbersStack.add(element.toInt())
                if (element in operatorsList) operatorsStack.add(element)
            }
        }
        return if (operatorsStack.size==0) numbersStack else calculate()
    }
    private fun calculate(): List<Int>{
        for (operator in operatorsStack){
            if (numbersStack.size==0) throw NullPointerException("empty stack")
            if (operator in listOf("+", "-", "*", "/")){
                if (numbersStack.size==1) throw NullPointerException("only one value on the stack")
                val secondOperand = numbersStack.removeLast()
                val firstOperand = numbersStack.removeLast()
                var result = 0
                when (operator){
                    "+" -> result = firstOperand + secondOperand
                    "-" -> result = firstOperand - secondOperand
                    "*" -> result = firstOperand * secondOperand
                    "/" -> if (secondOperand==0) throw NullPointerException("divide by zero") else result = firstOperand / secondOperand
                }
                numbersStack.add(result)
            }

        }
        return numbersStack
    }
}
