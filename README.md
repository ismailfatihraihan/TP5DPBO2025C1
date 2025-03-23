# Janji
Saya Ismail Fatih Raihan dengan NIM 2307840 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.  

# Aplikasi Manajemen Data Mahasiswa

## 📌 Deskripsi Program

Aplikasi ini merupakan sistem manajemen data mahasiswa berbasis GUI yang dikembangkan menggunakan **Java Swing**. Aplikasi ini memungkinkan pengguna untuk menambah, memperbarui, menghapus, dan menampilkan data mahasiswa yang terdiri dari **NIM, Nama, Jenis Kelamin, dan Jurusan**. Sekarang, aplikasi juga mendukung penyimpanan data ke dalam **database**, serta menerapkan **validasi NIM unik** dan memastikan **semua form diisi sebelum menambah atau menghapus data**.

## 🛠️ Fitur Program

✅ **Menampilkan daftar mahasiswa** dalam tabel yang terhubung ke database.

✅ **Menambahkan mahasiswa baru** dengan input NIM, Nama, Jenis Kelamin, dan Jurusan.

✅ **Memastikan NIM unik**, tidak boleh ada NIM yang sama.

✅ **Memastikan semua form terisi** sebelum melakukan aksi **Add/Delete**.

✅ **Memperbarui data mahasiswa** yang sudah ada.

✅ **Menghapus mahasiswa** dengan konfirmasi.

✅ **Form otomatis dibersihkan** setelah aksi dilakukan.

## 🗐 Desain Program

### **Struktur Data Mahasiswa**
Setiap mahasiswa direpresentasikan dengan class `Mahasiswa`, yang memiliki atribut berikut:
- **`nim`** (String) → Nomor Induk Mahasiswa (unik).
- **`nama`** (String) → Nama lengkap mahasiswa.
- **`jenisKelamin`** (String) → Jenis kelamin mahasiswa (Laki-laki/Perempuan).
- **`jurusan`** (String) → Program studi mahasiswa.

### **Penyimpanan Data**
Sekarang data mahasiswa disimpan dalam **database**, bukan hanya ArrayList. Database digunakan untuk menyimpan, mengambil, memperbarui, dan menghapus data mahasiswa.

### **Desain GUI**
Aplikasi ini dirancang menggunakan **Java Swing** dengan komponen berikut:
- **`JTable`** → Menampilkan daftar mahasiswa.
- **`JTextField`** → Input untuk NIM dan Nama.
- **`JComboBox`** → Pilihan untuk Jenis Kelamin dan Jurusan.
- **`JButton`** → Untuk aksi tambah, perbarui, hapus, dan reset.
- **`JPanel`** → Untuk mengatur layout tampilan.
- **`JOptionPane`** → Untuk menampilkan konfirmasi dan pesan notifikasi.

Tata letak utama menggunakan **`BorderLayout`**, di mana:
- **Bagian Utama (Center)** → `JTable` untuk menampilkan data.
- **Bagian Atas (North)** → Form input (NIM, Nama, Jenis Kelamin, Jurusan).
- **Bagian Bawah (South)** → Tombol aksi (Add, Update, Delete, Clear).

## 🔄 Penjelasan Alur Program

1️⃣ **Pengguna membuka aplikasi** dan melihat tabel daftar mahasiswa yang diambil dari database.

2️⃣ **Pengguna dapat menambahkan mahasiswa baru** dengan mengisi **NIM, Nama, Jenis Kelamin, dan Jurusan**, lalu menekan tombol **Add**. Jika NIM sudah ada atau ada form yang kosong, input akan ditolak.

3️⃣ **Jika ingin memperbarui data**, pengguna memilih baris pada tabel, lalu mengedit dan menekan tombol **Update**.

4️⃣ **Jika ingin menghapus data**, pengguna memilih baris lalu menekan tombol **Delete**, dengan konfirmasi sebelum menghapus. Jika ada form yang kosong, aksi ini tidak bisa dilakukan.

5️⃣ **Setiap perubahan langsung diperbarui di database**, dan **form otomatis dibersihkan** setelah aksi.

## 📷 Dokumentasi Tampilan
![Dokumentasi](https://github.com/user-attachments/assets/c61c1a87-0615-4cbd-aa20-e2569c96a4b4)


## 🏢 Teknologi yang Digunakan

- **Java Swing** – untuk tampilan GUI
- **MySQL/PostgreSQL** – untuk penyimpanan data
- **JDBC** – untuk koneksi database
- **JTable** – untuk menampilkan daftar mahasiswa
- **JComboBox** – untuk memilih jenis kelamin & jurusan
- **JOptionPane** – untuk konfirmasi dan pesan notifikasi
