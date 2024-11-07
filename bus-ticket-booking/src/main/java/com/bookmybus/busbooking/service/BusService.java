package com.bookmybus.busbooking.service;

import java.util.List;

import com.bookmybus.busbooking.entity.Bus;

public interface BusService {

	Bus createBus(Bus bus);
    Bus getBusById(Long busId);
    List<Bus> getAllBuses();
    Bus updateBus(Long busId, Bus bus);
    void deleteBus(Long busId);
}
