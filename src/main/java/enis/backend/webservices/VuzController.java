package enis.backend.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import enis.backend.model.Vuz;
import enis.backend.service.VuzService;

@RestController
public class VuzController {
    @Autowired
    private VuzService service;

    @RequestMapping("/getVuzByVuzCis")
    public Vuz getVuzByVuzCis(@RequestParam(value="vuzCis", defaultValue="0") String vuzCis) {
        Vuz vuz = getVuz(Long.parseLong(vuzCis));
        if (vuz != null) {
            return vuz;
        }
        return null;
    }

    @RequestMapping("/setVuzKm")
    public Boolean setVuzKm(@RequestParam(value="vuzCis", defaultValue="0") String vuzCis,
                              @RequestParam(value="kilometers", defaultValue="0") String kilometers) {
        Vuz vuz = getVuz(Long.parseLong(vuzCis));
        if (vuz != null) {
            vuz.getStavKm().setNajetKm(Long.parseLong(kilometers));
            return true;
        }
        return false;
    }

    private Vuz getVuz(Long vuzCis) {
        return service.getVuz(vuzCis);
    }
}
