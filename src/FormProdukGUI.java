import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormProdukGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUDANGin - CRUD Produk");
        frame.setSize(650, 450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblNama = new JLabel("Nama Produk:");
        lblNama.setBounds(30, 30, 120, 25);
        frame.add(lblNama);

        JTextField txtNama = new JTextField();
        txtNama.setBounds(150, 30, 200, 25);
        frame.add(txtNama);

        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setBounds(30, 70, 120, 25);
        frame.add(lblHarga);

        JTextField txtHarga = new JTextField();
        txtHarga.setBounds(150, 70, 200, 25);
        frame.add(txtHarga);

        JButton btnTambah = new JButton("Tambah");
        btnTambah.setBounds(380, 30, 100, 25);
        frame.add(btnTambah);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(380, 70, 100, 25);
        frame.add(btnEdit);

        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBounds(500, 30, 100, 25);
        frame.add(btnHapus);

        JButton btnBersih = new JButton("Bersihkan");
        btnBersih.setBounds(500, 70, 100, 25);
        frame.add(btnBersih);

        String[] kolom = {"Nama Produk", "Harga"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 130, 570, 250);
        frame.add(scrollPane);

        btnTambah.addActionListener(e -> {
            String nama = txtNama.getText();
            String harga = txtHarga.getText();
            if (nama.isEmpty() || harga.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Isi semua data dulu!");
            } else {
                model.addRow(new Object[]{nama, harga});
                txtNama.setText("");
                txtHarga.setText("");
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int i = table.getSelectedRow();
            if (i >= 0) {
                txtNama.setText(model.getValueAt(i, 0).toString());
                txtHarga.setText(model.getValueAt(i, 1).toString());
            }
        });

        btnEdit.addActionListener(e -> {
            int i = table.getSelectedRow();
            if (i >= 0) {
                model.setValueAt(txtNama.getText(), i, 0);
                model.setValueAt(txtHarga.getText(), i, 1);
                JOptionPane.showMessageDialog(frame, "Data berhasil diubah!");
            } else {
                JOptionPane.showMessageDialog(frame, "Pilih data yang ingin diubah!");
            }
        });

        btnHapus.addActionListener(e -> {
            int i = table.getSelectedRow();
            if (i >= 0) {
                model.removeRow(i);
                JOptionPane.showMessageDialog(frame, "Data berhasil dihapus!");
                txtNama.setText("");
                txtHarga.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Pilih data yang ingin dihapus!");
            }
        });

        btnBersih.addActionListener(e -> {
            txtNama.setText("");
            txtHarga.setText("");
            table.clearSelection();
        });

        frame.setVisible(true);
    }
}
