package pro.angelogomez.restapi_oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private String status;
    private List<T> data;
    private Metadata metadata;

    @AllArgsConstructor
    @Data
    public static class Metadata {
        private int totalItems;
        private int totalPages;
        private int currentPages;
        private int itemsPerPage;
    }
}
