package Calculator2;

import java.text.DecimalFormat;

import javax.swing.JTextField;

public class CalClass2 {
	private String input; // 342. --> 받을 수 없다
	private String value;
	private String operator;
	private String cal_formula;
	private boolean isCalculated;
	private boolean isEqualCalculated;

	CalClass2() {
		this.input = "0";
		this.operator = null;
		this.value = null;
		this.cal_formula = "";
		this.setCalculated(true);
		this.setEqualCalculated(false); // = 연산이 실행되었는가?
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPrecisionInput() {
		double num = this.stod(input);

		DecimalFormat df = new DecimalFormat("#.######");
		String result = df.format(num);

		return result;
	}

	public void setPrecisionInput(String input) {
		double num = this.stod(input);

		DecimalFormat df = new DecimalFormat("#.######");
		String result = df.format(num);

		this.setInput(result);
	}

	public void setTextField(JTextField textField, String str) {
		textField.setText(str);
	}

	public void stackingNumber(JTextField textField, String btn_name) {
		// System.out.println(this.isCalculated());
		System.out.println(this.operator);
		if (this.isCalculated()) {
			// if (this.operator != null && this.operator.equals("=")) {
			// this.reset();
			// }
			this.setInput(btn_name);
			this.setCalculated(false);
			return;
		}

		// =을 입력한 직후에 숫자 입력
		if (this.isEqualCalculated()) {
			this.setInput(btn_name);
			this.setValue("0");
			this.setEqualCalculated(false);
			this.setCal_formula("");
			return;
		}
		this.setInput(this.getInput() + btn_name);
		return;
	}

	public void popNumber() {
		if (this.getInput().length() > 0) {
			this.setInput(this.getInput().substring(0, this.getInput().length() - 1));
		}

		if (this.getInput().isEmpty() || this.getInput().equals("-")) {
			this.setInput("0");
		}
	}

	public boolean isDouble(String str) {
		double number = Double.parseDouble(str);
		if (number != (double) ((int) number)) {
			return true;
		}
		return false;
	}

	public boolean isDouble(double number) {
		if (number != (double) ((int) number)) {
			return true;
		}
		return false;
	}

	public double stod() {
		return Double.parseDouble(this.getInput());
	}

	public double stod(String str) {
		return Double.parseDouble(str);
	}

	public int stoi(String str) {
		return Integer.parseInt(str);
	}

	public int dtoi(double num) {
		return (int) num;
	}

	public String to_string(double num) {
		return Double.toString(num);
	}

	public String to_string(int num) {
		return Integer.toString(num);
	}

	public boolean isCalculated() {
		return isCalculated;
	}

	public void setCalculated(boolean isCalculated) {
		this.isCalculated = isCalculated;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setValue(double value) {
		this.value = Double.toString(value);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void calculate(JTextField textField, String operator) { // String + switch == overflow

		System.out.println("isEqual : " + this.isEqualCalculated());
		System.out.println("oper : " + this.getOperator());

		if (this.isEqualCalculated() && !operator.equals("=")) {
			this.setInput("0");
			this.setEqualCalculated(false);
			this.setCalculated(false);
			this.setCal_formula(this.getValue() + " " + operator + " ");
			this.setTextField(textField, this.getCal_formula());
		}

		if (operator.equals("=")) {
			this.setCal_formula(this.getValue() + " " + this.getOperator() + " " + this.getInput() + " = ");
			if (this.getValue() == null) {
				this.setValue(this.getInput());
				this.setEqualCalculated(true);
				return;
			}
			if (this.operator == null) {
				this.setValue(this.getInput());
				this.setOperator("=");
				this.setEqualCalculated(true);
				return;
			}
		}
		
		if(operator.equals("%")) {
			this.setPrecisionInput(this.to_string(this.stod(this.getInput())/100));
			this.setEqualCalculated(true);
		}else if(operator.equals("√")) {
			double temp_number = Math.sqrt(this.stod());
			if(this.isDouble(temp_number)) {
				this.setPrecisionInput(Double.toString(temp_number));
			}else {
				this.setInput(this.to_string(this.dtoi(temp_number)));
			}
			this.setEqualCalculated(true);
		}else if(operator.equals("x²")) {
			if(this.isDouble(this.getInput())) {
				this.setPrecisionInput(Double.toString(Math.pow(this.stod(this.getInput()), 2)));
			}else {
				this.setPrecisionInput(this.to_string(this.dtoi(Math.pow(this.stod(), 2))));
			}
			this.setEqualCalculated(true);
		}else if(operator.equals("x³")) {
			if (this.isDouble(this.getInput())) {
				this.setPrecisionInput(Double.toString(Math.pow(this.stod(this.getInput()), 3)));
			} else {
				this.setPrecisionInput(this.to_string(this.dtoi(Math.pow(this.stod(), 2))));
			}
			this.setEqualCalculated(true);
		}else if(operator.equals("¹⁄ₓ")) {
			this.setPrecisionInput(this.to_string(1 / this.stod(this.getInput())));	
			this.setEqualCalculated(true);
		}
		
		

		if (this.getValue() == null) {
			this.setValue(this.getInput());
			this.calculate(textField, operator);
			return;
		}

		if (this.operator == null) {
			this.setOperator(operator);
			this.setCalculated(true);
			return;
		}

		double calculated_value = 0.0;

		
		
		
		if (this.operator.equals("+")) {
			calculated_value = this.stod(this.getValue()) + this.stod(this.getInput());

			if (this.isDouble(calculated_value)) {
				this.setValue(calculated_value);
			} else {
				this.setValue(Integer.toString((int) calculated_value));
			}
		} else if (this.operator.equals("－")) {
			calculated_value = this.stod(this.getValue()) - this.stod(this.getInput());

			if (this.isDouble(calculated_value)) {
				this.setValue(calculated_value);
			} else {
				this.setValue(Integer.toString((int) calculated_value));
			}
		} else if (this.operator.equals("×")) {
			calculated_value = this.stod(this.getValue()) * this.stod(this.getInput());

			if (this.isDouble(calculated_value)) {
				this.setValue(calculated_value);
			} else {
				this.setValue(Integer.toString((int) calculated_value));
			}
		} else if (this.operator.equals("÷")) {
			calculated_value = this.stod(this.getValue()) / this.stod(this.getInput());

			if (this.isDouble(calculated_value)) {
				this.setValue(calculated_value);
			} else {
				this.setValue(Integer.toString((int) calculated_value));
			}
		} 
		

		if (!operator.equals("=")) {
			this.setOperator(operator);
		} else {
			this.setEqualCalculated(true);
			return;
		}

		this.setCalculated(true);
		// this.setEqualCalculated(true);
}

	public String getCal_formula() {
		return cal_formula;
	}

	public void setCal_formula(String cal_formula) {
		this.cal_formula = cal_formula;
	}

	public void reset() {
		this.input = "0";
		this.operator = null;
		this.value = null;
		this.cal_formula = "";
		this.setCalculated(false);
		this.setEqualCalculated(false);
	}

	public boolean isEqualCalculated() {
		return isEqualCalculated;
	}

	public void setEqualCalculated(boolean isEqualCalculated) {
		this.isEqualCalculated = isEqualCalculated;
	}
}
