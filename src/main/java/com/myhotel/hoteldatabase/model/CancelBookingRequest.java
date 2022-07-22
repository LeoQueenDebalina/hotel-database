package com.myhotel.hoteldatabase.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelBookingRequest {
    @NotNull(message = "Field cannot be null")
    @Pattern(regexp = "^([1-9]\\d*|0)$", message = "Only content number")
    @Size(min = 3,max = 3,message = "Enter 3 digit room no")
    @ApiModelProperty(value = "Room Number")
    private String roomNo;
    @NotNull(message = "Field cannot be null")
    @Pattern(regexp = "^([1-9]\\d*|0)$", message = "Only content number")
    @Size(min = 10, max = 10, message = "Enter 10 digit mobile number")
    @ApiModelProperty(value = "Phone Number")
    private String userPhoneNumber;
}
