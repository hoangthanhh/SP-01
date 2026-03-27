package SP1.payload.DataRequest;

import lombok.Data;

@Data
public class ThemChiTietRequest {
    private Integer soLuong;
    private String donViTinh;
    private Integer sanPhamId;
}
