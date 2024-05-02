package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;

import java.util.ArrayList;
import java.util.List;

public class KeranjangRepository {
    private final List<Keranjang> keranjangList = new ArrayList<>();
    public Keranjang save(Keranjang keranjang) {
        int i = 0;
        for (Keranjang savedKeranjang : keranjangList) {
            if (savedKeranjang.getId().equals(keranjang.getId())) {
                keranjangList.remove(i);
                keranjangList.add(i, keranjang);
                return keranjang;
            }
            i += 1;
        }

        keranjangList.add(keranjang);
        return keranjang;
    }

    public Keranjang findById(String id) {
        for (Keranjang savedKeranjang : keranjangList) {
            if (savedKeranjang.getId().equals(id)) {
                return savedKeranjang;
            }
        }
        return null;
    }
}
