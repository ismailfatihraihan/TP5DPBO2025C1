import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JComboBox<String> jurusanComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel jurusanLabel;

    // constructor
    public Menu() {
        // Inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // Isi listMahasiswa
        database = new Database();

        // Isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // Ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // Atur isi combo box jenis kelamin
        String[] jenisKelaminData = {"", "Laki-Laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel<>(jenisKelaminData));

        // Atur isi combo box jurusan
        String[] jurusanData = {"", "Informatika", "Sistem Informasi", "Teknik Komputer", "Data Science"};
        jurusanComboBox.setModel(new DefaultComboBoxModel<>(jurusanData)); // Menambahkan combo box untuk jurusan

        // Sembunyikan button delete
        deleteButton.setVisible(false);

        // Saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // Saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });

        // Saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // Simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedJurusan = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString(); // Ambil jurusan

                // Ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                jurusanComboBox.setSelectedItem(selectedJurusan); // Set combo box jurusan

                // Ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // Tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // Tentukan kolom tabel (tambahkan kolom "Jurusan")
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Jurusan"};

        // Buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            int i = 0;
            while (resultSet.next()){
                Object[] row = new Object[5]; // Tambah satu elemen untuk jurusan
                row[0] = i + 1; // No urut
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("jurusan");
                
                temp.addRow(row);
                i++;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return temp;
    }


    public void insertData() {
        // Ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String jurusan = jurusanComboBox.getSelectedItem().toString(); // Ambil jurusan dari ComboBox

        // Validasi input (pastikan semua field terisi)
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || jurusan.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Hentikan proses jika ada field kosong
        }

        // Query INSERT dengan VALUES langsung (karena masih pakai Statement biasa)
        String sql = "INSERT INTO mahasiswa (nim, nama, jenis_kelamin, jurusan) VALUES ('" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + jurusan + "')";

        try {
            database.insertUpdateDeleteQuery(sql);

            // Update tabel
            mahasiswaTable.setModel(setTable());

            // Bersihkan form
            clearForm();

            // Feedback sukses
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");

        } catch (RuntimeException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "⚠️ ERROR: NIM sudah terdaftar! Gunakan NIM lain.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void updateData() {
        // Ambil data dari form
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String jurusan = jurusanComboBox.getSelectedItem().toString(); // Ambil nilai jurusan dari ComboBox

        // Validasi input (pastikan semua field terisi)
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || jurusan.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Hentikan proses jika ada field kosong
        }

        String sql = "UPDATE mahasiswa SET nama='" + nama + "', jenis_kelamin='" + jenisKelamin + "', jurusan='" + jurusan + "' WHERE nim='" + nim + "';";
        database.insertUpdateDeleteQuery(sql);

        mahasiswaTable.setModel(setTable());
        clearForm();

        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
    }


    public void deleteData() {
            // Pastikan ada data yang dipilih sebelum menghapus
            if (selectedIndex != -1) {
                // Tampilkan konfirmasi sebelum menghapus data
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Hapus data?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION
                );

                // Jika pengguna memilih "Yes", hapus data
                if (confirm == JOptionPane.YES_OPTION) {
                    String nim = mahasiswaTable.getValueAt(selectedIndex, 1).toString();
                    String sql = "DELETE FROM mahasiswa WHERE nim='" + nim + "';";
                    database.insertUpdateDeleteQuery(sql);

                    mahasiswaTable.setModel(setTable());
                    clearForm();

                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            }
        }


    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        jurusanComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "Informatika"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "Data Science"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "Informatika"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "Data Science"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "Informatika"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "Data Science"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "Informatika"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "Data Science"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "Informatika"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "Sistem Informasi"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "Teknik Komputer"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "Data Science"));
    }
}
