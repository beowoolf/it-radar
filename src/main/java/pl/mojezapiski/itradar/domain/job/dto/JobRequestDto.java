package pl.mojezapiski.itradar.domain.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
//@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "director", toBuilder = true)
public class JobRequestDto extends JobResponse {
}
