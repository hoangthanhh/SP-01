package SP1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "sanpham")
@Getter
@Setter
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sanPhamId;

    @Column(name = "tensanpham")
    private String tenSanPham;

    @Column(name = "giathanh")
    private Double giaThanh;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "ngayhethan")
    private LocalDate ngayHetHan;

    @Column(name = "kyhieusanpham")
    private String kyHieuSanPham;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "loaisanphamid")
    private LoaiSanPham loaiSanPham;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sanPham")
//    @JsonIgnoreProperties(value = "vatTu")
//    private Set<ChiTietHoaDon> chiTietHoaDons;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ChiTietHoaDon> chiTietHoaDons;
}
