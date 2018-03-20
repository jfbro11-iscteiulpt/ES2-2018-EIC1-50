package org.iscte_iul.es2.ES_2018_EIC1_50;

import java.awt.EventQueue;


public class Main {
	
    	public static void main(String[] args) {
    	
    	
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					gui frame = new gui();
    					frame.setVisible(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
}
          