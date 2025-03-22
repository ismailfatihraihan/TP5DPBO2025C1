public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private String jurusan;

    public Mahasiswa(String nim, String nama, String jenisKelamin, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jurusan = jurusan;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setJurusan(String jurusan){
        this.jurusan = jurusan;
    }

    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public String getJurusan(){
        return this.jurusan;
    }
}
