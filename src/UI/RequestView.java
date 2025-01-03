package UI;

import javax.swing.*;
import java.awt.*;

public class RequestView {
    private final String request;
    private final String ip;

    public RequestView(String request, String ip) {
        this.request = request;
        this.ip = ip;
    }

    public boolean showRequest() {
        int option = JOptionPane.showConfirmDialog(
                null,
                "Yêu cầu từ địa chỉ IP: " + ip + "\n Bạn có muốn chấp nhận yêu cầu: \n" + request,
                "Yêu cầu từ Client.",
                JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }
}
