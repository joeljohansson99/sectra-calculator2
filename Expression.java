
interface Expression {

}

class NumberExpression implements Expression {
    private Operand op;
    private int value;

    NumberExpression (Operand op, int value) {
        this.op = op;
        this.value = value;
    }
}

class RegisterExpression implements Expression {
    private final Operand op;
    private final String reg;

    RegisterExpression (Operand op, String reg) {
        this.op = op;
        this.reg = reg;
    }
}
