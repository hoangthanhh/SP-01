package SP1.payload.DataRequest;

import SP1.payload.DataResponse.ChiTietHoaDonDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class ThemHoaDonRequest {
    private String tenHoaDon;
    private String ghiChu;
    private Integer khachHangId;
    private List<ThemChiTietRequest> chiTietHoaDons;
}
