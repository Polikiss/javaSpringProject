package itmo.polikiss.entity;

import lombok.Getter;

@Getter
public class Filter<T> {
    public String field;
    public T value;
    public Filter(String field, T value) {
        this.field = field;
        this.value = value;
    }
}