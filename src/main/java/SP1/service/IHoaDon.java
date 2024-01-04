package SP1.service;

import SP1.dto.HoaDonCustom;
import SP1.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IHoaDon {
    public String themHoaDon(HoaDon hoaDon);
    public String suaHoaDon(HoaDon hoaDon);
    public String xoaHoaDon(int hoaDonId);
    public Page<HoaDonCustom> layHoaDonTheoNamThang(int year, int month, Pageable  page);
    public Page<HoaDonCustom> layHoaDonTrongKhoang(int month, int ngayMot, int ngayHai, Pageable page);
    public Page<HoaDonCustom> layHoaDonTheoTongTien(double Money, double money, Pageable page);
    public Page<HoaDonCustom> layHoaDonTheoMaGDHoacTenHD(String maGD, String tenHD, Pageable page);
    public Optional<HoaDon> layHoaDonVaChiTiet(int hoaDonId, Pageable page);
}
