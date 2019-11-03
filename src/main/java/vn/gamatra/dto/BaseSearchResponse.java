package vn.gamatra.dto;

import lombok.Data;
import vn.gamatra.response.Pagination;

import java.io.Serializable;

@Data
public class BaseSearchResponse implements Serializable {
    public static final long serialVersionUID = 1L;

    private Pagination pagination;
    private Object queries;
    private Object items;
}
