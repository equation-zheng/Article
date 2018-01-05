package servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListFileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取上传文件的目录
		String uploadFilePath = "E:/Article";
		//储存要下载的文件名
		Map<String, String> fileNameMap = new HashMap<String, String>();
		//递归遍历filepath目录下的所有文件和目录,将文件的文件名储存到map集合中
		listFile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录
		//将Map集合发送到 jsp页面显示
		//System.out.println(fileNameMap);
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/listFile.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	//遍历指定目录下的所有文件
	public void listFile(File file, Map<String, String> map) {
		if (file == null) return;
		//如果file代表的不是一个文件,而是一个目录
		if (!file.isFile()) {
			//列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			if (files == null) return;
			//遍历files[]数组
			for (File f : files) {
				//递归
				listFile(f, map);
			}
		} else {
			String realName = file.getName().substring(file.getName().indexOf("~") + 1);
			//file.getName()得到的是文件的原始名称,这个名称是唯一的,因此可以作为key,
			//realName是处理过后的名称,有可能会重复
			map.put(file.getName(), realName);
		}
	}
}
