import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class FileChecker {
	private File textfile;
	private String[] data;
	public FileChecker() throws FileNotFoundException{
		//String dirPath = "C:" + File.separator + "data";
		String filePath = "C:" + File.separator + "data" + File.separator + "data.txt";
		textfile = new File(filePath);//�̸��׸� �ؽ�Ʈ����
		
		//String[] str = null;
		//this.data = myFileReader(textfile, str);
		
	}//Constructor
	
	public void setInit(){
		
		File dir = new File("C:" + File.separator + "data");
		if(!dir.exists()){ // ������ ������ ����
			System.out.println("mkdirs!");
			dir.mkdirs();
		}
		
		String[] str = null;
		if(textfile.exists()){
			this.data = myFileReader(textfile, str);
		}else{
			myFileWriter(textfile);
			this.data = myFileReader(textfile, str);
		}
		
	}
	
	public String[] myFileReader(File file, String[] str){
		String line;
		if(file.exists() ){
			try {
				FileReader reader = new FileReader(file);
				BufferedReader bufReader = new BufferedReader(reader);
				
	            while((line = bufReader.readLine()) != null){
	            	str = line.split(";");
	            }
	            //.readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.
	            bufReader.close();
	            
			}catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}else{
			//myFileWriter(file);//������ ������ ����
			return null;
		}
		
	}
	
	public String[] getData(){
		return this.data;
	}
	
	public void myFileWriter(File file){
		
		FileWriter writer=null;
		try{
			String[] data = new String[7];
			writer = new FileWriter(file, false);//true:�������뿡 �̾��, false: �������� ���ְ� ���ξ���
			
			for(int i=0;i<7;i++){
				data[i] = "0".concat(Integer.toString(i+1)).concat(";");
				writer.write(data[i]);
			}
			writer.flush();
			System.out.println("���ϻ���.....");
			//return true;
		}catch(Exception e){
			e.printStackTrace();
			//return false;
		}finally {
	        try {
	            if(writer != null) writer.close();
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
		
	public boolean myCustomFileWriter(String[] str){
		FileWriter writer=null;
		
		try{
			String[] data = new String[str.length];
			writer = new FileWriter(textfile, false);//true:�������뿡 �̾��, false: �������� ���ְ� ���ξ���
			
			for(int i=0;i<data.length; i++){
				data[i] = str[i].concat(";");
				writer.write(data[i]);
				System.out.println("�׸����� ����: "+data[i]);
			}
			writer.flush();
			System.out.println("�׸� ������ ���� �Ϸ�");
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
		    try {
		        if(writer != null) writer.close();
		    } catch(IOException e) {
		        e.printStackTrace();
		    }
		}
		
	}
		
}
