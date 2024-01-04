package SP1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "khachhang")
@Getter
@Setter
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer khachHangId;

    @Column(name = "hoten")
    private String hoTen;

    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;

    @Column(name = "sdt")
    private String sdt;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "khachHang")
//    @JsonIgnoreProperties(value = "khachHang")
//    private Set<HoaDon> hoaDons;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<HoaDon> hoaDons;
}
