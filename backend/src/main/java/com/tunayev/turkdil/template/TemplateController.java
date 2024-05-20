package com.tunayev.turkdil.template;

import com.tunayev.turkdil.model.Template;
import com.tunayev.turkdil.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/templates")
public class TemplateController {
    private final TemplateService templateService;

    @GetMapping("/{communityId}")
    public List<TemplateDTO> getAll(
            @PathVariable int communityId
    ) {
        return templateService.getAll(communityId);
    }

    @PostMapping("/{communityId}")
    public ResponseEntity<Template> create(
            @RequestBody TemplateCreateRequest request,
            @AuthenticationPrincipal User user,
            @PathVariable int communityId
    ) {
        return ResponseEntity.ok(templateService.save(request, user, communityId));
    }

    @GetMapping("/t/{id}")
    public ResponseEntity<TemplateDTO> get() {
        return ResponseEntity.ok(templateService.get(1));
    }

    @PutMapping("/t/{id}")
    public ResponseEntity<TemplateDTO> update(
            @PathVariable int id,
            @RequestBody TemplateUpdateRequest request
    ) {
        return ResponseEntity.ok(templateService.update(id, request));
    }

    @DeleteMapping("/t/{id}")
    public boolean delete(
            @RequestBody TemplateDeleteRequest request
    ) {
        return templateService.delete(request.getId());
    }
}
