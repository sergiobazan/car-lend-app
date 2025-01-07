package com.bazan.carlend.vehicle;

import com.bazan.carlend.category.Category;
import com.bazan.carlend.category.ICategoryRepository;
import com.bazan.carlend.kafka.VehicleCreated;
import com.bazan.carlend.kafka.VehicleProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VehicleService implements IVehicleService {

    private final IVehicleRepository vehicleRepository;
    private final ICategoryRepository categoryRepository;
    private final VehicleProducer vehicleProducer;

    @Override
    public VehicleResponse create(VehicleRequest vehicleRequest) throws Exception {
        Category category = categoryRepository.findById(vehicleRequest.categoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        var vehicle = Vehicle.builder()
                .model(vehicleRequest.model())
                .characteristics(vehicleRequest.characteristics())
                .mileage(vehicleRequest.mileage())
                .pricePerDay(vehicleRequest.pricePerDay())
                .status(VehicleStatus.AVAILABLE)
                .category(category)
                .build();

        var vehicleSaved = vehicleRepository.save(vehicle);

        vehicleProducer.send(
                new VehicleCreated(
                        vehicleSaved.getId(),
                        vehicleSaved.getModel(),
                        vehicleSaved.getPricePerDay(),
                        vehicleSaved.getStatus(),
                        category.getName()
                )
        );
        
        return VehicleMapper.fromVehicle(vehicleSaved);
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::fromVehicle)
                .toList();
    }
}
