package org.school.backend.application.mappers;

import org.school.backend.application.dto.response.StudentsLogsOutputDto;
import org.school.backend.application.dto.response.TuitonFeeResDto;
import org.school.backend.domain.model.TuitionFeeModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public class TuitionResMapper {

    public static List<TuitonFeeResDto> toListDto(List<TuitionFeeModel> fees, Map<UUID, StudentsLogsOutputDto> studentMap) {
        return fees.stream()
                .map(fee -> {
                    StudentsLogsOutputDto student = studentMap.get(fee.studentId());
                    return new TuitonFeeResDto(
                            fee.id(),
                            fee.month(),
                            fee.amount(),
                            fee.status(),
                            toStringFormat( fee.transactionDate()),
                            student
                    );
                })
                .toList();
    }

}
