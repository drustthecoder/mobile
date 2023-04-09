class BankAccount {
    private var active = true
    var balance: Long = 0
        get() {
            check(active)
            return field
        }

    fun adjustBalance(amount: Long){
        check(active)
        synchronized(this) {
            this.balance += amount
        }
    }

    fun close() {
        active = false
    }
}
