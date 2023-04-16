package io.cygnuxltb.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        super("Login");

        // 创建界面元素
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // 添加事件监听器
        loginButton.addActionListener(this);

        // 将元素添加到面板
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        // 将面板添加到窗口
        getContentPane().add(panel, BorderLayout.CENTER);

        // 设置窗口大小和关闭行为
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        // 获取输入的用户名和密码
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // 检查用户名和密码是否正确
        if (username.equals("admin") && password.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.");
        }
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
    }
}

