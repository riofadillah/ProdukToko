package kelas.controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {

    @FXML private TextField cariField;
    @FXML private ComboBox<String> filterBox;
    @FXML private TilePane tileKatalog;

    private Navigator navigator;
    private DataProduk data;

    @FXML
    private void initialize() {
        filterBox.getItems().addAll("Semua", "Makanan", "Minuman");
        filterBox.setValue("Semua");
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        muatKatalog(data.getDaftar());
    }

    private void muatKatalog(ObservableList<Produk> daftarProduk) {
        tileKatalog.getChildren().clear();

        if (daftarProduk.isEmpty()) {
            Label kosongLabel = new Label("Belum ada produk yang tersedia.");
            kosongLabel.getStyleClass().add("muted");
            tileKatalog.getChildren().add(kosongLabel);
            return;
        }

        for (Produk p : daftarProduk) {
            tileKatalog.getChildren().add(buatKartuProduk(p));
        }
    }

    private VBox buatKartuProduk(Produk p) {
        VBox card = new VBox(8.0);
        card.getStyleClass().add("card-produk");
        card.setAlignment(Pos.TOP_CENTER);
        card.setPrefWidth(220.0);
        card.setPadding(new Insets(12.0));

        
        ImageView imgView = new ImageView();
        imgView.setFitWidth(190.0);
        imgView.setFitHeight(130.0);
        imgView.setPreserveRatio(true);
        if (p.getGambarUri() != null && !p.getGambarUri().isEmpty()) {
            imgView.setImage(new Image(p.getGambarUri(), 190, 130, true, true));
        }

        Label lblNama = new Label(p.getNamaProduk());
        lblNama.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #1e293b;");
        lblNama.setWrapText(true);

        Label lblKategori = new Label(p.getKategori());
        lblKategori.setStyle("-fx-background-color: #ede9fe; -fx-text-fill: #6d28d9; -fx-padding: 3 8; -fx-background-radius: 6; -fx-font-size: 11px; -fx-font-weight: bold;");

        Label lblHarga = new Label(String.format("Rp %,d", p.getHarga()));
        lblHarga.setStyle("-fx-text-fill: #ea580c; -fx-font-weight: 800; -fx-font-size: 14px;");

        Button btnDetail = new Button("Detail");
        btnDetail.setMaxWidth(Double.MAX_VALUE);
        btnDetail.setOnAction(e -> {
            try {
                navigator.bukaDetail(p);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        card.getChildren().addAll(imgView, lblNama, lblKategori, lblHarga, btnDetail);
        return card;
    }

    @FXML
    private void cari() {
        if (data == null) return;

        String keyword = cariField.getText() == null ? "" : cariField.getText().trim().toLowerCase();
        String kategoriPilihan = filterBox.getValue();

        ObservableList<Produk> hasilFilter = FXCollections.observableArrayList();
        for (Produk p : data.getDaftar()) {
            boolean cocokNama = p.getNamaProduk().toLowerCase().contains(keyword);
            boolean cocokKategori = "Semua".equals(kategoriPilihan) || p.getKategori().equalsIgnoreCase(kategoriPilihan);

            if (cocokNama && cocokKategori) {
                hasilFilter.add(p);
            }
        }
        muatKatalog(hasilFilter);
    }

    @FXML
    private void reset() {
        cariField.clear();
        filterBox.setValue("Semua");
        if (data != null) {
            muatKatalog(data.getDaftar());
        }
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}