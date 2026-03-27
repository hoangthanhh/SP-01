package SP1.payload.DataResponse;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
public class HoaDonDTO {
    private String tenHoaDon;
    private String maGiaoDich;
    private LocalDate thoiGianTao;
    private LocalDate thoiGianCapNhat;
    private String ghiChu;
    private Double tongTien;
    private String tenKhachHang;
    private Set<ChiTietHoaDonDTO> chiTietHoaDons;
}
