package io.cygnuxltb.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class OrderTableFrame extends JFrame {
    public OrderTableFrame() {
        super("Order Table");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Order ID", "Customer Name", "Order Date", "Total Amount"};
        Object[][] data = {
                {"1001", "John Doe", "2023-04-15", 1500.0},
                {"1002", "Jane Smith", "2023-04-16", 2000.0},
                {"1003", "Bob Johnson", "2023-04-17", 500.0},
                {"1004", "Alice Williams", "2023-04-18", 1000.0}
        };
        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        OrderTableFrame frame = new OrderTableFrame();
        frame.setVisible(true);
    }
}
