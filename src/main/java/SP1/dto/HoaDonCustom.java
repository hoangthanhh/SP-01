package SP1.dto;
import SP1.entity.ChiTietHoaDon;

import java.time.LocalDate;
import java.util.List;

public interface HoaDonCustom {
    Integer getHoaDonId();
    String getTenHoaDon();
    String getMaGiaoDich();
    LocalDate getThoiGianTao();
    LocalDate getThoiGianCapNhat();
    String getGhiChu();
    Double getTongTien();
    Integer getKhachHangId();
}

