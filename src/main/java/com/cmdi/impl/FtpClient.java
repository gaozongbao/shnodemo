package com.cmdi.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

import com.csvreader.CsvReader;

public class FtpClient {
	private String hostIp;
	private int port;
	private String userName;
	private String passWord;
	private String controlEncoding;
	private FTPClient ftpClient;
	public FtpClient(String hostIp, int port, String userName, String passWord, String controlEncoding) {
		super();
		this.hostIp = hostIp;
		this.port = port;
		this.userName = userName;
		this.passWord = passWord;
		this.controlEncoding = controlEncoding;
		ftpClient = new FTPClient();
		boolean flag = false;
		try {
			ftpClient.setControlEncoding(controlEncoding);
			ftpClient.connect(hostIp,port);
			flag = ftpClient.login(userName, passWord);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(!ftpClient.isConnected() || !flag)
			ftpClient = null;
	}
	
	public void disconnect() {
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public FTPFile[] listAllFile(String path) {
		// TODO Auto-generated method stub
		try {
			FTPFile[] files = ftpClient.listFiles(path);
			return files;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public FTPFile[] listAllFile(String path, final String regex) {
		FTPFileFilter filter = new FTPFileFilter() {
			
			@Override
			public boolean accept(FTPFile file) {
				// TODO Auto-generated method stub
				boolean flag = false;
				try {
//					System.out.println(file.getName().matches(regex));
					if(file.isFile() && file.getName().matches(regex) && file.getName().split("_").length ==2)
						flag = true;
				} catch (Exception e) {
					// TODO: handle exception
				}
				return flag;
			}
		};
		try {
			FTPFile[] listFiles = ftpClient.listFiles(path, filter);
			return listFiles;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CsvReader getFtpFile(String path, FTPFile ftpFile, String fileEncoding) {
		CsvReader reader = null;
		try {
			ftpClient.changeWorkingDirectory(path);
			System.out.println(ftpFile);
			System.out.println(ftpFile.getName());
			reader = new CsvReader(ftpClient.retrieveFileStream(ftpFile.getName()), ',', Charset.forName(fileEncoding));
//			System.out.println(reader);
			return reader;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public InputStream getFtpFileIuputStream(String path, FTPFile ftpFile) {
		InputStream inputStream = null;
		try {
			ftpClient.changeWorkingDirectory(path);
			System.out.println(ftpFile);
			System.out.println(ftpFile.getName());
			inputStream = ftpClient.retrieveFileStream(ftpFile.getName());
//			System.out.println(reader);
			return inputStream;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}

}
