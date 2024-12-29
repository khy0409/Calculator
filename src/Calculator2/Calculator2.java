package Calculator2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class Calculator2 extends JFrame {

	private JPanel contentPane;
	private JTextField txt_cal_formula;
	private JTextField txt_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator2 frame = new Calculator2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이 코드가 있어야 창 종료시 메모리에서 지워짐
		setBounds(100, 100, 350, 400); // x좌표, y좌표, 가로길이, 세로길이
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_cal_formula = new JTextField();
		txt_cal_formula.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_cal_formula.setEditable(false);
		txt_cal_formula.setBounds(0, 0, 334, 25);
		contentPane.add(txt_cal_formula);
		txt_cal_formula.setColumns(10);
		
		txt_input = new JTextField();
		txt_input.setFont(new Font("Arial", Font.PLAIN, 45));
		txt_input.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_input.setText("0");
		txt_input.setEditable(false);
		txt_input.setBounds(0, 35, 334, 44);
		contentPane.add(txt_input);
		txt_input.setColumns(10);
		
		String[] btn_names = {"%", "C", "cE", "De", "÷",
							  "√", "7", "8" , "9" , "×",
							  "x²", "4", "5", "6" , "－",
							  "x³", "1", "2", "3" , "＋",
							  "¹⁄ₓ", "±", "0", ".", "=" };
		
		JButton[] btnArr = new JButton[btn_names.length];
		
		CalClass2 cal = new CalClass2();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				int index = 5 * i + j;
				btnArr[index] = new JButton(btn_names[index]);
				btnArr[index].setBounds(50 + (50 * j), 100 + (50 * i), 50, 50);
				
				btnArr[index].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String btn_name = e.getActionCommand();
						
						if (btn_name.equals("%")) {
							cal.setCal_formula(cal.getCal_formula() + "(" + cal.getInput() + ")%");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							
							cal.setPrecisionInput(cal.to_string(cal.stod(cal.getInput()) / 100.0));
							cal.calculate(txt_cal_formula, "=");	
							cal.setEqualCalculated(true);
							cal.setTextField(txt_input, cal.getInput());
						}
						else if (btn_name.equals("C")) {
							cal.setInput("0");
							cal.setValue("0");
							cal.setCal_formula("0");
							cal.setOperator(null);
							cal.setCalculated(true);
							cal.setEqualCalculated(false);
							
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.setTextField(txt_input, cal.getValue());
						}
						else if (btn_name.equals("cE")) {
							cal.setInput("0");
							cal.setCalculated(true);
							cal.setTextField(txt_input, cal.getInput());
						}
						else if (btn_name.equals("De")) {
							cal.popNumber();
							cal.setTextField(txt_input, cal.getInput());
						}
						else if (btn_name.equals("÷")) {
							cal.setCal_formula(cal.getCal_formula() + cal.getInput() + " ÷ ");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "÷");
							cal.setTextField(txt_input, cal.getValue());
						}
						else if (btn_name.equals("√")) {
							cal.setCal_formula(cal.getCal_formula() + "√(" + cal.getInput() + ")");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "√");
							cal.setTextField(txt_input, cal.getInput());
						
					}else if (btn_name.equals("×")) {
							cal.setCal_formula(cal.getCal_formula() + cal.getInput() + " × ");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "×");
							cal.setTextField(txt_input, cal.getValue());
						}
						else if (btn_name.equals("x²")) {
							// int로 캐스팅 -> 324.1^2 계산불가
							// double로 캐스팅 -> 5^2 == 25.0
							// 35.0 -> int
							// 35.1 -> double
							// 35 -> int
							
							cal.setCal_formula(cal.getCal_formula() +  cal.getInput() + "^2");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "x²");
							cal.setTextField(txt_input, cal.getInput());
							
						}
						else if (btn_name.equals("－")) {
							cal.setCal_formula(cal.getCal_formula() + cal.getInput() + " － ");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "－");
							cal.setTextField(txt_input, cal.getValue());
						}
						else if (btn_name.equals("x³")) {
							cal.setCal_formula(cal.getCal_formula() +  cal.getInput() + "^3");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "x³");
							cal.setTextField(txt_input, cal.getInput());
						}
						else if (btn_name.equals("＋")) {
							cal.setCal_formula(cal.getCal_formula() + cal.getInput() + " + ");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "+");
							cal.setTextField(txt_input, cal.getValue());
						}
						else if (btn_name.equals("¹⁄ₓ")) {
							cal.setCal_formula(cal.getCal_formula() +  "1/" + cal.getInput() + " ");
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.calculate(txt_cal_formula, "¹⁄ₓ");
							cal.setTextField(txt_input, cal.getInput());
						}
						else if (btn_name.equals("±")) {
							if (cal.getInput().substring(0, 1).equals("-")) {
								if (!cal.getInput().equals("0")) {
									cal.setInput(cal.getInput().substring(1, cal.getInput().length()));
								}
							} else {
								cal.setInput("-" + cal.getInput());
							}
							cal.setTextField(txt_input, cal.getInput());
						}
						else if (btn_name.equals(".")) {
							
							if (cal.isCalculated()) {
								cal.setInput("0");
								cal.setCalculated(false);
							}
							
							if (!cal.getInput().contains(".")) {
								cal.setInput(cal.getInput() + ".");
							}
							cal.setTextField(txt_input, cal.getInput());
						}
						else if (btn_name.equals("=")) {
							
							cal.calculate(txt_cal_formula, "=");
							
							cal.setTextField(txt_cal_formula, cal.getCal_formula());
							cal.setTextField(txt_input, cal.getValue());
							
							//cal.setValue(null);
							
						} else {
							
							cal.stackingNumber(txt_input , btn_name);
							cal.setTextField(txt_input, cal.getInput());
							
							
							// 정수일땐 -> 정수로 나와야해
							// 342 -> 342.0 (X)
							// 342 -> 342 (O)
							// 342.
							// 342.1 -> 342.1
						}
					}
				});
				
				contentPane.add(btnArr[index]);
			}
		}
		
	}
}
