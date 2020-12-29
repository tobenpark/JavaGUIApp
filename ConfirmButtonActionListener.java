import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ConfirmButtonActionListener implements ActionListener{
	//JTextField text;
	//JLabel label;
	private JFileChooser jfc= null;
	private JFrame frame;
	private JTextField text= null;
	private int id = 0;
	private DefaultTableModel table1 = null;
	private DefaultTableModel table2 = null;
	private JTableBox tableRemote = null;
	private File dir;//�����������
	private File[] resultList = null;//�̹�����¿�
	ConfirmButtonActionListener(JFileChooser jfc, JFrame frame, JTextField text,
								int id, DefaultTableModel table1,DefaultTableModel table2){
		this.jfc= jfc;
		this.frame = frame;
		this.text = text;
		this.id = id;
		this.table1 = table1;
		this.table2 = table2;
	}
		ConfirmButtonActionListener(JFileChooser jfc, JFrame frame, JTextField text,
				int id, DefaultTableModel table1,DefaultTableModel table2, JTableBox tableRemote){
	this.jfc= jfc;
	this.frame = frame;
	this.text = text;
	this.id = id;
	this.table1 = table1;
	this.table2 = table2;
	this.tableRemote=tableRemote;
	}
		
	@Override
	public void actionPerformed(ActionEvent e){
		int ret = jfc.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION){//APPROVE_OPTION:���� �����, ��ҹ�ư ������ ���� ����
			return;
		}else{
			
			dir = jfc.getSelectedFile();//������ ������ ���ϰ�ü�� ����
			if (!dir.isDirectory()) {//������ ��� ���������� ���ϰ�ü�� ����
				dir = dir.getParentFile();
		    }
	        String path = dir==null?"":dir.getPath();
	        text.setText(path);
        
	        if(id==0){//������ ����-----------------------
	        	tableRowClear();
	        	resultList = extCheck("jpg");//Ȯ���� Ȯ��
	        	//FileAdapter adapter = new FileAdapter();
	        	//adapter.setOriginObj(this);//���� ���ϸ���Ʈ�� ��û���� �˼� �����Ƿ� ����Ͷ�� ��ü�� ����� �������� ��Ƽ� ������ �� �ְ���
	        	if(resultList!= null && resultList.length>0){
	        		editTableData();
	        		tableRemote.setEventValue(tableRemote.getValueAt(0, 0));//���ʷ� 0���� ��Ŀ���ϰ���
	        		String imgPath = text.getText().concat("\\").concat(tableRemote.getValueAt(0, 0).toString());
	        		tableRemote.imageRender(imgPath);
	        	}else{
	        		JOptionPane.showMessageDialog(null, "������ ���� �����Դϴ�.", "����", JOptionPane.DEFAULT_OPTION);
	        	}
	        	
	        	
	        }else if(id==1){//������ ����----------------
	        	table3RowClear();
        	
        	
/*//	        	((ConfirmButtonActionListener) mainProgram.originAL).getResultList();//�������̽� ��������(�θ�)�� ������ ��ü(�ڽ�)�� �޼ҵ� ����� ���� :������ ����
//	        	System.out.println("Test!!"+((ConfirmButtonActionListener) mainProgram.originAL).getResultList()[0].getName());
	        	
	        	//������ ���� �̹��� ���ϵ��� ���޹޾�����,
	        	//���ο� ��ο� �̹������ϵ��� �̸��� �����Ͽ� �����Ѵ�
	        	//�̶� ������ ������ ���� ���ڸ�, ���ĺ��빮�� �ѱ��� Ȥ�� ���� ���� �ϸ�, ���� ���ڸ�
	        	
	        	//���� ����: �ݵ�� ���� ��θ� ������ �� ���� ��θ� �����ϰ� �ؾ��Ѵ�.
	        	//���� ����Ʈ���� �����Ƿ� null�� �����
//	        	gfl.SearchDir(path);	  
//	        	File[] resultList = gfl.getResultList();*/
	        }
		}
	}
	
	public void tableRowClear(){
		int rowCount = table1.getRowCount();
		//Remove rows one by one from the end of the table
		if(rowCount>0){
			for (int i = rowCount - 1; i >= 0; i--) {
				table1.removeRow(i);
			}
		}
		
		rowCount=table2.getRowCount();
		if(rowCount>0){
			for (int i = rowCount - 1; i >= 0; i--) {
				table2.removeRow(i);
			}
		}
	}
	public void table3RowClear(){
		int rowCount = table2.getRowCount();
		if(rowCount>0){
			for (int i = rowCount - 1; i >= 0; i--) {
				table2.removeRow(i);
			}
		}
	}
	private File[] extCheck(String fileExt){//Ȯ���� �˻�
		final String ext = fileExt.toLowerCase();
		File[] files = dir.listFiles(new FileFilter() {
    		public boolean accept(File file) {
        		if (file.isDirectory()) {
        			return false;
        		}
        		return file.getName().toLowerCase().endsWith("." + ext);
    		}
		});

		return files;
	}
	
	private void editTableData(){//JTable�� ������ ����
		String[] str = new String[resultList.length];
		Object [] row;
		for(int i=0;i<resultList.length;i++){
			str[i]=resultList[i].getName();
			row = new Object[]{ str[i] };//row = new Object[]{ new String(str[i]) };
			table1.addRow(row);//��������߽�
		}
		
	}
	
	public File[] getResultList(){
		return resultList;
	}
	
	public void setResultList(File[] resultList){
		this.resultList = resultList;
	}
	
}


	

