package kelas.model;

public abstract class Produk {
    private String kodeProduk;
    private String namaProduk;
    private int harga;
    private String gambarUri;

    public Produk(String kodeProduk, String namaProduk, int harga, String gambarUri) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.gambarUri = gambarUri;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public int getHarga() {
        return harga;
    }

    public String getGambarUri() {
        return gambarUri;
    }

    public abstract String getKategori();
}