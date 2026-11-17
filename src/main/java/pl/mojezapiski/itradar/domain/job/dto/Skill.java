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
public class Skill implements Serializable {

    private String name;
    private Integer level;

}
