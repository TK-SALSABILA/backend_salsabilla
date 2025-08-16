package org.school.backend.adapters.datasources.repository.impl;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.*;
import org.school.backend.adapters.dto.*;
import org.school.backend.adapters.schema.jpa.ParentJpa;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.dto.StudentGradeDto;
import org.school.backend.application.dto.ParentDto;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

@Component
public class StudentLogsRepositoryImpl implements StudentLogsRepository {

    final ApplicationConfigProperties applicationConfigProperties;
    final JpaStudentLogsRepository jpaStudentLogsRepository;
    final JpaGradeRepository jpaGradeRepository;
    final JpaStudentGradeRepository jpaStudentGradeRepository;
    final JpaParentRepository jpaParentRepository;
//    final  ElasticSearchRepository elasticSearchRepository;

    public StudentLogsRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaStudentLogsRepository jpaStudentLogsRepository,
            final JpaGradeRepository jpaGradeRepository,
            final JpaStudentGradeRepository jpaStudentGradeRepository,
            final JpaParentRepository jpaParentRepository
//            ElasticSearchRepository elasticSearchRepository
    ){
        this.applicationConfigProperties =applicationConfigProperties;
        this.jpaStudentLogsRepository = jpaStudentLogsRepository;
        this.jpaGradeRepository = jpaGradeRepository;
        this.jpaStudentGradeRepository = jpaStudentGradeRepository;
        this.jpaParentRepository = jpaParentRepository;
//        this.elasticSearchRepository = elasticSearchRepository;
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
//            case "elasticsearch" -> elasticSearchRepository.findAll()
//                    .forEach(entity-> results.add(new StudentLogs(
//                            entity.getId(),
//                            entity.getFullName(),
//                            entity.getNickName(),
//                            entity.getNik(),
//                            entity.getGender(),
//                            entity.getDateBirth(),
//                            entity.getBirthOrder()
//                    )));
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
//            case "elasticsearch" -> elasticSearchRepository.findAll()
//                    .forEach(entity-> results.add(new StudentLogs(
//                            entity.getId(),
//                            entity.getFullName(),
//                            entity.getNickName(),
//                            entity.getNik(),
//                            entity.getGender(),
//                            entity.getDateBirth(),
//                            entity.getBirthOrder()
//                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;
    }

    @Override
    public Optional<StudentDetails> findById(Object id) {
        StudentDetails studentDetails;

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                Optional<StudentLogJpa> resultJpa = jpaStudentLogsRepository.findById((UUID) id);
                if (resultJpa.isPresent()) {
                    StudentLogJpa studentJpa = resultJpa.get();

                    studentDetails = new StudentDetails();
                    studentDetails.id = studentJpa.getId();
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

                    jpaStudentGradeRepository.findByStudentId(studentJpa.getId()).ifPresent(gradeJpa -> {
                        jpaGradeRepository.findById(gradeJpa.getGradeId()).ifPresent(gradeEntity -> {
                            GradeLogs gradeDto = new GradeLogs(
                                    gradeJpa.getGradeId(),
                                    gradeEntity.getGradeLevel()
                            );

                            StudentGrade academicGrades = new StudentGrade(
                                    gradeJpa.getAcademicYear(),
                                    gradeJpa.getIsCurrent(),
                                    gradeDto
                            );

                            studentDetails.gradeClass = academicGrades;
                        });
                    });
                } else {
                    return Optional.empty();
                }
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }

        return Optional.ofNullable(studentDetails);
    }


    @Override
    @Transactional
    public void create(StudentRequest record){
        if (applicationConfigProperties.getDatabaseDefault().toLowerCase().equals("postgresql")) {
            StudentLogJpa studentData = new StudentLogJpa(
                    record.getFullName(),
                    record.getNickName(),
                    record.getNik(),
                    record.getGender(),
                    record.getDateBirth(),
                    record.getBirthOrder(),
                    record.getTribe(),
                    record.getAddress(),
                    record.getHeight(),
                    record.getWeight()
            );
            jpaStudentLogsRepository.save(studentData);

            StudentGrade gradeDto = record.getGradeClass();
            StudentGradeJpa gradeData = new StudentGradeJpa(
                    gradeDto.getAcademicYear(),
                    gradeDto.getIsCurrent(),
                    studentData.getId(),
                    gradeDto.getGradeLog().getId()
            );
            jpaStudentGradeRepository.save(gradeData);

            ParentLogs parentDto = record.getParent();
            ParentJpa parentData = new ParentJpa(
                    parentDto.getFatherName(),
                    parentDto.getFatherDateBirth(),
                    parentDto.getFatherNik(),
                    parentDto.getFatherEducation(),
                    parentDto.getFatherJob(),
                    parentDto.getFatherCitizen(),
                    parentDto.getFatherIncome(),
                    parentDto.getFatherAddress(),
                    parentDto.getFatherPhone(),
                    parentDto.getMotherName(),
                    parentDto.getMotherDateBirth(),
                    parentDto.getMotherNik(),
                    parentDto.getMotherEducation(),
                    parentDto.getMotherCitizen(),
                    parentDto.getMotherIncome(),
                    parentDto.getMotherAddress(),
                    parentDto.getMotherPhone(),
                    studentData.getId()
            );
            jpaParentRepository.save(parentData);
        } else {
            throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }
    }

    @Override
    public void update(Object id, StudentDetails record) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                Optional<StudentLogJpa> optionalEntity = jpaStudentLogsRepository.findById((UUID) id);
                System.out.println(optionalEntity + "<<<<<<<<<<<<<<<<<><><<><><>");

                if (optionalEntity.isPresent()){
                    StudentLogJpa entity = optionalEntity.get();

                    entity.setFullName(record.getFullName());
                    entity.setNickName(record.getNickName());
                    entity.setNik(record.getNik());
                    entity.setGender(record.getGender());
                    entity.setDateBirth(record.getDateBirth());
                    entity.setBirthOrder(record.getBirthOrder());
                    entity.setTribe(record.getTribe());
                    entity.setAddress(record.getAddress());
                    entity.setHeight(record.getHeight());
                    entity.setWeight(record.getWeight());

                    jpaStudentLogsRepository.save(entity);
                }else {
                    throw new RuntimeException("Student not found with id: " + id);
                }
            }
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
    }

    @Override
    public List<StudentLogs> findAllStudentId(Set<UUID> id) {
        List<StudentLogs> results = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaStudentLogsRepository.findAllById(id)
                    .forEach(entity-> results.add(new StudentLogs(
                            entity.getId(),
                            entity.getFullName(),
                            entity.getNickName(),
                            entity.getNik(),
                            entity.getGender(),
                            entity.getDateBirth(),
                            entity.getBirthOrder()
                    )));
//            case "elasticsearch" -> elasticSearchRepository.findAll()
//                    .forEach(entity-> results.add(new StudentLogs(
//                            entity.getId(),
//                            entity.getFullName(),
//                            entity.getNickName(),
//                            entity.getNik(),
//                            entity.getGender(),
//                            entity.getDateBirth(),
//                            entity.getBirthOrder()
//                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;
    }
}
