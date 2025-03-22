package com.kostya.taskmanager.dto.taskdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Long assigneeId;
    private String dueDate;
    private String startDate;
    private String endDate;
    private String createdDate;
    private String updatedDate;


}


