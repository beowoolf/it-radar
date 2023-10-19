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
public class Multilocation implements Serializable {

    private String city;
    private String street;
    private String slug;

}
