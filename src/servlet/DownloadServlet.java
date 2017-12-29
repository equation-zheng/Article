package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�õ�Ҫ���ص��ļ���
		String fileName = request.getParameter("filename");
		fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
		//�ϴ����ļ�����Ҫ������/WEB-INFĿ¼�µ���Ŀ¼����
		String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/wall");
		//�õ�Ҫ���ص��ļ�
		File file = new File(fileSaveRootPath + "\\" + fileName);
		//����ļ�������
		if (!file.exists()) {
			request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ��!!");
			request.getRequestDispatcher("/wall.jsp").forward(request, response);
			return;
		}
		//�����ļ���
		String realname = fileName.substring(fileName.indexOf("_") + 1);
		//������Ӧͷ,������������ظ��ļ�
		response.setHeader("content-disposition", "attachment;filename=" +
					URLEncoder.encode(realname, "UTF-8"));
		//��ȡҪ���ص��ļ�,���浽�ļ�������
		FileInputStream fin = new FileInputStream(fileSaveRootPath + "\\" + fileName);
		//����������
		OutputStream out = response.getOutputStream();
		//����������
		byte buffer[] = new byte[1024];
		int len = 0;
		//ѭ�����������е����ݶ�ȡ������������
		while ((len = fin.read(buffer)) > 0) {
			//��������������ݵ������,ʵ���ļ�����
			out.write(buffer, 0, len);
		}
		//�ر��ļ�������
		fin.close();
		//�ر������
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		doGet(request, response);
	}
}

