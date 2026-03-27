package SP1.payload.Converter;

import SP1.entity.HoaDon;
import SP1.payload.DataResponse.HoaDonDTO;
import SP1.repo.ChiTietHoaDonRepo;
import SP1.repo.KhachHangRepo;
import SP1.repo.SanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HoaDonConverter {
    @Autowired
    private ChiTietHoaDonRepo _chiTietRepo;
    @Autowired
    private SanPhamRepo _sanPhamRepo;
    private final ChiTietHoaDonConverter _converter;
    public HoaDonConverter(ChiTietHoaDonConverter converter){
        _converter = converter;
    }
    @Autowired
    private KhachHangRepo _khachHangRepo;
    public HoaDonDTO entityToDTO(HoaDon hoaDon){
        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        hoaDonDTO.setTenHoaDon(hoaDon.getTenHoaDon());
        hoaDonDTO.setGhiChu(hoaDon.getGhiChu());
        hoaDonDTO.setMaGiaoDich(hoaDon.getMaGiaoDich());
        hoaDonDTO.setThoiGianTao(hoaDon.getThoiGianTao());
        hoaDonDTO.setThoiGianCapNhat(hoaDon.getThoiGianCapNhat());
        hoaDonDTO.setTongTien(hoaDon.getTongTien());
        hoaDonDTO.setTenKhachHang(_khachHangRepo.findById(hoaDon.getKhachHang().getKhachHangId()).get().getHoTen());
        hoaDonDTO.setChiTietHoaDons(_chiTietRepo.findAll().stream().filter(x -> x.getHoaDon().getHoaDonId() == hoaDon.getHoaDonId()).map(_converter::entityToDTO).collect(Collectors.toSet()));
        return hoaDonDTO;
    }
}
