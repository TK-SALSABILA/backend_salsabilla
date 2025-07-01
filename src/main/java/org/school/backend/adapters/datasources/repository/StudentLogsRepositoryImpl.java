package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.school.backend.application.dto.GradeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentLogsRepositoryImpl implements StudentLogsRepository{

    final ApplicationConfigProperties applicationConfigProperties;
    final JpaStudentLogsRepository jpaStudentLogsRepository;
    final JpaGradeRepository jpaGradeRepository;
    final  ElasticSearchRepository elasticSearchRepository;

    public StudentLogsRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaStudentLogsRepository jpaStudentLogsRepository,
            final JpaGradeRepository jpaGradeRepository,
            ElasticSearchRepository elasticSearchRepository
    ){
        this.applicationConfigProperties =applicationConfigProperties;
        this.jpaStudentLogsRepository = jpaStudentLogsRepository;
        this.jpaGradeRepository = jpaGradeRepository;
        this.elasticSearchRepository = elasticSearchRepository;
    }

    @Override
    public List<StudentLogs> findAll(){
        List<StudentLogs> results = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaStudentLogsRepository.findAll()
                    .forEach(entity-> results.add(new StudentLogs(
                            entity.getId(),
                            entity.getFullName(),
                            entity.getNickName(),
                            entity.getNik(),
                            entity.getGender(),
                            entity.getDateBirth(),
                            entity.getBirthOrder()
                    )));
            case "elasticsearch" -> elasticSearchRepository.findAll()
                    .forEach(entity-> results.add(new StudentLogs(
                            entity.getId(),
                            entity.getFullName(),
                            entity.getNickName(),
                            entity.getNik(),
                            entity.getGender(),
                            entity.getDateBirth(),
                            entity.getBirthOrder()
                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;
    }

    @Override
    public List<StudentLogs> findAll(int rpp,int page){
        List<StudentLogs> results = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaStudentLogsRepository.findAll()
                    .forEach(entity-> results.add(new StudentLogs(
                            entity.getId(),
                            entity.getFullName(),
                            entity.getNickName(),
                            entity.getNik(),
                            entity.getGender(),
                            entity.getDateBirth(),
                            entity.getBirthOrder()
                    )));
            case "elasticsearch" -> elasticSearchRepository.findAll()
                    .forEach(entity-> results.add(new StudentLogs(
                            entity.getId(),
                            entity.getFullName(),
                            entity.getNickName(),
                            entity.getNik(),
                            entity.getGender(),
                            entity.getDateBirth(),
                            entity.getBirthOrder()
                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;
    }

    @Override
    public Optional<StudentDetails> findById(Object id) {
        StudentDetails studentDetails = null;
        GradeDto academicGrades;

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                Optional<StudentLogJpa> resultJpa = jpaStudentLogsRepository.findById((Integer) id);
//                Optional<GradeJpa> resultGrade = jpaGradeRepository.findByStudentId(resultJpa.get().getId());
                if (resultJpa.isPresent()) {
                    StudentLogJpa studentJpa = resultJpa.get();
                    studentDetails = new StudentDetails();
                    studentDetails.fullName = studentJpa.getFullName();
                    studentDetails.nickName = studentJpa.getNickName();
                    studentDetails.nik = studentJpa.getNik();
                    studentDetails.gender = studentJpa.getGender();
                    studentDetails.dateBirth = studentJpa.getDateBirth();
                    studentDetails.birthOrder = studentJpa.getBirthOrder();
                    studentDetails.tribe = studentJpa.getTribe();
                    studentDetails.address = studentJpa.getAddress();
                    studentDetails.height = studentJpa.getHeight();
                    studentDetails.weight = studentJpa.getWeight();

                    academicGrades = jpaGradeRepository.findByStudentId(studentJpa.getId());
                    studentDetails.gradeClass = academicGrades;
                } else {
                    return Optional.empty();
                }
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }

        return Optional.ofNullable(studentDetails);
    }


}
