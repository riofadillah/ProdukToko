package kelas.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kelas.model.Produk;

public class DataProduk {

    private static final ObservableList<Produk> daftar = FXCollections.observableArrayList();

    public static ObservableList<Produk> getDaftar() {
        return daftar;
    }

    public static void tambah(Produk produk) {
        daftar.add(produk);
    }

    public static void hapus(Produk produk) {
        daftar.remove(produk);
    }
}