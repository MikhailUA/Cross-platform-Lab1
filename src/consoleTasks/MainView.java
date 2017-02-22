package consoleTasks;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 655);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextArea textArea = new JTextArea(20, 50);
		JScrollPane output = new JScrollPane(textArea);
		frame.getContentPane().add(output);
		
		JButton btnCalculate = new JButton("Calculate");
		frame.getContentPane().add(btnCalculate);
		
		btnCalculate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					Map output = DerivativeApplication.calculate();
					String ResultsView = generateView(output);
					textArea.setText(ResultsView);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		
	}

	private String generateView(Map<String, List> output) {
		
		String ResultsView = "";
		String functionName;
		String x,y,derivative;
		
		for (String key : output.keySet()){
			System.out.println(key);
			functionName = key;
			List<Map> results = output.get(key);
			String resultLine = "";	
			for (Map<String, Double> result : results){
				x = result.get("x").toString();
				y = result.get("y").toString();
				derivative = result.get("derivative").toString();
				resultLine = resultLine + "x: " + x + "\tf: " + y + "\tf' : " + derivative + "\n";
			}			
			
			ResultsView = ResultsView + "Функция: " + functionName + "\n" + resultLine;			
		}		

		return ResultsView;
	}

}
