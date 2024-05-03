
// Class for storing a pending operation, calling it Expression in order to not confuse it with operand. 
class Expression {
    private Operand op;
    private String value;
	private boolean number;

    Expression (Operand op, String value, boolean number) {
        this.op = op;
        this.value = value;
		this.number = number;
    }

	public String get_value() {
		return this.value;
	}

	public Operand get_op() {
		return this.op;
	}

	public boolean is_number() {
		return this.number;
	}
}
