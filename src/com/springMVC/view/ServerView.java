package com.springMVC.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerView extends JFrame {

  private JPanel contentPane;
  private JPanel headerPanel;
  private JPanel centerPanel;
  private JPanel leftPanel;
  private JPanel rightPanel;
  private JPanel clientPanel;
  private JPanel requestPanel;
  private JTextField branchTf;
  private JLabel branchLbl;
  private JTextField requestTf;
  private JLabel requestLbl;
  private JPanel serverPanel;
  private JPanel responsePanel;
  private JTextField responseTf;
  private JLabel responseLbl;
  private JTextField textField1;

  public ServerView() {
    setContentPane(contentPane);
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
