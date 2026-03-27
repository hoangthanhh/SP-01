package SP1.payload.Converter;

import SP1.entity.ChiTietHoaDon;
import SP1.payload.DataResponse.ChiTietHoaDonDTO;
import SP1.repo.SanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChiTietHoaDonConverter {
    @Autowired
    private SanPhamRepo _sanPhamRepo;
    public ChiTietHoaDonDTO entityToDTO(ChiTietHoaDon chiTietHoaDon){
        ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO();
        ct.setDonViTinh(chiTietHoaDon.getDonViTinh());
        ct.setSoLuong(chiTietHoaDon.getSoLuong());
        ct.setThanhTien(chiTietHoaDon.getThanhTien());
        ct.setTenSanPham(_sanPhamRepo.findById(chiTietHoaDon.getSanPham().getSanPhamId()).get().getTenSanPham());
        ct.setChiTietHoaDonId(chiTietHoaDon.getChiTietHoaDonId());
        return ct;
    }
}
