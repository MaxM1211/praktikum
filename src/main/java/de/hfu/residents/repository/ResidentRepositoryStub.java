package de.hfu.residents.repository;

import de.hfu.residents.domain.Resident;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ResidentRepositoryStub implements ResidentRepository {
    List<Resident> residentList = new ArrayList<>();


    @Override
    public List<Resident> getResidents() {
        return residentList;
    }

    public void addResident(Resident residen) {
        residentList.add(residen);

    }
}
