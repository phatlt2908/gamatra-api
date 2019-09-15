/**
 * create by PhatLT
 */

package vn.gamatra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto implements Serializable {
    public static final long serialVersionUID = 1L;

    private String appCode;
    private String message;
    private int status;
    private Timestamp timestamp;
    private String path;
    private Map<String, Object> values;

    public BaseDto(String appCode, String message) {
        this.appCode = appCode;
        this.message = message;
    }
}
