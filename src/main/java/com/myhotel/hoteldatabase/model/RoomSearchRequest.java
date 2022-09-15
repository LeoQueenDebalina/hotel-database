package com.myhotel.hoteldatabase.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchRequest {
    @NotNull(message = "Field cannot be null")
    @ApiModelProperty(value = "Start Date")
    private Date startDate;
    @NotNull(message = "Field cannot be null")
    @ApiModelProperty(value = "End Date")
    private Date endDate;
    @NotNull(message = "Field cannot be null")
    @Pattern(regexp = "^[a-zA-Z- ]+$", message = "Only content character or space")
    @ApiModelProperty(value = "Room Type")
    private String roomType;
}
