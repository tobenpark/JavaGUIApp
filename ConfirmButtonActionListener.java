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
	private File dir;//원본폴더경로
	private File[] resultList = null;//이미지담는용
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
		if(ret != JFileChooser.APPROVE_OPTION){//APPROVE_OPTION:열기 상수값, 취소버튼 누를때 조건 성립
			return;
		}else{
			
			dir = jfc.getSelectedFile();//선택한 파일을 파일객체에 담음
			if (!dir.isDirectory()) {//파일인 경우 상위폴더를 파일객체에 담음
				dir = dir.getParentFile();
		    }
	        String path = dir==null?"":dir.getPath();
	        text.setText(path);
        
	        if(id==0){//변경전 폴더-----------------------
	        	tableRowClear();
	        	resultList = extCheck("jpg");//확장자 확인
	        	//FileAdapter adapter = new FileAdapter();
	        	//adapter.setOriginObj(this);//언제 파일리스트를 요청할지 알수 없으므로 어댑터라는 객체를 만들어 참조값을 담아서 접근할 수 있게함
	        	if(resultList!= null && resultList.length>0){
	        		editTableData();
	        		tableRemote.setEventValue(tableRemote.getValueAt(0, 0));//최초로 0행을 포커스하게함
	        		String imgPath = text.getText().concat("\\").concat(tableRemote.getValueAt(0, 0).toString());
	        		tableRemote.imageRender(imgPath);
	        	}else{
	        		JOptionPane.showMessageDialog(null, "파일이 없는 폴더입니다.", "에러", JOptionPane.DEFAULT_OPTION);
	        	}
	        	
	        	
	        }else if(id==1){//변경후 폴더----------------
	        	table3RowClear();
        	
        	
/*//	        	((ConfirmButtonActionListener) mainProgram.originAL).getResultList();//인터페이스 참조변수(부모)를 구현한 객체(자식)의 메소드 사용을 위해 :다형성 구현
//	        	System.out.println("Test!!"+((ConfirmButtonActionListener) mainProgram.originAL).getResultList()[0].getName());
	        	
	        	//앞으로 할일 이미지 파일들을 전달받았으니,
	        	//새로운 경로에 이미지파일들의 이름을 변경하여 저장한다
	        	//이때 변경할 조건은 숫자 네자리, 알파벳대문자 한글자 혹은 숫자 여야 하며, 숫자 세자리
	        	
	        	//버그 막기: 반드시 이전 경로를 지정한 후 변경 경로를 지정하게 해야한다.
	        	//파일 리스트들이 없으므로 null이 뜰것임
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
	private File[] extCheck(String fileExt){//확장자 검사
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
	
	private void editTableData(){//JTable에 파일을 담음
		String[] str = new String[resultList.length];
		Object [] row;
		for(int i=0;i<resultList.length;i++){
			str[i]=resultList[i].getName();
			row = new Object[]{ str[i] };//row = new Object[]{ new String(str[i]) };
			table1.addRow(row);//여기까지했슴
		}
		
	}
	
	public File[] getResultList(){
		return resultList;
	}
	
	public void setResultList(File[] resultList){
		this.resultList = resultList;
	}
	
}


	

