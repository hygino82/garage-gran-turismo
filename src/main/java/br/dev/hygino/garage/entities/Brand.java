package br.dev.hygino.garage.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.dev.hygino.garage.dtos.ResponseBrandDto;
import br.dev.hygino.garage.enums.Continent;
import br.dev.hygino.garage.enums.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The name cannot be null or empty")
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "The country cannot be null")
    private Country country;

    @NotNull(message = "The continent cannot be null")
    private Continent continent;

    private final List<Car> carlist = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResponseBrandDto toResponse() {
        return new ResponseBrandDto(id, name, country, continent, createdAt, updatedAt);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((continent == null) ? 0 : continent.hashCode());
        result = prime * result + ((carlist == null) ? 0 : carlist.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Brand other = (Brand) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (country != other.country)
            return false;
        if (continent != other.continent)
            return false;
        if (carlist == null) {
            if (other.carlist != null)
                return false;
        } else if (!carlist.equals(other.carlist))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        } else if (!updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }
}
