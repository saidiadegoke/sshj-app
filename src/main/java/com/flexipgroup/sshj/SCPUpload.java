package com.flexipgroup.sshj;


import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;

import java.io.File;
import java.io.IOException;

/** This example demonstrates uploading of a file over SCP to the SSH server. */
public class SCPUpload {
	static String username = "saidiadegoke";
	static String password = "Password1";

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        SSHClient ssh = new SSHClient();
        //ssh.loadKnownHosts();
        ssh.addHostKeyVerifier(new PromiscuousVerifier());
        ssh.connect("192.168.1.174");
        ssh.authPassword(username, password);
        try {
            //ssh.authPublickey(System.getProperty("user.name"));

            // Present here to demo algorithm renegotiation - could have just put this before connect()
            // Make sure JZlib is in classpath for this to work
            ssh.useCompression();

            final String src = System.getProperty("user.home") + File.separator + "sshj/cover.pdf";
            ssh.newSCPFileTransfer().upload(new FileSystemFile(src), System.getProperty("user.home") + File.separator + "/ssh-dest/covered.pdf");
        } finally {
            ssh.disconnect();
        }
    }
}
