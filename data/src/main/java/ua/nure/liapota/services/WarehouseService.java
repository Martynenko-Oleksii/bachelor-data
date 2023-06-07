package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.*;
import ua.nure.liapota.models.security.Facility;
import ua.nure.liapota.models.security.StandardDepartment;
import ua.nure.liapota.models.warehouse.*;
import ua.nure.liapota.repositories.data.ValueRepository;
import ua.nure.liapota.repositories.security.FacilityRepository;
import ua.nure.liapota.repositories.security.StandardDepartmentRepository;
import ua.nure.liapota.repositories.warehouse.*;

import java.util.List;

@Service
public class WarehouseService {
    private final FacilityRepository facilityRepository;
    private final ValueRepository valueRepository;
    private final StandardDepartmentRepository standardDepartmentRepository;
    private final DepartmentInstanceRepository departmentInstanceRepository;
    private final FacilityWarehouseRepository facilityWarehouseRepository;
    private final FactRepository factRepository;
    private final MeasureRepository measureRepository;
    private final TimePeriodWarehouseRepository timePeriodWarehouseRepository;

    @Autowired
    public WarehouseService(FacilityRepository facilityRepository,
                            ValueRepository valueRepository,
                            StandardDepartmentRepository standardDepartmentRepository,
                            DepartmentInstanceRepository departmentInstanceRepository,
                            FacilityWarehouseRepository facilityWarehouseRepository,
                            FactRepository factRepository,
                            MeasureRepository measureRepository,
                            TimePeriodWarehouseRepository timePeriodWarehouseRepository) {
        this.facilityRepository = facilityRepository;
        this.valueRepository = valueRepository;
        this.standardDepartmentRepository = standardDepartmentRepository;
        this.departmentInstanceRepository = departmentInstanceRepository;
        this.facilityWarehouseRepository = facilityWarehouseRepository;
        this.factRepository = factRepository;
        this.measureRepository = measureRepository;
        this.timePeriodWarehouseRepository = timePeriodWarehouseRepository;
    }

    public FacilityWarehouse getFacilityWarehouse(TimePeriodFacility timePeriod) {
        Facility facility = facilityRepository.findById(timePeriod.getFacilityId()).get();
        FacilityWarehouse facilityWarehouse = new FacilityWarehouse();
        facilityWarehouse.setAbbreviation(facility.getAbbreviation());
        facilityWarehouse.setAddress1(facility.getAddress1());
        facilityWarehouse.setCity(facility.getCity());
        facilityWarehouse.setId(facility.getId());
        facilityWarehouse.setAddress2(facility.getAddress2());
        facilityWarehouse.setContactEmail(facility.getContactEmail());
        facilityWarehouse.setContactPhone(facility.getContactPhone());
        facilityWarehouse.setName(facility.getName());
        facilityWarehouse.setZipCode(facility.getZipCode());
        return facilityWarehouse;
    }

    public TimePeriodWarehouse getTimePeriodWarehouse(TimePeriodFacility timePeriod) {
        TimePeriodWarehouse timePeriodWarehouse = new TimePeriodWarehouse();
        timePeriodWarehouse.setId(timePeriod.getTimePeriod().getId());
        timePeriodWarehouse.setEndDate(timePeriod.getTimePeriod().getEndDate());
        timePeriodWarehouse.setShortName(timePeriod.getTimePeriod().getShortName());
        timePeriodWarehouse.setStartDate(timePeriod.getTimePeriod().getStartDate());
        return timePeriodWarehouse;
    }

    public List<Value> getValuesByTimePeriod(TimePeriodFacility timePeriodFacility) {
        return valueRepository.getValuesByTimePeriod(timePeriodFacility.getId());
    }

    public DepartmentInstanceWarehouse getDepartmentInstance(Value value) {
        DepartmentInstanceWarehouse departmentInstanceWarehouse = new DepartmentInstanceWarehouse();
        CostCenter costCenter = value.getMapping().getCostCenter();
        Department department = costCenter.getDepartment();
        StandardDepartment standardDepartment = standardDepartmentRepository.findById(department
                .getStandardDepartmentId()).get();
        departmentInstanceWarehouse.setStandardDepartmentId(department
                .getStandardDepartmentId());
        departmentInstanceWarehouse.setId(department.getId());
        departmentInstanceWarehouse.setName(department.getName());
        departmentInstanceWarehouse.setCostCenterName(costCenter.getDescription());
        departmentInstanceWarehouse.setStandardDepartmentName(standardDepartment.getName());
        return departmentInstanceWarehouse;
    }

    public Measure getMeasure(Value value) {
        Measure measure = new Measure();
        DepartmentElement departmentElement = value.getMapping().getDepartmentElement();
        measure.setId(departmentElement.getId());
        measure.setDepartmentElementName(departmentElement.getName());
        return measure;
    }

    public void setValue(FacilityWarehouse facilityWarehouse,
                         TimePeriodWarehouse timePeriodWarehouse,
                         DepartmentInstanceWarehouse departmentInstanceWarehouse,
                         Measure measure,
                         Value value) {
        FactKey factKey = new FactKey();
        if (facilityWarehouseRepository.findById(facilityWarehouse.getId()).isEmpty()) {
            facilityWarehouseRepository.save(facilityWarehouse);
        }
        factKey.setFacilityId(facilityWarehouse.getId());

        if (timePeriodWarehouseRepository.findById(timePeriodWarehouse.getId()).isEmpty()) {
            timePeriodWarehouseRepository.save(timePeriodWarehouse);
        }
        factKey.setTimePeriodId(timePeriodWarehouse.getId());

        if (departmentInstanceRepository.findById(departmentInstanceWarehouse.getId()).isEmpty()) {
            departmentInstanceRepository.save(departmentInstanceWarehouse);
        }
        factKey.setDepartmentId(departmentInstanceWarehouse.getId());

        if (measureRepository.findById(measure.getId()).isEmpty()) {
            measureRepository.save(measure);
        }
        factKey.setMeasureId(measure.getId());

        if (factRepository.findById(factKey).isEmpty()) {
            Fact fact = new Fact();
            fact.setValue(value.getValue());
            fact.setFactKey(factKey);
            factRepository.save(fact);
        } else {
            Fact fact = factRepository.findById(factKey).get();
            fact.setValue(fact.getValue() + value.getValue());
            factRepository.save(fact);
        }
    }
}
