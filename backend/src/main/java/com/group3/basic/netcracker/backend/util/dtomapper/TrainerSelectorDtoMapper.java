package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.TrainerSelectorDto;
import com.group3.basic.netcracker.backend.entity.Trainer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TrainerSelectorDtoMapper {

    public Trainer toTrainer (TrainerSelectorDto tsd) {

        Trainer trainer = new Trainer();
        trainer.setId(tsd.getId());
        trainer.setFname(tsd.getFirstName());
        trainer.setLname(tsd.getLastName());

        return trainer;

    }

    public TrainerSelectorDto toTrainerSelectorDto (Trainer trainer) {

        TrainerSelectorDto tsd = new TrainerSelectorDto();
        tsd.setId(trainer.getId());
        tsd.setFirstName(trainer.getFname());
        tsd.setLastName(trainer.getLname());

        return tsd;

    }

}
