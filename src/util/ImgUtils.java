package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImgUtils {

	//读取本地图片获取输入流
	public static FileInputStream readImage(String path) throws IOException {
		//String paths = path.substring(0, path.lastIndexOf("/"));
		//System.out.println(paths);
		//System.out.println(new File(path));
		return new FileInputStream(new File(path));
	}
	
	
	
	//读取表中图片的输入流
	public static void readBinImage(InputStream in, String targetPath) {
		File file = new File(targetPath);  //D:\at_night.jpg
		//path = D:
		String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
		if (!file.exists()) {  //测试此抽象路径名表示的文件或目录是否存在
			new File(path).mkdir();
		}
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);		//将输入流中最多 len 个数据字节读入 byte 数组
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
















