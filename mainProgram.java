import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class mainProgram{
	private JFrame frame;
	public mainProgram(){
		FileChecker filechk=null;
		try {
			filechk = new FileChecker();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		filechk.setInit();
		JFrame frame = new JFrame("이미지 정리 프로그램");
		this.frame=frame;
		//frame.setPreferredSize(new Dimension(1020,768));//크기
		frame.setSize(1020,748);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		final int TEXT_HEIGHT= 20;
		
		Container contentPane = frame.getContentPane();//팬
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		Font font = new Font("arian", Font.PLAIN, 16);
		
		//레이아웃
		JButton originBtn = new JButton("원본폴더");
		originBtn.setBounds(10, 20, 90,TEXT_HEIGHT);
		panel.add(originBtn);
		
		JButton transBtn = new JButton("변경폴더");
		transBtn.setBounds(10, 45, 90,TEXT_HEIGHT);
		panel.add(transBtn);
		
		JTextField originPathText= new JTextField();
		originPathText.setBounds(120, 20, 300,TEXT_HEIGHT);
		originPathText.setFont(font);
		originPathText.setEditable(false);
		panel.add(originPathText);
		
		JTextField transPathText= new JTextField();
		transPathText.setBounds(120, 45, 300,TEXT_HEIGHT);
		transPathText.setFont(font);
		transPathText.setEditable(false);
		panel.add(transPathText);
		
		JTextField[] pathTexts = new JTextField[2];
		pathTexts[0]=originPathText;
		pathTexts[1]=transPathText;
		
		
		//이름 형식 입력란
		Font font2 = new Font("arian", Font.PLAIN, 30);
		
		JTextField namingFormatText1= new JTextField();
		namingFormatText1.setBounds(10, 90, 80,50);
		namingFormatText1.setFont(font2);
		panel.add(namingFormatText1);
		
		JTextField namingFormatText2= new JTextField();
		namingFormatText2.setBounds(100, 90, 30,50);
		namingFormatText2.setFont(font2);
		namingFormatText2.addKeyListener(new KeyListener() {
					
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().length()>0){
					String str =src.getText();
					namingFormatText2.setText(str.substring(1));
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField src = (JTextField)e.getSource();
				String str =src.getText();
				if(str.length()>0){
				if(str.charAt(0)>='a'&& str.charAt(0)<='z'){
					namingFormatText2.setText(str.toUpperCase());
				}
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(namingFormatText2);
		
		JTextField namingFormatText3= new JTextField();
		namingFormatText3.setBounds(140, 90, 60,50);
		namingFormatText3.setFont(font2);
		panel.add(namingFormatText3);
		

		JButton addIndexBtn= new JButton("항목추가");
		addIndexBtn.setBounds(240, 90, 100,40);
		panel.add(addIndexBtn);
		
		JTextField[] Texts =new JTextField[3];//이름 형식
		Texts[0]=namingFormatText1;
		Texts[1]=namingFormatText2;
		Texts[2]=namingFormatText3;
		//-----------
		
		String[] colName = {"현재파일"};
		
		DefaultTableModel model1 = new DefaultTableModel(colName, 0) {
	        public boolean isCellEditable(int rowIndex, int mColIndex) {
	                return false;
	            }
	        };//JTable 제어를 위해 써야하는 JTable의 수퍼클래스
	        JTableBox indextable1= new JTableBox(model1,0);

	        indextable1.setFont(font);
	        indextable1.setPathText(pathTexts);
	        indextable1.setRowHeight(30);
//	        Filetable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
//			Filetable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
			JScrollPane scrollPane1 = new JScrollPane(indextable1);
			scrollPane1.setBounds(10,150, 200,300);
			panel.add(scrollPane1);
//		Filetable = new JTable(model1);
//		Filetable.setFont(font);
//		Filetable.setRowHeight(20);
//		Filetable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
//		Filetable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
//		Filetable.addMouseListener(this);
//		JScrollPane scrollPane1 = new JScrollPane(Filetable);
//		scrollPane1.setBounds(10,180, 200,300);
//		panel.add(scrollPane1);
		
		
		String[] colName2 = {"항목"};
		DefaultTableModel model2 = new DefaultTableModel(colName2, 0) {
	        public boolean isCellEditable(int rowIndex, int mColIndex) {
	                return false;
	            }
	        };
        JTableBox indextable2 = new JTableBox(model2,1);
        indextable2.setText(Texts);
        indextable2.setPathText(pathTexts);
        indextable2.setFont(new Font("arian", Font.BOLD, 25));
        indextable2.setRowHeight(40);
        indextable2.setFileChecker(filechk);
		indextable2.getIndexListTable2();
        
        JScrollPane scrollPane2 = new JScrollPane(indextable2);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(240,150, 100,560);
		panel.add(scrollPane2);
		
		 
//		indextable = new JTable(model2);
//		indextable.setFont(new Font("arian", Font.BOLD, 25));
//		indextable.setRowHeight(50);
//		indextable.addMouseListener(this);
//		JScrollPane scrollPane2 = new JScrollPane(indextable);
//		scrollPane2.setBounds(230,180, 100,350);
//		panel.add(scrollPane2);
		
		//ImageSection
		JLabel imageLabel = new JLabel();
		JLabelBox labelbox = new JLabelBox(imageLabel);
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setBounds(400, 100, 600,600);
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel.add(imageLabel);
//		JScrollPane imageSection = new JScrollPane(imageLabel);
//		imageSection.setBounds(400, 100, 600,600);
//		panel.add(imageSection);

		//--------------------
		
		String[] colName3 = {"변환파일"};
		DefaultTableModel model3 = new DefaultTableModel(colName3, 0) {
	        public boolean isCellEditable(int rowIndex, int mColIndex) {
	                return false;
	            }
	        };
        JTableBox indextable3 = new JTableBox(model3,2);
		indextable3.setFont(font);
		indextable3.setPathText(pathTexts);
		indextable3.setRowHeight(30);
		JScrollPane scrollPane3 = new JScrollPane(indextable3);
		scrollPane3.setBounds(10,460, 200,250);
		panel.add(scrollPane3);
		
		//adapter set
		indextable1.setAdapter(indextable2,0);
        indextable2.setAdapter(indextable1,0);
        indextable2.setAdapter(indextable3,1);
        indextable3.setAdapter(indextable1,0);
        
        indextable1.setImageObject(imageLabel);
        indextable2.setImageObject(imageLabel);
        indextable3.setImageObject(imageLabel);
        
//		indextable3 = new JTable(model3);
//		indextable3.setFont(font);
//		indextable3.addMouseListener(this);
//		JScrollPane scrollPane3 = new JScrollPane(indextable3);
//		scrollPane3.setBounds(10,490, 200,220);
//		panel.add(scrollPane3);
		
		contentPane.add(panel,BorderLayout.CENTER);
		frame.setContentPane(contentPane);//프레임에 패널 적용
		
		//JFileChooser
		JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.setCurrentDirectory(new File("C:\\"));
        ActionListener originAL = new ConfirmButtonActionListener(jfc, frame, originPathText, 0, model1, model3, indextable1);
        ActionListener transAL = new ConfirmButtonActionListener(jfc, frame, transPathText, 1, model1, model3);
        TableDialog dialog = new TableDialog(frame,indextable2, filechk);
		originBtn.addActionListener(originAL);
		transBtn.addActionListener(transAL);
		
		addIndexBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);//다이얼로그 닫기
				dialog.tableCopy();
			}
		});
		
        //JFrame End
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(false);
		
		
	}
	
//	public void imageEvent(){
//        try {
//            //map = new JLabel(new ImageIcon(ImageIO.read(new File("c:\\Test\\1.jpg"))));
//            //map.setAutoscrolls(true);
//            //add(new JScrollPane(map));
//
//            MouseAdapter ma = new MouseAdapter() {
//
//                private Point origin;
//
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    origin = new Point(e.getPoint());
//                }
//
//                @Override
//                public void mouseReleased(MouseEvent e) {
//                }
//
//                @Override
//                public void mouseDragged(MouseEvent e) {
//                    if (origin != null) {
//                        JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, map);
//                        if (viewPort != null) {
//                            int deltaX = origin.x - e.getX();
//                            int deltaY = origin.y - e.getY();
//
//                            Rectangle view = viewPort.getViewRect();
//                            view.x += deltaX;
//                            view.y += deltaY;
//
//                            map.scrollRectToVisible(view);
//                        }
//                    }
//                }
//
//            };
//
//            map.addMouseListener(ma);
//            map.addMouseMotionListener(ma);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//	}
	public void Activation(){
		System.out.println("activation");
		this.frame.setVisible(true);
	}
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    
		    mainProgram mp = new mainProgram();
		    ProductActivation pa = new ProductActivation(mp);
		    //mp.Activation();
		} catch (Exception e) {
		}
	}
	
}
/*//			panel.setLayout(new GridLayout(2,2));
//			panel.add(new JLabel("이름"));
//			JTextField text1 = new JTextField("ddd");
//			panel.add(text1);
//			panel.add(new JLabel("주소"));
//			JTextField text2 = new JTextField("어디동");
//			panel.add(text2);
//			contentPane.add(panel, BorderLayout.NORTH);
//			JLabel label = new JLabel("안녕하슈 라벨내용", SwingConstants.CENTER);//내용, 가운데정렬
//			JTextField text = new JTextField("ddd");
//			JButton button =new JButton("확인");
//			panel.add(text, BorderLayout.CENTER);
			//			contentPane.add(text, BorderLayout.CENTER);//팬에내용추가
			//			contentPane.add(button, BorderLayout.EAST);
			//			contentPane.add(label, BorderLayout.SOUTH);
			
			//ActionListener listener = new ConfirmButtonActionListener(text, label);
			//button.addActionListener(listener);
*/			

	
