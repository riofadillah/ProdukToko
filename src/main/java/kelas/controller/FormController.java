package kelas.controller;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;
import kelas.model.ProdukMakanan;
import kelas.model.ProdukMinuman;

public class FormController {

    @FXML private TextField txtKode;
    @FXML private TextField txtNama;
    @FXML private ComboBox<String> cbKategori;
    @FXML private TextField txtHarga;
    @FXML private Label lblNamaGambar;
    @FXML private ImageView imgPreview;

    private Navigator navigator;
    private DataProduk data;
    private String gambarUri = null;

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
    }

    @FXML
    public void initialize() {
        cbKategori.getItems().addAll("Makanan", "Minuman");
        cbKategori.getSelectionModel().selectFirst();
    }

    @FXML
    private void handlePilihGambar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Gambar Produk");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("File Gambar", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(txtKode.getScene().getWindow());
        if (file != null) {
            gambarUri = file.toURI().toString();
            if (lblNamaGambar != null) {
                lblNamaGambar.setText(file.getName());
            }
            if (imgPreview != null) {
                imgPreview.setImage(new Image(gambarUri));
            }
        }
    }

    @FXML
    private void handleSimpan() {
        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        String kategori = cbKategori.getValue();
        String hargaStr = txtHarga.getText().trim();

       
        if (kode.isEmpty() || nama.isEmpty() || hargaStr.isEmpty()) {
            tampilkanPeringatan("Input Tidak Valid", "Kode dan Nama Produk tidak boleh kosong.");
            return;
        }

     
        int harga;
        try {
            harga = Integer.parseInt(hargaStr);
            if (harga <= 0) {
                tampilkanPeringatan("Harga Tidak Valid", "Harga produk harus lebih besar dari 0.");
                return;
            }
        } catch (NumberFormatException e) {
            tampilkanPeringatan("Format Salah", "Harga harus berupa bilangan bulat.");
            return;
        }

       
        Produk produkBaru;
        if ("Makanan".equalsIgnoreCase(kategori)) {
            produkBaru = new ProdukMakanan(kode, nama, harga, gambarUri);
        } else {
            produkBaru = new ProdukMinuman(kode, nama, harga, gambarUri);
        }

        data.tambah(produkBaru);

        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBatal() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tampilkanPeringatan(String judul, String pesan) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
}