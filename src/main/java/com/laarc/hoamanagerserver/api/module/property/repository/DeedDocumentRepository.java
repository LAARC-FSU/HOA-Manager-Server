package com.laarc.hoamanagerserver.api.module.property.repository;

import com.laarc.hoamanagerserver.shared.model.DeedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeedDocumentRepository extends JpaRepository<DeedDocument, Long> {
}
