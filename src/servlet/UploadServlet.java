package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//���������ϴ��ļ���չ��
	private String Ext_Name = "gif,jpg,jpeg,png,bmp,swf,flv,mp3,"
			+ "wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,"
			+ "xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {
		String savePath = "E:/Article";
		File saveFileDir = new File(savePath);
		if (!saveFileDir.exists()) {
			//������ʱĿ¼
			saveFileDir.mkdirs();
		}
		//�ϴ�ʱ������ʱ�ļ�����Ŀ¼
		String tmpPath = "E:/Article/tem";
		File tmpFile = new File(tmpPath);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		//��Ϣ��ʾ
		String message = "";
		try {
			//ʹ��Apache�ļ��ϴ���������ļ��ϴ�����:
			//1.����һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//���ù����Ļ�������С,���ϴ����ļ���С�����������Ĵ�Сʱ,
			//�ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼��
			//���û�������СΪ100KB,�����ָ��,��ôĬ�ϻ�������С��10KB
			factory.setSizeThreshold(1024 * 10);
			//�����ϴ�ʱ���ɵ���ʱ���ļ�����Ŀ¼
			factory.setRepository(tmpFile);
			//2.����һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
            // �����ļ��ϴ�����
            upload.setProgressListener(new ProgressListener() {
                @Override
                public void update(long readedBytes,
                				   long totalBytes,
                				   int currentItem) {
                    System.out.println(currentItem + "  ��ǰ�Ѵ�����" + readedBytes/1024/1024 + "MB");
                    System.out.println("�ļ���СΪ��" + totalBytes/1024/1024 + "MB");
                }
            });
			//����ϴ��ļ�����������������
			upload.setHeaderEncoding("UTF-8");
			//3.�ж��ύ�����������Ƿ��ϴ�����������
			if (!ServletFileUpload.isMultipartContent(request)) {
				//����ͳ��ʽ��ȡ����
				return;
			}
			//�����ϴ������ļ������ֵ
			upload.setFileSizeMax(1024 * 1024 * 21);
			//�����ϴ��ļ����������ֵ,���Ǳ��������ϴ��ļ��ܺ͵����ֵ
			upload.setSizeMax(1024 * 1024 * 210);
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				//���fileitem�з�װ������ͨ����������
				if (item.isFormField()) {
					String name = item.getFieldName();
					//�����ͨ����������������������
					String value = item.getString("UTF-8");
                    System.out.println(name + "=" + value);
                } else// ���fileitem�з�װ�����ϴ��ļ�
                {
					//�õ��ϴ��ļ����ļ���
					String fileName = item.getName();
                    System.out.println("�ļ�����" + fileName);
					if (fileName == null && fileName.trim().length() == 0) {
						continue;
					}
					//ע��:��ͬ��������ύ���ļ����ǲ�һ����,��Щ������ύ�������ļ����Ǵ���·����
					//��: C:\Users\***\Desktop\1.txt ����Щ���� : 1.txt
					//������ȡ�����ϴ��ļ����ļ�·������,ֻ�����ļ�������
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					// �õ��ϴ��ļ�����չ��
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					//�����չ��
					//�����Ҫ�����ϴ����ļ�����,��ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
                    System.out.println("�ϴ����ļ�����չ���ǣ�" + fileExt);
					if (!Ext_Name.contains(fileExt)) {
						System.out.println("�ϴ��ļ���չ���ǲ���������չ����" + fileExt);
                        message = message + "�ļ���" + fileName + "���ϴ��ļ���չ���ǲ���������չ����" + fileExt + "<br/>";
						break;
					}
					//����ļ��Ĵ�С
					if (item.getSize() == 0) continue;
					if (item.getSize() > 1024 * 1024 * 21) {
						System.out.println("�ϴ��ļ���С��" + item.getSize());
						message = message + "�ļ���" + fileName + "���ϴ��ļ���С�������ƴ�С"
								+ ":" + upload.getFileSizeMax() + "<br/>";
						break;
					}
					//�õ����ļ����ļ���
					String saveFileName = makeFileName(fileName);
					//�����ļ�����һ   ��ȡitem�е��ϴ��ļ���������
					InputStream is = item.getInputStream();
					//����һ���ļ�������
					FileOutputStream out = new FileOutputStream(savePath+"\\"+saveFileName);
					//����һ��������
					byte buffer[] = new byte[1024];
					//�ж��������е������Ƿ��Ѿ�����ı�־
					int len = 0;
					while ((len = is.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					//�ر������
					out.close();
					//�ر�������
					is.close();
					//ɾ����ʱ�ļ�
					item.delete();
                    message = message + "�ļ���" + fileName + "���ϴ��ɹ�<br/>";
					//�����ļ�������
					//File uploadedFile = new File(savePath, saveFileName);
					//item.write(uploadedFile);
				}
			}
		} catch (FileSizeLimitExceededException e) {
			message = message + "�ϴ��ļ���С��������<br/>";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			request.getRequestDispatcher("/wall.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws ServletException, IOException {
		doGet(request, response);
	}
	private String makeFileName(String fileName) {
		// Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
		return UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
	}
}