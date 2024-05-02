package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;

import java.util.ArrayList;
import java.util.List;

public class KeranjangRepository {
    private List<Keranjang> keranjangList = new ArrayList<>();
    public Keranjang save(Keranjang keranjang) {
        return new Keranjang();
    }

    public Keranjang findById(String id) {
        return new Keranjang();
    }
}
