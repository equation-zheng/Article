package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
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

import util.DataBaseUtils;
import util.ImgUtils;


public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String username = null;
	
	//定义允许上传文件扩展名
	private String Ext_Name = "gif,jpg,jpeg,png,bmp,JPEG2000,tiff,psd,"
			+ ",swf,svg,pcx,dxf,wmf,emf,lic,eps,tga";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {
		String savePath = "E:/Article";
		File saveFileDir = new File(savePath);
		if (!saveFileDir.exists()) {
			//创建临时目录
			saveFileDir.mkdirs();
		}
		//上传时生成临时文件保存目录
		String tmpPath = "E:/Article/tem";
		File tmpFile = new File(tmpPath);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		//消息提示
		String message = "";
		try {
			//使用Apache文件上传组件处理文件上传步骤:
			//1.创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置工厂的缓存区大小,当上传的文件大小超过缓存区的大小时,
			//就会生成一个临时文件存放到指定的临时目录中
			//设置缓存区大小为100KB,如果不指定,那么默认缓冲区大小是10KB
			factory.setSizeThreshold(1024 * 10);
			//设置上传时生成的临时的文件保存目录
			factory.setRepository(tmpFile);
			//2.创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
            // 监听文件上传进度
            upload.setProgressListener(new ProgressListener() {
                @Override
                public void update(long readedBytes,
                				   long totalBytes,
                				   int currentItem) {
                    System.out.println(currentItem + "  当前已处理：" + countFileSize(readedBytes));
                    System.out.println("文件大小为：" + countFileSize(totalBytes));
                }
            });
			//解决上传文件名的中文乱码问题
			upload.setHeaderEncoding("UTF-8");
			//3.判断提交上来的数据是否上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				//按传统方式获取数据
				return;
			}
			//设置上传当个文件的最大值
			upload.setFileSizeMax(1024 * 1024 * 21);
			//设置上传文件总量的最大值,就是本次所有上传文件总和的最大值
			upload.setSizeMax(1024 * 1024 * 210);
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				//如果fileitem中封装的是普通的输入数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					//解决普通输入项数据中文乱码问题
					String value = item.getString("UTF-8");
                    if(name.equals("username")) username = value;
                } else// 如果fileitem中封装的是上传文件
                {
					//得到上传文件的文件名
					String fileName = item.getName();
                    System.out.println("文件名：" + fileName);
					if (fileName == null && fileName.trim().length() == 0) {
						continue;
					}
					//注意:不同的浏览器提交的文件名是不一样的,有些浏览器提交上来的文件名是带有路径的
					//如: C:\Users\***\Desktop\1.txt 而有些则是 : 1.txt
					//处理获取到的上传文件的文件路径部分,只保留文件名部分
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					//检查扩展名
					//如果需要限制上传的文件类型,那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是：" + fileExt);
					if (!Ext_Name.contains(fileExt)) {
						System.out.println("上传文件扩展名是不允许的扩展名：" + fileExt);
                        message = message + "文件：" + fileName + "，上传文件扩展名是不允许的扩展名：" + fileExt + "<br/>";
						break;
					}
					//检查文件的大小
					if (item.getSize() == 0) continue;
					if (item.getSize() > 1024 * 1024 * 21) {
						System.out.println("上传文件大小：" + item.getSize());
						message = message + "文件：" + fileName + "，上传文件大小超过限制大小"
								+ ":" + upload.getFileSizeMax() + "<br/>";
						break;
					}
					//得到存文件的文件名
					String saveFileName = makeFileName(fileName);
					//保存文件方法一   获取item中的上传文件的输入流
					InputStream is = item.getInputStream();
					//创建一个文件输入流
					FileOutputStream out = new FileOutputStream(savePath+"\\"+saveFileName);
					//创建一个缓存区
					byte buffer[] = new byte[1024];
					//判断输入流中的数据是否已经读完的标志
					int len = 0;
					while ((len = is.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					//关闭输出流
					out.close();
					//关闭输入流
					is.close();
					//删除临时文件
					item.delete();
                    message = message + "文件：" + fileName + "，上传成功<br/>";
				}
			}
		} catch (FileSizeLimitExceededException e) {
			message = message + "上传文件大小超过限制<br/>";
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
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString().replaceAll("-", "") + "~" + username + "_" + fileName;
	}
	//文件大小转换成格式化的字符串
	public static String countFileSize(long fileSize) {
	    String fileSizeString = "";
	    try {
	        DecimalFormat df = new DecimalFormat("#.00");
	        long fileS = fileSize;
	        if (fileS == 0) {
	            fileSizeString = "0KB";
	        } else if (fileS < 1024) {
	            fileSizeString = df.format((double) fileS) + "B";
	        } else if (fileS < 1048576) {
	            fileSizeString = df.format((double) fileS / 1024) + "KB";
	        } else if (fileS < 1073741824) {
	            fileSizeString = df
	                    .format(((double) fileS / 1024 / 1024) - 0.01)
	                    + "MB";
	        } else {
	            fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
	                    + "G";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return fileSizeString;
	}
}
