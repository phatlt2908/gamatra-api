package vn.gamatra.form;

import lombok.Data;
import vn.gamatra.response.Pagination;

import java.io.Serializable;

@Data
public class BaseSearchForm<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    private Pagination pagination;
    private T queries;
}
