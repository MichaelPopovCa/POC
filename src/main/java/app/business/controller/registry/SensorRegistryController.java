package app.business.controller.registry;

import app.business.enums.Face;
import app.business.service.registry.SensorRegistryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor") // ← здесь задаётся базовый путь
public class SensorRegistryController {

    private final SensorRegistryService sensorRegistryService;

    public SensorRegistryController(SensorRegistryService sensorRegistryService) {
        this.sensorRegistryService = sensorRegistryService;
    }

    @PostMapping("/add")
    public Long registerSensor(@RequestParam Face face) {
        return sensorRegistryService.registerSensor(face);
    }

    @PostMapping("/remove")
    public void removeSensor(@RequestBody Long id) {
        sensorRegistryService.removeSensor(id);

    }
}
