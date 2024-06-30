package com.albaring.common.document;

import com.albaring.common.exception.common.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponse(responseCode = "200", description = "OK")
@ApiResponse(responseCode = "400,401,403,500", description = "공통 오류 응답", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
public interface BaseApiController {

}
