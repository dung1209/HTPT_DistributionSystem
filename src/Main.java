import UI.RequestView;
import UI.WelcomeView;
import com.springMVC.entity.User;
import enums.Constants;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.List;

public class Main {
    private static WelcomeView welcomeView = new WelcomeView();
    private String branchserver;
    private Object response;
    public static void main(String[] args) {

        welcomeView.setVisible(true);

        try (ServerSocket distribution = new ServerSocket(9091)) {
            while (true) {
                Socket socket = distribution.accept();
                System.out.println("Client connected");
                System.out.println("Client IP:" + socket.getInetAddress().getHostName());
                Main main = new Main();
                Thread t = new Thread(main.new ClientHandler(socket));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {

        private Socket socket;

        public ClientHandler(Socket socket) {
            super();
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Waiting..........");
                String request = in.readLine();
                System.out.println("Title: " + request);

                String clientid = socket.getInetAddress().getHostName();

                RequestView requestView = new RequestView(request,clientid);
                boolean isAccepted = requestView.showRequest();
                String branch = request.substring(request.length()-1);
                branchserver = branch;
                welcomeView.setStringClient(branch,clientid,processRequest(request));
                if (isAccepted) {
                    String processedRequest = processRequest(request);
                    String server = request.substring(request.length() - 3);
                    switch (processedRequest) {
                        case "GET_LIST_CS1":
                            Object response = forwardToServer(processedRequest, null, Constants.SERVER_ADDRESS, Constants.PORT_NUMBER, clientid);
                            out.writeObject(response);
                            break;
                        case "GET_LIST_CS2":
                            Object response4 = forwardToServer(processedRequest, null, Constants.SERVER_ADDRESS_2, Constants.PORT_NUMBER_2, clientid);
                            out.writeObject(response4);
                            break;
                        default:
                            if (server.equals("CS1")) {
                                Object user = (User) in.readObject();
                                Object response1 = forwardToServer(processedRequest, (User) user, Constants.SERVER_ADDRESS, Constants.PORT_NUMBER, clientid);
                                out.writeObject(response1);

                            } else {
                                Object user2 = (User) in.readObject();
                                Object response2 = forwardToServer(processedRequest, (User) user2, Constants.SERVER_ADDRESS_2, Constants.PORT_NUMBER_2, clientid);
                                out.writeObject(response2);
                            }
                            break;
                    }
                } else {
                    out.writeObject("Yêu cầu đã bị từ chối bởi Coordinator.");
                }
                out.flush();
                welcomeView.setStringServer(String.valueOf(response),branchserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String processRequest(String request) {
        return request;
    }

    private Object forwardToServer(String request, User user, String addressserver, int portserrver, String clientid) {
        try(Socket serverSocket = new Socket(addressserver, portserrver)) {

            ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

            out.writeBytes(request + '\n');
            out.writeUTF(clientid + '\n');
            out.writeObject(user);
            out.flush();
            response = in.readObject();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            welcomeView.setStringServer("Lỗi khi gửi dữ liệu tới Server.",branchserver);
            return "Lỗi khi gửi dữ liệu tới Server.";
        }
    }


}