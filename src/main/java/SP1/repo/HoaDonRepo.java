package SP1.repo;

import SP1.dto.HoaDonCustom;
import SP1.entity.ChiTietHoaDon;
import SP1.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepo extends JpaRepository<HoaDon,Integer> {
    @Query("SELECT COUNT(*) FROM HoaDon h WHERE DATE_FORMAT(h.thoiGianTao, '%Y%m%d') = :formattedDate")
    int demSoHoaDonTrongNgay(@Param("formattedDate") String formattedDate);

    @Query(value = "SELECT * FROM hoadon WHERE YEAR(thoiGianTao) = :year AND MONTH(thoiGianTao) = :month", nativeQuery = true)
    Page<HoaDonCustom> layHoaDonTheoNamThang(@Param("year") int year, @Param("month") int month, Pageable page);

    @Query(value = "SELECT * FROM hoadon WHERE MONTH(thoigiantao) = :month AND day(thoigiantao) BETWEEN :ngayMot AND :ngayHai", nativeQuery = true)
    Page<HoaDonCustom> layHoaDonTrongKhoang(@Param("month") int month, @Param("ngayMot") int ngayMot, @Param("ngayHai") int ngayHai, Pageable page);

    @Query(value = "select * from hoadon where tongtien between :Money and :money", nativeQuery = true)
    Page<HoaDonCustom> layHoaDonTheoTongTien(@Param("Money") double Money, @Param("money") double money, Pageable page);

    @Query(value = "SELECT * FROM hoadon WHERE (:maGD IS NULL OR magiaodich = :maGD) OR (:tenHD IS NULL OR tenhoadon = :tenHD)", nativeQuery = true)
    Page<HoaDonCustom> layHoaDonTheoMaGDHoacTenHD(@Param("maGD") String maGD, @Param("tenHD") String tenHD, Pageable page);

//    @Query(value = "SELECT * FROM hoadon JOIN chitiethoadon ON hoadon.hoa_don_id = chitiethoadon.hoadonid WHERE hoadon.hoa_don_id = :hoaDonId ORDER BY hoadon.thoigiantao DESC", nativeQuery = true)
//    List<HoaDonCustom> layHoaDonVaChiTiet(@Param("hoaDonId") int hoaDonId);

    @Query(value = "select * from chitiethoadon where hoadonid = :hoaDonId", nativeQuery = true)
    HoaDon layHoaDonVaChiTiet(@Param("hoaDonId") int hoaDonId,Pageable page);
}
