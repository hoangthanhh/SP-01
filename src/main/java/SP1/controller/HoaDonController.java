package SP1.controller;

import SP1.dto.HoaDonCustom;
import SP1.entity.ChiTietHoaDon;
import SP1.entity.HoaDon;
import SP1.service.HoaDonService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "apihoadon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @RequestMapping(value = "themhoadon", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String themHoaDon(@RequestBody String hoaDon, @RequestParam String formattedDate) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        HoaDon hd = gson.fromJson(hoaDon, HoaDon.class);
        return hoaDonService.themHoaDon(hd);
    }

    @RequestMapping(value = "suahoadon", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String suaHoaDon(@RequestBody String hoaDonSua){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();
        HoaDon hd = gson.fromJson(hoaDonSua, HoaDon.class);
        return hoaDonService.suaHoaDon(hd);
    }

    @RequestMapping(value = "xoahoadon", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String xoaHoaDon(@RequestParam int hoaDonId) {
        return hoaDonService.xoaHoaDon(hoaDonId);
    }

    @RequestMapping(value = "layhoadontheonamthang", method = RequestMethod.GET)
    public Page<HoaDonCustom> layHoaDonTheoNamThang(@RequestParam int year, @RequestParam int month, Integer pageVar, Integer size) {
        if (pageVar == null) {
            pageVar = 1;
        }
        if (size == null) {
            size = 1;
        }
        Pageable page = PageRequest.of(pageVar,size);
        return hoaDonService.layHoaDonTheoNamThang(year,month,page);
    }

    @RequestMapping(value = "layhoadontrongkhoang", method = RequestMethod.GET)
    public Page<HoaDonCustom> layHoaDonTrongKhoang(@RequestParam int month, @RequestParam int ngayMot, @RequestParam int ngayHai,Integer pageVar,Integer size) {
        if (pageVar == null) {
            pageVar = 1;
        }
        if (size == null) {
            size = 1;
        }
        Pageable page = PageRequest.of(pageVar,size);
        return hoaDonService.layHoaDonTrongKhoang(month,ngayMot,ngayHai,page);
    }

    @RequestMapping(value = "layhoadontheotongtien", method = RequestMethod.GET)
    public Page<HoaDonCustom> layHoaDonTheoTongTien(@RequestParam int Money, @RequestParam int money,Integer pageVar,Integer size) {
        if (pageVar == null) {
            pageVar = 1;
        }
        if (size == null) {
            size = 1;
        }
        Pageable page = PageRequest.of(pageVar,size);
        return hoaDonService.layHoaDonTheoTongTien(Money,money,page);
    }

    @RequestMapping(value = "layhoadontheomagdhoactenhd", method = RequestMethod.GET)
    public Page<HoaDonCustom> layHoaDonTheoMaGDHoacTenHD(@RequestParam String maGD, @RequestParam String tenHD,Integer pageVar,Integer size) {
        if (pageVar == null) {
            pageVar = 1;
        }
        if (size == null) {
            size = 1;
        }
        Pageable page = PageRequest.of(pageVar,size);
        return hoaDonService.layHoaDonTheoMaGDHoacTenHD(maGD, tenHD,page);
    }

    @RequestMapping(value = "layhoadonvachitiet", method = RequestMethod.GET)
    public Optional<HoaDon> layHoaDonVaChiTiet(@RequestParam int hoaDonId,Integer pageVar,Integer size) {
        if (pageVar == null) {
            pageVar = 1;
        }
        if (size == null) {
            size = 1;
        }
        Pageable page = PageRequest.of(pageVar,size);
        return hoaDonService.layHoaDonVaChiTiet(hoaDonId,page);
    }
}
