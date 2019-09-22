package com.narayanatutorial.apachesftp;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

public class GetMyFiles {

	static Properties props;

	public static void main(String[] args) {

		props=new Properties();
		props.put("serverAddress", "demo.wftpserver.com:2222");
		props.put("userId", "demo-user");
		props.put("password", "demo-user");
		props.put("remoteDirectory", "/upload/");
		props.put("localDirectory", "D:/localDownload/");
		
		
		
		GetMyFiles getMyFiles = new GetMyFiles();
	

	
		String fileToDownload = "meny-2018.pdf";
		getMyFiles.startFTP( fileToDownload,props);

	}

	public boolean startFTP(String fileToDownload, Properties props) {

		
		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {

			
			String serverAddress = props.getProperty("serverAddress").trim();
			String userId = props.getProperty("userId").trim();
			String password = props.getProperty("password").trim();
			String remoteDirectory = props.getProperty("remoteDirectory").trim();
			String localDirectory = props.getProperty("localDirectory").trim();

			// Initializes the file manager
			manager.init();

			// Setup our SFTP configuration
			FileSystemOptions opts = new FileSystemOptions();
			SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
			SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
			SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

			// Create the SFTP URI using the host name, userid, password, remote path and
			// file name
			String sftpUri = "sftp://" + userId + ":" + password + "@" + serverAddress + "/" + remoteDirectory
					+ fileToDownload;

			// Create local file object
			String filepath = localDirectory + fileToDownload;
			File file = new File(filepath);
			FileObject localFile = manager.resolveFile(file.getAbsolutePath());

			// Create remote file object
			FileObject remoteFile = manager.resolveFile(sftpUri, opts);

			// Copy local file to sftp server
			localFile.copyFrom(remoteFile, Selectors.SELECT_SELF);
			System.out.println("File download successful");

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			manager.close();
		}

		return true;
	}

}