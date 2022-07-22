package com.myhotel.hoteldatabase.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchRequest {
    @NotNull(message = "Field cannot be null")
    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "Only content date format yyyy-mm-dd")
    @ApiModelProperty(value = "Start Date")
    private String startDate;
    @NotNull(message = "Field cannot be null")
    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "Only content date format yyyy-mm-dd")
    @ApiModelProperty(value = "End Date")
    private String endDate;
    @NotNull(message = "Field cannot be null")
    @Pattern(regexp = "^[a-zA-Z- ]+$", message = "Only content character or space")
    @ApiModelProperty(value = "Room Type")
    private String roomType;
}
