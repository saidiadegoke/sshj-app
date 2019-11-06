package com.flexipgroup.sshj;

import java.io.File;
import java.io.IOException;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;

public class Application {
	static String remoteHost = "192.168.1.174";
	static String username = "saidiadegoke";
	static String password = "Password1";
	
	static String localFile = "/users/saidiadegoke/sshj/cover.pdf"; //"~/sshj/cover.pdf";
	static String remoteDir = "~/ssh-temp";

	public static void main(String[] args) {
		try {
			whenUploadFileUsingSshj_thenSuccess();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	private static SSHClient setupSshj() throws IOException {
	    SSHClient client = new SSHClient();
	    client.addHostKeyVerifier(new PromiscuousVerifier());
	    client.connect(remoteHost);
	    client.authPassword(username, password);
	    return client;
	}
	
	public static void whenUploadFileUsingSshj_thenSuccess() throws IOException {
	    SSHClient sshClient = setupSshj();
	    SFTPClient sftpClient = sshClient.newSFTPClient();
	  System.out.println(System.getProperty("user.home")+ File.separator + "sshj/cover.pdf");
	    final String src = System.getProperty("user.home") + File.separator + "sshj/cover.pdf";
	    final String dest = System.getProperty("user.home") + File.separator +  "/ssh-temp/";
	    sftpClient.put(src, dest + "sshjFile.pdf");
	  
	    sftpClient.close();
	    sshClient.disconnect();
	}

}
