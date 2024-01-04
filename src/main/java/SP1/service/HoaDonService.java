package SP1.service;

import SP1.dto.HoaDonCustom;
import SP1.entity.ChiTietHoaDon;
import SP1.entity.HoaDon;
import SP1.entity.SanPham;
import SP1.repo.ChiTietHoaDonRepo;
import SP1.repo.HoaDonRepo;
import SP1.repo.SanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class HoaDonService implements IHoaDon{
    @Autowired
    private HoaDonRepo hoaDonRepo;
    @Autowired
    private ChiTietHoaDonRepo chiTietHoaDonRepo;
    @Autowired
    private SanPhamRepo sanPhamRepo;

    private String taoMaGiaoDichDuyNhat(){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String ngayDinhDang = now.format(formatter);

        int soLuongDon = laySoLuongDonTrongNgay(ngayDinhDang);
        String chiSo = String.format("%03d", soLuongDon + 1);
        String maGD = ngayDinhDang + "_" + chiSo;
        return maGD;
    }
    private int laySoLuongDonTrongNgay(String formattedDate) {
        int soLuongDon = hoaDonRepo.demSoHoaDonTrongNgay(formattedDate);
        return soLuongDon;
    }
    @Override
    public String themHoaDon(HoaDon hoaDon) {
        String maGiaoDich = taoMaGiaoDichDuyNhat();
        hoaDon.setMaGiaoDich(maGiaoDich);
        hoaDon.setThoiGianTao(LocalDate.now());
        hoaDon.setTongTien(0.0);

        double tongTien = hoaDon.getTongTien();
        for (ChiTietHoaDon chiTietHoaDon: hoaDon.getChiTietHoaDons()) {
            Optional<SanPham> sanPham = sanPhamRepo.findById(chiTietHoaDon.getSanPham().getSanPhamId());
            if (sanPham.isEmpty()) {
                return "Sản phẩm chưa tồn tại. Vui lòng kiểm tra lại!";
            }
            else {
                hoaDonRepo.save(hoaDon);
                SanPham sp = sanPham.get();
                chiTietHoaDon.setThanhTien(chiTietHoaDon.getSoLuong() * sp.getGiaThanh());
                tongTien += chiTietHoaDon.getThanhTien();
                hoaDon.setTongTien(tongTien);
                chiTietHoaDon.setHoaDon(hoaDon);
                chiTietHoaDonRepo.save(chiTietHoaDon);
            }
        }
        return "Them thanh cong hoa don";
    }

    @Override
    public String suaHoaDon(HoaDon hoaDonSua) {
        hoaDonSua.setThoiGianCapNhat(LocalDate.now());
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(hoaDonSua.getHoaDonId());
        if (hoaDonOptional.isEmpty()) {
            return "Hoa don khong ton tai";
        }

        HoaDon hoaDon = hoaDonOptional.get();
        if (hoaDonSua.getChiTietHoaDons() != null) {
            hoaDon.setChiTietHoaDons(hoaDonSua.getChiTietHoaDons());
        }

        double tongTien = 0.0;
        for (ChiTietHoaDon chiTietHoaDon: hoaDon.getChiTietHoaDons()) {
            SanPham sanPham = sanPhamRepo.findById(chiTietHoaDon.getSanPham().getSanPhamId()).get();
//            sanPham.setGiaThanh(chiTietHoaDon.getSanPham().getGiaThanh());
//            chiTietHoaDon.setSanPham(sanPham);
            chiTietHoaDon.setThanhTien(chiTietHoaDon.getSoLuong() * sanPham.getGiaThanh());
            tongTien += chiTietHoaDon.getThanhTien();
            hoaDon.setTongTien(tongTien);
            chiTietHoaDon.setHoaDon(hoaDon);
            chiTietHoaDonRepo.save(chiTietHoaDon);
//            sanPhamRepo.save(sanPham);
        }
        hoaDon.setThoiGianCapNhat(LocalDate.now());
        hoaDonRepo.save(hoaDon);
//        chiTietHoaDonRepo.save(hoaDon.getChiTietHoaDons());
        return "Hoa don da dc cap nhat thanh cong";
    }

    @Override
    public String xoaHoaDon(int hoaDonId) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(hoaDonId);
        if (hoaDonOptional.isEmpty()) {
            return "Ko thể xóa vì hóa đơn ko tồn tại";
        }
        chiTietHoaDonRepo.findAll().forEach(x -> {
            HoaDon hoaDon = x.getHoaDon();
            if (hoaDon != null && hoaDon.getHoaDonId() == hoaDonId) {
                chiTietHoaDonRepo.delete(x);
            }
        });
        hoaDonRepo.deleteById(hoaDonId);
        return "Xóa hóa đơn thành công";
    }

    @Override
    public Page<HoaDonCustom> layHoaDonTheoNamThang(int year, int month, Pageable page) {
        return hoaDonRepo.layHoaDonTheoNamThang(year,month,page);
    }

    @Override
    public Page<HoaDonCustom> layHoaDonTrongKhoang(int month, int ngayMot, int ngayHai, Pageable page) {
        return hoaDonRepo.layHoaDonTrongKhoang(month,ngayMot,ngayHai,page);
    }

    @Override
    public Page<HoaDonCustom> layHoaDonTheoTongTien(double Money, double money, Pageable page) {
        return hoaDonRepo.layHoaDonTheoTongTien(Money,money,page);
    }

    @Override
    public Page<HoaDonCustom> layHoaDonTheoMaGDHoacTenHD(String maGD, String tenHD, Pageable page) {
        return hoaDonRepo.layHoaDonTheoMaGDHoacTenHD(maGD, tenHD, page);
    }

    @Override
    public Optional<HoaDon> layHoaDonVaChiTiet(int hoaDonId,Pageable page) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(hoaDonId,page);
        return hoaDonOptional;

//        HoaDon res  =  hoaDonRepo.findById(hoaDonId).get();
//        return (HoaDonCustom) res;
    }
}
