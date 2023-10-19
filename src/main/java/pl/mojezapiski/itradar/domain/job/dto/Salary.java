package pl.mojezapiski.itradar.domain.job.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Salary implements Serializable {

    private Integer from;
    private Integer to;
    private String currency;

}
