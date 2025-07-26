package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.dto.StudentRequest;
import org.school.backend.application.mappers.ParentMapper;
import org.school.backend.domain.model.StudentModel;

import static org.school.backend.adapters.mapper.StudentGradeMapper.toDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StudentRecordEntityMapper {

    static StudentRequest convertEntityToModel(final StudentModel entity){
        return StudentRequest.builder()
                .fullName(entity.fullName())
                .nickName(entity.nickName())
                .nik(entity.nik())
                .gender(entity.gender())
                .dateBirth(entity.dateBirth())
                .birthOrder(entity.birthOrder())
                .tribe(entity.tribe())
                .address(entity.address())
                .height(entity.height())
                .weight(entity.weight())
                .gradeClass(toDto(entity.gradeClass()))
                .parent(ParentLogsMapper.convertEntityToModel(entity.parent()))
                .build();
    }

    static List<StudentModel> convertModelsToEntity(final List<StudentLogs> recordEntities){
        List<StudentModel> studentRecordEntities = new ArrayList<>();
        recordEntities.forEach((entity) -> studentRecordEntities.add(new StudentModel(
                entity.getId(),
                entity.getFullName(),
                entity.getNickName(),
                entity.getNik(),
                entity.getGender(),
                entity.getDateBirth(),
                entity.getBirthOrder(),
                null,  // tribe
                null,  // address
                null,  // height
                null,  // weight
                null,  // gradeClass
                null   // parent
                )));
        return studentRecordEntities;
    }

    static Optional<StudentModel> convertModelToEntity(Optional<StudentDetails> entity){
        if(entity.isPresent()){
            return Optional.of(new StudentModel(
                    null,
                    entity.get().fullName,
                    entity.get().nickName,
                    entity.get().nik,
                    entity.get().gender,
                    entity.get().dateBirth,
                    entity.get().birthOrder,
                    entity.get().tribe,
                    entity.get().address,
                    entity.get().height,
                    entity.get().weight,
                    StudentGradeMapper.convertModelToEntity(entity.get().gradeClass),
                    null
            ));
        } else {
            return Optional.empty();
        }
    }
}
