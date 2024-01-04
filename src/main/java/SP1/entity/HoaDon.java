package SP1.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "hoadon")
@Getter
@Setter
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hoaDonId;

    @Column(name = "tenhoadon")
    private String tenHoaDon;

    @Column(name = "magiaodich")
    private String maGiaoDich;

    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;

    @Column(name = "thoigiancapnhat")
    private LocalDate thoiGianCapNhat;

    @Column(name = "ghichu")
    private String ghiChu;

    @Column(name = "tongtien")
    private Double tongTien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "khachhangid")
    private KhachHang khachHang;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ChiTietHoaDon> chiTietHoaDons;

}
