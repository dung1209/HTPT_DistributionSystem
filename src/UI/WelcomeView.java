package UI;

import com.springMVC.entity.User;
import enums.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import socket.Client;

public class WelcomeView extends JFrame {

  private JPanel contentPane;
  private JButton deleteButton;
  private JButton saveButton;
  private JButton addButton;
  private JButton getListBtn;
  private JComboBox<String> branchCombobox;
  private JTable userList;
  private JTextField tfClientId;
  private JTextField tfClientRequest;
  private JTextField tfServerResponse;
  private JTextField tfClientBranch;
  private JTextField tfPassword;
  private JTextField tfBranch;
  private JTextField tfDob;
  private JPanel infoUserPanel;
  private JTextField tfServerBranch;
  private Client client;
  private List<User> users;

  private void init() {
  }


  public WelcomeView() {
    super(Constants.TITLE);
    init();
    setContentPane(contentPane);
    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);




    // Create a new instance of the Client class
//    client = new Client(Constants.SERVER_ADDRESS, Constants.PORT_NUMBER);

  }

  public void serverSocket(String address,int port){
      client = new Client(address,port);
  }

  public void setStringClient(String branch,String ip,String request){
    tfClientBranch.setText(branch);
    tfClientId.setText(ip);
    tfClientRequest.setText(request);
  }

  public void setStringServer(String response,String branch){
    tfServerResponse.setText(response);
    tfServerBranch.setText(branch);
  }


}
