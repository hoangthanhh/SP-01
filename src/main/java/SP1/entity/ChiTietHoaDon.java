package SP1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chitiethoadon")
@Getter
@Setter
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chiTietHoaDonId;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "donvitinh")
    private String donViTinh;

    @Column(name = "thanhTien")
    private Double thanhTien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "hoadonid")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "sanphamid")
    private SanPham sanPham;
}
