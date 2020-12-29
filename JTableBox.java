import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JTableBox extends JTable implements MouseListener{
	
	private int id;
	private DefaultTableModel model;
	private JTextField[] Texts;
	private JTextField[] pathText;
	private JTableBox[] adapter;
	private int row=0,column=0;
	private Object eventValue;
	private JLabel imageLabel;
	private FileChecker fchk;
	public JTableBox(DefaultTableModel model, int id){
		super(model);
		addMouseListener(this);
		setCellSelectionEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		setRowSorter(new TableRowSorter(model));
		this.model=model;
		this.id=id;
		if(id==0){
			adapter = new JTableBox[1];
		}else if(id==1){
			adapter = new JTableBox[2];

		}else if(id==2){
			adapter = new JTableBox[1];
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		row = this.getSelectedRow();
	    column = this.getSelectedColumn();
	    eventValue = this.getValueAt(row, column);
		if(id==0){//현재경로와 파일 이름을 조합. 이미지 불러온 후 이미지 렌더링
			
			String path = pathText[0].getText().concat("\\").concat(eventValue.toString());//pathText[0]:beforeFilePath
			imageRender(path);
		}else if(id==1){
			//번호 버튼을 누르면 변경전 폴더의 이미지를 변경 후 이미지로 이름 변경후 이동
			//"변경후 파일명" 테이블에 바뀐 이미지 이름 추가
			if(pathText[1].getText().length()>0&&pathText[1]!=null){
				String[] text = new String[4];
				text[0]= Texts[0].getText();
				text[1]= Texts[1].getText().toUpperCase();
				text[2]= Texts[2].getText().concat("_");
				text[3]= eventValue.toString();
				boolean namechk= namingChk(Texts[0].getText(),4);
								namechk &= namingChk_ABC(Texts[1].getText(),1);
								namechk &= namingChk(Texts[2].getText(),3);
				
				if(namechk==true){
					String newName = text[0].concat(text[1]).concat(text[2]).concat(text[3]);
					String renewFile;
					
					if(adapter[0].getRowCount()>0&&adapter[0].eventValue==null){
						System.out.println(adapter[0].row);
							adapter[0].eventValue=adapter[0].getValueAt(0, 0);
							renewFile = adapter[0].eventValue.toString();
							
						System.out.println(renewFile);
						fileMove(renewFile, newName,0);
						if(adapter[0].getRowCount()>0){
						String path = pathText[0].getText().concat("\\").concat(adapter[0].getValueAt(0, 0).toString());//pathText[0]:beforeFilePath
						System.out.println("!!!!!!!!!: "+path);
						imageRender(path);
						}
						adapter[0].eventValue=null;
					}else if(adapter[0].getRowCount()>0&&adapter[0].eventValue!=null){
						
						renewFile = adapter[0].eventValue.toString();
					
						System.out.println(renewFile);
						fileMove(renewFile, newName,adapter[0].row);
						if(adapter[0].getRowCount()>0){
						String path = pathText[0].getText().concat("\\").concat(adapter[0].getValueAt(0, 0).toString());//pathText[0]:beforeFilePath
						System.out.println("!!!!!!!!!: "+path);
						imageRender(path);
						}
						adapter[0].eventValue=null;
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "변경할 이름을 다시 입력하십시오.", "주의", JOptionPane.WARNING_MESSAGE);
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "변경폴더을 선택하십시오.", "주의", JOptionPane.WARNING_MESSAGE);
			}
		}else if(id==2){
			if(e.getClickCount()==1){
			String path = pathText[1].getText().concat("\\").concat(eventValue.toString());//pathText[0]:beforeFilePath
			imageRender(path);
			}else if(e.getClickCount()==2){
				String renewFile = eventValue.toString();
				filePrev(renewFile);
				if(this.getRowCount()<=0){
					String path = pathText[0].getText().concat("\\").concat(adapter[0].getValueAt(0, 0).toString());//pathText[0]:beforeFilePath
					System.out.println("!!!!!!!!!: "+path);
					imageRender(path);
				}
			}
			
		}
	}
	
	
	public void setText(JTextField[] Texts){
		this.Texts= Texts;
	}
	
	public boolean namingChk(String str, int len){
		if(str!=null&& str.length()>0 && str.length()==len){
			for (int i = 0; i < str.length(); i++) {
	            if(!(str.charAt(i) == '0' || str.charAt(i) == '1' || str.charAt(i) == '2'
	                        || str.charAt(i) == '3' || str.charAt(i) == '4' || str.charAt(i) == '5'
	                        || str.charAt(i) == '6' || str.charAt(i) == '7' || str.charAt(i) == '8'
	                        || str.charAt(i) == '9'
	                    )) {
	                return false;
	            }
	        }
			return true;
		}else{
			return false;
		}
        
	}
	public boolean namingChk_ABC(String str, int len){
		int i=0;
		if(str!=null&& str.length()>0 && str.length()==len){
			if((str.charAt(i)>='A'&& str.charAt(i)<='Z') || str.charAt(i)>='a'&& str.charAt(i)<='z'){
				
				return true;
			}else if((str.charAt(i) == '0' || str.charAt(i) == '1' || str.charAt(i) == '2'
                    || str.charAt(i) == '3' || str.charAt(i) == '4' || str.charAt(i) == '5'
                    || str.charAt(i) == '6' || str.charAt(i) == '7' || str.charAt(i) == '8'
                    || str.charAt(i) == '9'
                )) {
				return true;
			}
			
		}
		return false;
	}
	
	public void setPathText(JTextField[] pathText){
		this.pathText = pathText;
	}
	
	public void fileMove(String renewFile, String newName,int index){
		try{
			String beforeFilePath = pathText[0].getText().concat("\\").concat(renewFile);//pathText[0]:beforeFilePath
			String filePath = pathText[1].getText().concat("\\").concat(newName).concat(".jpg");//pathText[0]:beforeFilePath

            File file =new File(beforeFilePath);
 
            if(file.renameTo(new File(filePath))){ //파일 이동
                String name = newName.concat(".jpg");
                
    			adapter[1].model.addRow(new Object[]{ name });//변경후 폴더 테이블에 추가
                adapter[0].model.removeRow(index);//변경전 폴더 테이블 데이터 제거
            }else{
            	 JOptionPane.showMessageDialog(null, "파일이 없거나 파일이 중복됩니다. \n다른 항목을 누르십시오.", "주의", JOptionPane.WARNING_MESSAGE);
                return;
            }
	 
        }catch(Exception e){
        	System.out.println("ERROR");
            e.printStackTrace();
            return;
        }
	}
	
	public void setAdapter(JTableBox adapter, int index){
		this.adapter[index] = adapter;
	}
	public int getRow(){
		return this.row; 
	}
	
	public void filePrev(String renewFile){
		try{
			String beforeFilePath = pathText[1].getText().concat("\\").concat(renewFile);
			String filePath = pathText[0].getText().concat("\\").concat(renewFile);
            File file =new File(beforeFilePath);
 
            if(file.renameTo(new File(filePath))){ //파일 이동
                System.out.println(filePath); //성공시 성공 파일 경로 return
                String name = renewFile;
                
    			adapter[0].model.addRow(new Object[]{ name });//변경후 폴더 테이블에 추가
                model.removeRow(row );//변경전 폴더 테이블 데이터 제거
            }else{
                return;
            }
	 
        }catch(Exception e){
        	System.out.println("ERROR");
            e.printStackTrace();
            return;
        }
	}
	public void setImageObject(JLabel imageLabel){
		this.imageLabel = imageLabel;
	}
	
	public void imageRender(String path){

			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File(path));
			} catch (IOException exception) {
			    exception.printStackTrace();
			}
	//		Image dimg = getRotateImage(img, 90).getScaledInstance(img.getWidth(), img.getHeight(),
	//		        Image.SCALE_SMOOTH);
			Image dimg = img.getScaledInstance(600, 600,
			        Image.SCALE_FAST);
			
			ImageIcon imageIcon = new ImageIcon(dimg);
			System.out.println(imageIcon);
			this.imageLabel.setIcon(imageIcon);
		
	}
	public void setEventValue(Object eventValue){
		this.eventValue = eventValue;
	}
	
	public void setFileChecker(FileChecker fchk){
		this.fchk=fchk;
	}
	
	public void getIndexListTable2(){
		System.out.println(fchk.getData().length);
		for(int i=0;i<fchk.getData().length;i++){
			System.out.println(fchk.getData()[i]);
			DefaultTableModel model=(DefaultTableModel)this.getModel();
			model.addRow(new Object[]{fchk.getData()[i]});
		}
	}
	
	public void getEmptyIndexTable2(){
		System.out.println(fchk.getData().length);
		for(int i=0;i<fchk.getData().length;i++){
			System.out.println(fchk.getData()[i]);
			DefaultTableModel model=(DefaultTableModel)this.getModel();
			model.addRow(new Object[]{fchk.getData()[i]});
		}
	}
//	public BufferedImage getScaledImage(BufferedImage image, int width, int height){
//	        
//	        GraphicsConfiguration gc = imageLabel.getGraphicsConfiguration();
//	        BufferedImage result = gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//	        Graphics2D g = result.createGraphics();
//	       
//	        double w = image.getWidth();
//	        double h = image.getHeight();
//	        g.scale((double)width/w, (double)height/h);
//	        g.drawRenderedImage(image, null);
//	        g.dispose();
//	       
//	        return result;
//	}
//	
//	//회전 함수. 0~360도 사이의 degree값을 지정합니다.
//	//회전시킨 BufferedImage 얻기
//	public BufferedImage getRotateImage(BufferedImage image, double angle){//angle : degree
//	        double _angle = Math.toRadians(angle);
//	        double sin = Math.abs(Math.sin(_angle));
//	        double cos = Math.abs(Math.cos(_angle));
//	        double w = image.getWidth();
//	        double h = image.getHeight();
//	        int newW = (int)Math.floor(w*cos + h*sin);
//	        int newH = (int)Math.floor(w*sin + h*cos);
//	       
//	        GraphicsConfiguration gc = imageLabel.getGraphicsConfiguration();
//	        BufferedImage result = gc.createCompatibleImage(newW, newH, Transparency.TRANSLUCENT);
//	        Graphics2D g = result.createGraphics();
//	       
//	        g.translate((newW-w)/2, (newH-h)/2);
//	        g.rotate(_angle, w/2, h/2);
//	        g.drawRenderedImage(image, null);
//	        g.dispose();
//	       
//	        return result;
//	}


}
