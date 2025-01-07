package com.bazan.carlend.vehicle;

import java.util.List;

public interface IVehicleService {
    VehicleResponse create(VehicleRequest vehicleRequest) throws Exception;
    List<VehicleResponse> getAllVehicles();
}
