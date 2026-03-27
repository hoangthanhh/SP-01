package SP1.payload.DataResponse;

import lombok.Data;

@Data
public class ChiTietHoaDonDTO {
    private Integer chiTietHoaDonId;
    private Integer soLuong;
    private String donViTinh;
    private Double thanhTien;
    private String tenSanPham;
}
