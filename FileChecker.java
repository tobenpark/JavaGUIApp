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
		textfile = new File(filePath);//이름항목 텍스트파일
		
		//String[] str = null;
		//this.data = myFileReader(textfile, str);
		
	}//Constructor
	
	public void setInit(){
		
		File dir = new File("C:" + File.separator + "data");
		if(!dir.exists()){ // 폴더가 없으면 생성
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
	            //.readLine()은 끝에 개행문자를 읽지 않는다.
	            bufReader.close();
	            
			}catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}else{
			//myFileWriter(file);//파일이 없으면 생성
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
			writer = new FileWriter(file, false);//true:기존내용에 이어쓰기, false: 기존내용 없애고 새로쓰기
			
			for(int i=0;i<7;i++){
				data[i] = "0".concat(Integer.toString(i+1)).concat(";");
				writer.write(data[i]);
			}
			writer.flush();
			System.out.println("파일생성.....");
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
			writer = new FileWriter(textfile, false);//true:기존내용에 이어쓰기, false: 기존내용 없애고 새로쓰기
			
			for(int i=0;i<data.length; i++){
				data[i] = str[i].concat(";");
				writer.write(data[i]);
				System.out.println("항목데이터 저장: "+data[i]);
			}
			writer.flush();
			System.out.println("항목 데이터 저장 완료");
			
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
