package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {
	public static void main(String[] args) {
		int opt = Integer.parseInt(JOptionPane.showInputDialog("1 - IP\n2 - PING\n3 - Finalizar"));
		RedesController redesController = new RedesController();
		switch (opt) {
			case 1: redesController.ip();
			case 2: redesController.ping();
			case 3: break;
		}
	}
}
