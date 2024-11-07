package com.bookmybus.busbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.busbooking.entity.Bus;
import com.bookmybus.busbooking.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {
	
	@Autowired
	private BusRepository busRepository;

	@Override
	public Bus createBus(Bus bus) {
		// TODO Auto-generated method stub
		return busRepository.save(bus);
	}

	@Override
	public Bus getBusById(Long busId) {
		// TODO Auto-generated method stub
		Optional<Bus> busOptional = busRepository.findById(busId);
        if (busOptional.isPresent()) {
            return busOptional.get();
        } else {
            throw new RuntimeException("Bus not found with id " + busId);
        }
	}

	@Override
	public List<Bus> getAllBuses() {
		// TODO Auto-generated method stub
		return busRepository.findAll();
	}

	@Override
	public Bus updateBus(Long busId, Bus busDetails) {
		// TODO Auto-generated method stub
		Bus existingBus = getBusById(busId);
        existingBus.setBusNumber(busDetails.getBusNumber());
        existingBus.setRoute(busDetails.getRoute());
        existingBus.setSeatsAvailable(busDetails.getSeatsAvailable());
        existingBus.setDepartureTime(busDetails.getDepartureTime());
        existingBus.setArrivalTime(busDetails.getArrivalTime());
        return busRepository.save(existingBus);
	}

	@Override
	public void deleteBus(Long busId) {
		// TODO Auto-generated method stub
		
	}

	
}
