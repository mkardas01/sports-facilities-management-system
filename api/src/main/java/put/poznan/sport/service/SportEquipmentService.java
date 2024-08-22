package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.SportEquipment.CreateSportEquipment;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.repository.SportEquipmentRepository;

import java.util.List;
import java.util.Optional;

public interface SportEquipmentService {


    public List<SportEquipment> getAllEquipments();

    public SportEquipment getEquipmentsById(int id);

    SportEquipment createEquipment(CreateSportEquipment equipment);

    public SportEquipment updateEquipment(SportEquipment sportEquipment);

    public boolean deleteEquipment(int id);
}
