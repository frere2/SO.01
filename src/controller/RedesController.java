package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class RedesController {
	private Process exec(String cmd) {
		try {
			Process proc = Runtime.getRuntime().exec(cmd.split(" "));
			return proc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String os() {
		String name = System.getProperty("os.name");
		return name.contains("Windows") ? "Windows" : "Linux";
	}
	
	public void ip() {
		String sysos = os();
		String ipcall = sysos == "Windows" ? "ipconfig" : "ip addr";
		
		Process proc = exec(ipcall);
        if (proc != null) {
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                	if (line.contains("Adaptador") || line.contains("adapter") || line.contains("eth") || line.contains("inet")) {
                		output.append(line).append("\n");
                    } else if (line.contains("IPv4") || line.contains("inet ")) {
                    	output.append(line).append("\n");
                    }
                }
                reader.close();
                JOptionPane.showMessageDialog(null, output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void ping() {
		String sysos = os();
		String pingcall = sysos == "Windows" ? 
				"ping -4 -n 10 www.google.com.br" : "ping -4 -c 10 www.google.com.br";
		
		Process proc = exec(pingcall);
        if (proc != null) {
        	if (proc != null) {
                StringBuilder output = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("ms, M") || line.contains("avg")) {
                            System.out.println(line.trim());
                        }
                    }
                    reader.close();
                    JOptionPane.showMessageDialog(null, output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
