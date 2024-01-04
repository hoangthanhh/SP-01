package SP1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "loaisanpham")
@Getter
@Setter
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loaiSanPhamId;

    @Column(name = "tenloaisanpham")
    private String tenLoaiSanPham;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "loaiSanPham")
//    @JsonIgnoreProperties(value = "loaiSanPham")
//    private Set<SanPham> sanPhams;

    @OneToMany(mappedBy = "loaiSanPham", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<SanPham> sanPhams;
}
