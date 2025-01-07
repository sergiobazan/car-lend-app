package com.bazan.carlend.vehicle;

public class VehicleMapper {

    public static VehicleResponse fromVehicle(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getModel(),
                vehicle.getCharacteristics(),
                vehicle.getPricePerDay(),
                vehicle.getMileage(),
                vehicle.getStatus(),
                vehicle.getCategory()
        );
    }
}
