package com.aaron.tools.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Ftp上传相关操作相关操作
 * @author aaron.qiu
 * @since 2015-2016
 */
public class FtpUtils {
	private static Log log = LogFactory.getLog(FtpUtils.class);
	private FTPClient ftpClient = null;
	private String ip;
	private String username;
	private String password;
	private int port;

	public FtpUtils() {
	}

	public FtpUtils(String ip, String username, String password, int port) {
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.port = port;
	}

	public boolean loginFtp() {
		boolean flag = true;
		if (this.ftpClient == null) {
			this.ftpClient = new FTPClient();
			this.ftpClient.setControlEncoding("UTF-8");
		}
		try {
			this.ftpClient.connect(this.ip, this.port);
			this.ftpClient.login(this.username, this.password);

			this.ftpClient.setDataTimeout(120000);
		} catch (SocketException e) {
			flag = false;
			e.printStackTrace();
			System.err.println("登录ftp服务器失败,连接超时！");
			log.debug("登录ftp服务器失败");
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
			System.err.println("登录ftp服务器失败，FTP服务器无法打开！");
			log.debug("登录ftp服务器失败");
		}
		return flag;
	}

	public boolean logoutFtp() throws IOException {
		try {
			if (this.ftpClient != null)
				this.ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ((this.ftpClient != null) && (this.ftpClient.isConnected())) {
				this.ftpClient.disconnect();
			}
		}
		return true;
	}

	public boolean uploadFile(InputStream inStream, String fileName, String path)
			throws Exception {
		boolean flag = true;
		try {
			loginFtp();
			this.ftpClient.setFileType(2);
			this.ftpClient.enterLocalPassiveMode();
			this.ftpClient.setFileTransferMode(10);
			path = path.replace('\\', '/');

			createDir(path);

			changerToPath(path);
			flag = this.ftpClient.storeFile(fileName, inStream);
			log.debug(new StringBuilder().append("文件:").append(fileName)
					.append("上传").append(flag == true ? "成功" : "失败").toString());
			this.ftpClient.changeWorkingDirectory("~");
			inStream.close();

			this.ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.ftpClient.isConnected()) {
				this.ftpClient.disconnect();
			}
		}
		return flag;
	}

	public boolean uploadFile(File file, String path) throws Exception {
		boolean flag = true;
		try {
			loginFtp();
			if (file.exists()) {
				this.ftpClient.setFileType(2);
				this.ftpClient.enterLocalPassiveMode();
				this.ftpClient.setFileTransferMode(10);
				FileInputStream bis = new FileInputStream(file);
				path = path.replace('\\', '/');

				createDir(path);

				changerToPath(path);
				flag = this.ftpClient.storeFile(file.getName(), bis);
				this.ftpClient.changeWorkingDirectory("~");
				log.debug(new StringBuilder().append("文件:")
						.append(file.getName()).append("上传")
						.append(flag == true ? "成功" : "失败").toString());

				bis.close();
			}
			this.ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.ftpClient.isConnected()) {
				this.ftpClient.disconnect();
			}

		}

		return flag;
	}

	private boolean changerToPath(String path) {
		boolean isOK = false;
		path = path.replace('\\', '/');
		StringTokenizer s = new StringTokenizer(path, "/");
		s.countTokens();
		String pathName = "";
		while (s.hasMoreElements()) {
			pathName = (String) s.nextElement();
			try {
				isOK = this.ftpClient.changeWorkingDirectory(pathName);
			} catch (Exception e) {
				e = null;
				isOK = false;
			}
		}
		return isOK;
	}

	public static void main(String[] args) throws Exception {
		FtpUtils packer = new FtpUtils("192.168.0.127", "pic1", "pic1", 9999);

		File file = new File("D:/template.xml.bak");
		InputStream in = new FileInputStream(file);
		packer.uploadFile(in, "test1.xml.bak", "/test/test/");

		File file2 = new File("D:/oschina-settings.xml");
		InputStream in2 = new FileInputStream(file2);
		packer.uploadFile(in2, "oschina-settings.xml", "/test/test/");

		File file3 = new File("d:/report.log");
		InputStream in3 = new FileInputStream(file3);
		packer.uploadFile(in3, "report.log", "/test/test/");
	}

	private void createDir(String dir) throws Exception {
		dir = dir.replace('\\', '/');
		StringTokenizer s = new StringTokenizer(dir, "/");
		s.countTokens();
		String pathName = "";
		while (s.hasMoreElements()) {
			pathName = (String) s.nextElement();
			try {
				this.ftpClient.makeDirectory(pathName);
				this.ftpClient.changeWorkingDirectory(pathName);
			} catch (Exception e) {
				e = null;
			}
		}
		this.ftpClient.changeWorkingDirectory("~");
	}

	private boolean isDirExist(String dir) {
		boolean isDirExist = false;
		try {
			isDirExist = this.ftpClient.changeWorkingDirectory(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isDirExist;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(int port) {
		this.port = port;
	}
}