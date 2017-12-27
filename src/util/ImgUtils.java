package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImgUtils {

	//��ȡ����ͼƬ��ȡ������
	public static FileInputStream readImage(String path) throws IOException {
		//String paths = path.substring(0, path.lastIndexOf("/"));
		//System.out.println(paths);
		//System.out.println(new File(path));
		return new FileInputStream(new File(path));
	}
	
	
	
	//��ȡ����ͼƬ��������
	public static void readBinImage(InputStream in, String targetPath) {
		File file = new File(targetPath);  //D:\at_night.jpg
		//path = D:
		String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
		if (!file.exists()) {  //���Դ˳���·������ʾ���ļ���Ŀ¼�Ƿ����
			new File(path).mkdir();
		}
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);		//������������� len �������ֽڶ��� byte ����
			}
			fos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
















