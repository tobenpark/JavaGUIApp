import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TableDialog extends JDialog{
	private JTableBox adapter;
	private FileChecker fck;
	Object[][] data2 ;
	String[] colName2 = {"항목"};
	
	DefaultTableModel tm;
	JTable table;
	JScrollPane scrollPane;
	JTextField text;
	JButton btnAdd;
	JButton btnDelete;
	JButton btnSave;
	JButton btnCancel;
	
	private int row, column;
	
	public TableDialog(JFrame frame, JTableBox adapter, FileChecker fck){
		super(frame, "항목추가");
		
		tm = new DefaultTableModel(data2, colName2){
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
		};
		table = new JTable(tm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		text = new JTextField();
		btnAdd = new JButton(">>");
		btnDelete = new JButton("<<");
		btnSave = new JButton("저장");
		btnCancel = new JButton("닫기");
		
		table.setRowHeight(25);
		table.setFont(new Font("arian", Font.BOLD, 20));
		table.setRowSorter(new TableRowSorter(tm));
		table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가

		scrollPane = new JScrollPane(table);
		setLayout(null);
		setResizable(false);
		text.setBounds(10,20,100,40);
		text.setFont(new Font("arian", Font.BOLD, 20));
		btnAdd.setBounds(120,20,60,40);
		btnDelete.setBounds(120,70,60,40);
		btnSave.setBounds(130,275,80,30);
		btnCancel.setBounds(210,275,80,30);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(190, 10, 100,260);
		add(scrollPane);
		add(btnSave);
		add(btnCancel);
		add(text);
		add(btnDelete);
		add(btnAdd);
		setBounds(650, 200, 300,340);
		this.adapter = adapter;
		this.fck=fck;
		
		text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField)e.getSource();
				if(src.getText().length()>1){
				text.setText("");
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
			    column = table.getSelectedColumn();
				
			}
		});
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getRowCount();
				String[] str = new String[i];
				if(i>0){
					for(int idx=0; idx<i; idx++){
					str[idx]= (String) table.getValueAt(idx,0);
					
					}
					fck.myCustomFileWriter(str);
					tableCopy2();
				}
				JOptionPane.showMessageDialog(null, "항목 데이터가 저장되었습니다.", "", JOptionPane.DEFAULT_OPTION);
				setVisible(false);//다이얼로그 닫기
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getRowCount();
				if(i>0){
				tableCopy2();
				}
				setVisible(false);//다이얼로그 닫기
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str= text.getText();
				if(isNumeric( str ) == true){
				if(str.length()>0 && str.length()<3){
					if(str.length()==1){
						str= "0".concat(text.getText());
					}
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					model.addRow(new Object[]{str});
				}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				model.removeRow(row);
			}
		});
	}
	

	public void tableCopy(){
		int i = adapter.getRowCount();
		int table_len = table.getRowCount();
		String[] str;
		DefaultTableModel model=(DefaultTableModel)table.getModel();
			if(table_len>0){
//				for(int x=table_len-1; x>=0; x--){
//					model.removeRow(x);
//				}
				model.setNumRows(0);
			}
			if(i>0){
				str= new String[i];
				
			for(int idx=0; idx<i; idx++){
				str[idx]= (String) adapter.getValueAt(idx,0);
				model.addRow(new Object[]{str[idx]});
			}
			}
		
	}
	
	public void tableCopy2(){
		int i = adapter.getRowCount();
		int table_len = table.getRowCount();
		String[] str;
		DefaultTableModel model=(DefaultTableModel)adapter.getModel();
			if(i>0){
//				for(int x=table_len-1; x>=0; x--){
//					model.removeRow(x);
//				}
				model.setNumRows(0);
			}
			if(table_len>0){
				str= new String[table_len];
				
			for(int idx=0; idx<table_len; idx++){
				str[idx]= (String) table.getValueAt(idx,0);
				model.addRow(new Object[]{str[idx]});
			}
			}
		
	}
	
	public boolean isNumeric(String s){
		try {
		      Double.parseDouble(s);
		      return true;
		  } catch(NumberFormatException e) {
		      return false;
		  }
	}

}
