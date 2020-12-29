import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProductActivation {
	JFrame frame;
	mainProgram mp;
	File serialDir;
	File parentDir;
	public ProductActivation(mainProgram mp){
		this.mp= mp;
		parentDir= new File("C:" + File.separator + "Program Manager"+ File.separator + "bin");
		serialDir= new File("C:"+ File.separator + "Program Manager"+ File.separator + "bin"+ File.separator + "a.ini");
		frame = new JFrame("인증 요청");
		frame.setSize(400,200);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container contentPane = frame.getContentPane();//팬
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel("제품 인증키를 입력 바랍니다");
		label.setBounds(10,10,200,40);
		panel.add(label);
		JTextField input = new JTextField();
		input.setBounds(10,60, 375,30);
		panel.add(input);
		
		JButton button = new JButton("인증");
		button.setBounds(300,120, 80,30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src=input.getText();
				int nDay;
				Calendar calendar = new GregorianCalendar(Locale.KOREA);
				//nYear = calendar.get(Calendar.YEAR);
				//nMonth = calendar.get(Calendar.MONTH) + 1;
				nDay = calendar.get(Calendar.DAY_OF_MONTH);
				
				String key="W269".concat(Integer.toString(nDay)).concat("NWFGWXYVC9B4J6C9T83GX");
				if(src.compareTo(key)==0){
					if(!parentDir.exists()){
						parentDir.mkdirs();
					}
					makePass(serialDir);
					frame.setVisible(false);
					mp.Activation();
					JOptionPane.showMessageDialog(null, "인증이 완료 되었습니다.", "인증완료", JOptionPane.DEFAULT_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, "잘못된 인증키", "error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(button);
		contentPane.add(panel,BorderLayout.CENTER);
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);	
		
		getRequestPathCheck();
		
		
		input.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					JTextField src = (JTextField)e.getSource();
					//int nYear;
					//int nMonth;
					int nDay;
					Calendar calendar = new GregorianCalendar(Locale.KOREA);
					//nYear = calendar.get(Calendar.YEAR);
					//nMonth = calendar.get(Calendar.MONTH) + 1;
					nDay = calendar.get(Calendar.DAY_OF_MONTH);
					
					String key="W269".concat(Integer.toString(nDay)).concat("NWFGWXYVC9B4J6C9T83GX");
					if(src.getText().compareTo(key)==0){
						if(!parentDir.exists()){
							System.out.println("dd");
							parentDir.mkdirs();
						}
						makePass(serialDir);
						frame.setVisible(false);
						mp.Activation();
						JOptionPane.showMessageDialog(null, "인증이 완료 되었습니다.", "인증완료", JOptionPane.DEFAULT_OPTION);
					}else{
						JOptionPane.showMessageDialog(null, "잘못된 인증키", "error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		

	
	}
	
	public void getRequestPathCheck(){
		
		if(!serialDir.exists()){
			frame.setVisible(false);//frame.setVisible(true);
			
			mp.Activation();//add
		}else{
			frame.setVisible(false);
			mp.Activation();
		}
		
	}
	
	public void makePass(File serialDir){
		FileWriter writer = null;
		try {
			writer = new FileWriter(serialDir, false);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
	            if(writer != null) writer.close();
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
}
